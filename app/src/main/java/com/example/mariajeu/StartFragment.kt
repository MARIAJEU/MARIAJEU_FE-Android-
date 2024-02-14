package com.example.mariajeu

import android.app.Activity
import android.content.Intent
import android.content.Intent.getIntent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.view.MotionEvent
import android.view.View.GONE
import android.view.View.OnHoverListener
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.mariajeu.databinding.FragmentStartBinding
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.FragmentManager


class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding

    private var selectedView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { // activity A 호출 -> B 호출 -> A 호출 (결과값 반환) 시에 사용하는 함수
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("값 전달받기 성공", "성공")

        // 로그인 성공 시, 로그인 -> 로그아웃 버튼으로 전환됨
        // ** 현재는 카카오톡 로그인 시에만 적용됨 ** //

        val mypageFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.mypageFragment) as? MypageFragment // MypageFragment에 있는 함수 호출하기 위해 작성함

        startLogin()
        mypageFragment?.mypageLogin()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("FragmentTransaction", "Before: ${requireActivity().supportFragmentManager.fragments}")

        var startBtnLogin = binding.startLoginTv
        var startBtnLogout = binding.startLogoutTv

        startBtnLogin.setOnClickListener {
            // 클릭 이벤트 발생 시 LoginActivity 로 전환
            val intent = Intent(activity, LoginActivity::class.java)
            intent.putExtra("로그인으로", "toLogin")
            startActivityForResult(intent, 1)
        }

        binding.startWhiteV.setOnClickListener { handleViewClick(binding.startWhiteV, binding.startWhiteTv) }
        binding.startRedV.setOnClickListener { handleViewClick(binding.startRedV, binding.startRedTv) }
        binding.startSparklingV.setOnClickListener { handleViewClick(binding.startSparklingV, binding.startSparklingTv) }


        binding.startChoice01V.setOnTouchListener(createChoiceTouchListener(binding.startChoice01Tv))
        binding.startChoice02V.setOnTouchListener(createChoiceTouchListener(binding.startChoice02Tv))
        binding.startChoice03V.setOnTouchListener(createChoiceTouchListener(binding.startChoice03Tv))
        binding.startChoice04V.setOnTouchListener(createChoiceTouchListener(binding.startChoice04Tv))


        // start_menu1_v 클릭 시 FoodFragment로 전환
        binding.startMenu1V.setOnClickListener {
            navigateToFoodFragment()
        }

        // start_menu1_tv 클릭 시 FoodFragment로 전환
        binding.startMenu1Tv.setOnClickListener {
            navigateToFoodFragment()
        }

        Log.d("FragmentTransaction", "After: ${requireActivity().supportFragmentManager.fragments}")
    }

    fun startLogin() {
        binding.startLoginTv.visibility = View.GONE
        binding.startLogoutTv.visibility = View.VISIBLE
    }

    fun startLogout() {
        binding.startLoginTv.visibility = View.VISIBLE
        binding.startLogoutTv.visibility = View.GONE
    }

    private fun createChoiceTouchListener(choiceTextView: TextView): View.OnTouchListener {
        return View.OnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> handleChoiceClickDown(choiceTextView)
                MotionEvent.ACTION_UP -> handleChoiceClickUp()
            }
            true
            // true로 이벤트 계속 발생하게
        }
    }

    private fun handleChoiceClickDown(choiceTextView: TextView) {
        val choiceText = choiceTextView.text.toString()
        Log.d("ClickEvent", "Mouse click down occurred for choice: $choiceText")

        when (choiceText) {
            "BOLD" -> setMouseOnExText("향이 뚜렷하고 쉽게 와인의 맛을 감별할 수 있는 맛.\n높을수록 Bold하며, 낮을수록 Light합니다.")
            "ACIDIC" -> setMouseOnExText("와인에서 느껴지는 신맛의 정도를 가리킵니다.\n높을수록 ACIDIC하며, 낮을수록 SOFT합니다.")
            "FIZZY" -> setMouseOnExText("거품을 부여하여 생동감 있고 상쾌한 맛.\n높을수록 FIZZY하며, 낮을수록 GENTLE합니다.")
            "TANNIC" -> setMouseOnExText("와인의 쓴맛과 떫은맛의 복합성을 더합니다.\n높을수록 TANNIC하며, 낮을수록 SMOOTH합니다.")
            "SWEET" -> setMouseOnExText("와인의 당도뿐 아니라 감미로운 맛을 표현합니다.\n높을수록 SWEET하며, 낮을수록 DRY합니다.")
            else -> setMouseOnExText("")
        }


        setMouseOnExTopMargin(choiceTextView)
        binding.startMouseonExV.visibility = View.VISIBLE
        binding.startMouseonExTv.visibility = View.VISIBLE
    }

    private fun handleChoiceClickUp() {
        binding.startMouseonExV.visibility = GONE
        binding.startMouseonExTv.visibility = GONE
    }

    private fun getClickedChoiceId(): Int {
        return when (selectedView?.id) {
            R.id.start_choice_01_v -> R.id.start_choice_01_v
            R.id.start_choice_02_v -> R.id.start_choice_02_v
            R.id.start_choice_03_v -> R.id.start_choice_03_v
            R.id.start_choice_04_v -> R.id.start_choice_04_v
            else -> R.id.start_choice_01_v
        }
    }

    private fun setMouseOnExText(text: String) {
        binding.startMouseonExTv.text = text
    }

    private fun setMouseOnExTopMargin(clickedView: View) {
        val params = binding.startMouseonExV.layoutParams as ConstraintLayout.LayoutParams
        params.topToTop = clickedView.id
        binding.startMouseonExV.layoutParams = params
    }

    private fun handleViewClick(clickedView: View, clickedTextView: View) {
        // 기존에 선택된 뷰가 있으면 원래의 배경으로 되돌림
        selectedView?.apply {
            background = requireContext().getDrawable(R.drawable.rectangle_2)
        }

        // 선택된 뷰를 갱신하고 새로운 배경으로 변경
        selectedView = clickedView
        clickedView.background = requireContext().getDrawable(R.drawable.rectangle_18)


        when (selectedView?.id) {
            R.id.start_red_v -> {
                setChoiceTextViewText("BOLD", "TANNIC", "SWEET")

                binding.startRectangle16V.visibility = View.VISIBLE
                binding.startRectangle1V.visibility = View.INVISIBLE
                showAdditionalViewsForRed()
                val params = binding.startMenu1V.layoutParams as ConstraintLayout.LayoutParams
                params.topMargin = resources.getDimensionPixelSize(R.dimen.menu1_margin_top_red)
                binding.startMenu1V.layoutParams = params
            }
            R.id.start_white_v -> {
                setChoiceTextViewText("BOLD", "SWEET", "ACIDIC")

                binding.startRectangle16V.visibility = View.INVISIBLE
                binding.startRectangle1V.visibility = View.VISIBLE
                hideAdditionalViews()
                val params = binding.startMenu1V.layoutParams as ConstraintLayout.LayoutParams
                params.topMargin = resources.getDimensionPixelSize(R.dimen.menu1_margin_top_white)
                binding.startMenu1V.layoutParams = params
            }
            R.id.start_sparkling_v -> {
                setChoiceTextViewText("BOLD", "ACIDIC", "FIZZY")

                binding.startRectangle16V.visibility = View.INVISIBLE
                binding.startRectangle1V.visibility = View.VISIBLE
                hideAdditionalViews()
                val params = binding.startMenu1V.layoutParams as ConstraintLayout.LayoutParams
                params.topMargin = resources.getDimensionPixelSize(R.dimen.menu1_margin_top_sparkling)
                binding.startMenu1V.layoutParams = params
            }
        }
    }

    private fun showAdditionalViewsForRed() {
        val choiceTextView4 = view?.findViewById<TextView>(R.id.start_choice_04_tv)
        choiceTextView4?.visibility = View.VISIBLE

        val choiceView4 = view?.findViewById<View>(R.id.start_choice_04_v)
        choiceView4?.visibility = View.VISIBLE

        val choiceImageView4 = view?.findViewById<ImageView>(R.id.start_choice_04_iv)
        choiceImageView4?.visibility = View.VISIBLE
    }

    private fun hideAdditionalViews() {
        val choiceTextView4 = view?.findViewById<TextView>(R.id.start_choice_04_tv)
        choiceTextView4?.visibility = View.INVISIBLE

        val choiceView4 = view?.findViewById<View>(R.id.start_choice_04_v)
        choiceView4?.visibility = View.INVISIBLE

        val choiceImageView4 = view?.findViewById<ImageView>(R.id.start_choice_04_iv)
        choiceImageView4?.visibility = View.INVISIBLE
    }


    private fun setChoiceTextViewText(text1: String, text2: String, text3: String) {
        // 선택된 텍스트뷰의 ID에 따라 텍스트 설정
        val textView1Id = when (selectedView?.id) {
            R.id.start_red_v, R.id.start_white_v, R.id.start_sparkling_v -> R.id.start_choice_01_tv
            else -> null
        }

        val textView2Id = when (selectedView?.id) {
            R.id.start_red_v -> R.id.start_choice_02_tv
            R.id.start_white_v -> R.id.start_choice_02_tv
            R.id.start_sparkling_v -> R.id.start_choice_02_tv
            else -> null
        }

        val textView3Id = when (selectedView?.id) {
            R.id.start_red_v -> R.id.start_choice_03_tv
            R.id.start_white_v -> R.id.start_choice_03_tv
            R.id.start_sparkling_v -> R.id.start_choice_03_tv
            else -> null
        }

        textView1Id?.let {
            val choiceTextView1 = view?.findViewById<TextView>(it)
            choiceTextView1?.text = text1
        }

        textView2Id?.let {
            val choiceTextView2 = view?.findViewById<TextView>(it)
            choiceTextView2?.text = text2
        }

        textView3Id?.let {
            val choiceTextView3 = view?.findViewById<TextView>(it)
            choiceTextView3?.text = text3
        }
    }

    private fun navigateToFoodFragment() {
        val foodFragment = FoodFragment()

        // 프래그먼트 전환을 위한 트랜잭션 시작
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        transaction.replace(R.id.start_constraintlayout, foodFragment)

        // 이전의 백스택을 모두 제거
        requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

//        Log.d("FragmentTransaction", "Before: ${requireActivity().supportFragmentManager.fragments}")

        // FoodFragment를 추가하거나 보여줌
        transaction.add(R.id.start_constraintlayout, foodFragment)

//        Log.d("FragmentTransaction", "After: ${requireActivity().supportFragmentManager.fragments}")


        // 트랜잭션 커밋
        transaction.commit()

//        Log.d("FragmentTransaction", "Before: ${requireActivity().supportFragmentManager.fragments}")
////        val transaction = requireActivity().supportFragmentManager.beginTransaction()
////        transaction.replace(R.id.start_constraintlayout, foodFragment)
////        transaction.addToBackStack(null)
////        transaction.commit()
//
//        val transaction = requireActivity().supportFragmentManager.beginTransaction()
//
//        transaction.setCustomAnimations(
//            android.R.anim.fade_in,
//            android.R.anim.fade_out,
//            android.R.anim.fade_in,
//            android.R.anim.fade_out
//        )
//        transaction.replace(R.id.start_constraintlayout, foodFragment)
//
//        // 현재 보이는 프래그먼트를 숨김
//        val startFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.main_frm)
//        if (startFragment != null) {
//            transaction.hide(startFragment)
//        }
//
//        // FoodFragment를 추가하거나 보여줌
//        if (!foodFragment.isAdded) {
//            // FoodFragment가 아직 추가되지 않았으면 추가
//            transaction.add(R.id.main_frm, foodFragment)
//        }
//
//        // 숨겨진 상태인 경우 보이게 함
//        transaction.show(foodFragment)
//
//        // addToBackStack을 사용하여 백스택에 추가
//        transaction.addToBackStack(null)
//
//        requireActivity().supportFragmentManager.addOnBackStackChangedListener {
//            Log.d("FragmentManager", "BackStackChanged")
//        }
//        transaction.addToBackStack(null)
//        transaction.commit()
//
//
//        // FragmentManager의 fragment 목록 출력
//        Log.d("FragmentManager", "Fragments after transaction: ${requireActivity().supportFragmentManager.fragments}")
    }
}