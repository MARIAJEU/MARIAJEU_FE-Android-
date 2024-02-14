package com.example.mariajeu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.mariajeu.databinding.FragmentSearchBinding

class SharedViewModel : ViewModel() {
    var btnHeartClikedInfo: String? = null
}

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var mainActivity: MainActivity
    private val viewModel by viewModels<SharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSearchBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        var restaurantList = arrayListOf<RestaurantDTO>() // 리스트뷰를 위해 생성

        fun setValues() {
            /* 예시 데이터 */
            restaurantList.add(RestaurantDTO("마리아주", 0))
            restaurantList.add(RestaurantDTO("AOS", 1))
            restaurantList.add(RestaurantDTO("안드로이드 파이팅", 2))
            restaurantList.add(RestaurantDTO("인하대 맛집", 3))
            restaurantList.add(RestaurantDTO("아주대 맛집", 4))
            restaurantList.add(RestaurantDTO("인하대 와인", 5))
            restaurantList.add(RestaurantDTO("아주대 와인", 6))
            restaurantList.add(RestaurantDTO("미테펍", 7))
            restaurantList.add(RestaurantDTO("와인파인", 8))
            restaurantList.add(RestaurantDTO("옐로퍼플", 9))
            Log.d("TEST레스토랑TEST", "Size: ${restaurantList.size}")

            val adapter = RestaurantAdapter(requireContext(),restaurantList)
            binding.lvRestaurant.adapter = adapter

            var cntRestaurant = adapter.count

            binding.tvCntRestaurant.text = cntRestaurant.toString() + "개의 매장"
        }

        // ********************************* 리스트뷰 데이터 ********************************************* //

        setValues()

        // ****************************** 팝업 메뉴 ********************************************//
        // 팝업 메뉴를 표시할 버튼이나 뷰를 찾아옵니다.
        val popupBtn: View = binding.tvSelectedFilter

        // 팝업 메뉴 객체를 생성합니다.
        val popupMenuView = layoutInflater.inflate(R.layout.custom_popup_menu, null)
        val popupWindow = PopupWindow(
            popupMenuView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )


        var btn1: ImageButton = popupMenuView.findViewById(R.id.ib_menu_check1)
        var btn2: ImageButton = popupMenuView.findViewById(R.id.ib_menu_check2)
        var btn3: ImageButton = popupMenuView.findViewById(R.id.ib_menu_check3)
        var btn4: ImageButton = popupMenuView.findViewById(R.id.ib_menu_check4)
        var btn5: ImageButton = popupMenuView.findViewById(R.id.ib_menu_check5)

        fun handleMenuItemClick(clickedButton: ImageButton, visibility: Int) {
            val buttons = arrayOf(btn1, btn2, btn3, btn4, btn5)

            buttons.forEach {
                it.visibility = if (it == clickedButton) visibility else View.GONE
            }
        }

        fun setMenuItemClickListener(textViewId: Int, imageButton: ImageButton, visibility: Int) {
            popupMenuView.findViewById<TextView>(textViewId).setOnClickListener {
                handleMenuItemClick(imageButton, visibility)
                binding.tvSelectedFilter.text = popupMenuView.findViewById<TextView>(textViewId).text.toString()
                popupWindow.dismiss()
            }
        }

        // 팝업 메뉴 아이템 클릭 리스너 설정
        setMenuItemClickListener(R.id.menu_recommand, btn1, View.VISIBLE)
        setMenuItemClickListener(R.id.menu_review, btn2, View.VISIBLE)
        setMenuItemClickListener(R.id.menu_price_high, btn3, View.VISIBLE)
        setMenuItemClickListener(R.id.menu_price_low, btn4, View.VISIBLE)
        setMenuItemClickListener(R.id.menu_distance, btn5, View.VISIBLE)

        // 팝업 메뉴가 표시되도록 클릭 이벤트 리스너 설정
        popupBtn.setOnClickListener {
            popupWindow.showAsDropDown(popupBtn)
        }

        // ************************** 다이얼로그 ********************************************//



        return binding.root


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

}
