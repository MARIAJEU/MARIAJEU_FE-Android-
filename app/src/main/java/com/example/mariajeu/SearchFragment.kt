package com.example.mariajeu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mariajeu.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding

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

        // ********************************* 리스트뷰 데이터 ********************************************* //

        var restaurantList = arrayListOf<Restaurant>() // 리스트뷰를 위해 생성

        /* 예시 데이터 */
        restaurantList.add(Restaurant("마리아주"))
        restaurantList.add(Restaurant("AOS"))
        restaurantList.add(Restaurant("안드로이드 파이팅"))
        restaurantList.add(Restaurant("인하대 맛집"))
        restaurantList.add(Restaurant("아주대 맛집"))
        restaurantList.add(Restaurant("인하대 와인"))
        restaurantList.add(Restaurant("아주대 와인"))
        restaurantList.add(Restaurant("미테펍"))
        restaurantList.add(Restaurant("와인파인"))
        restaurantList.add(Restaurant("옐로퍼플"))
        Log.d("TEST레스토랑TEST", "Size: ${restaurantList.size}")

        val adapter = RestaurantAdapter(requireContext(), restaurantList)
        binding.lvRestaurant.adapter = adapter

        var cntRestaurant = adapter.count

        binding.tvCntRestaurant.text = cntRestaurant.toString() + "개의 매장"


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

        // 팝업 메뉴 아이템 클릭 리스너 설정
        popupMenuView.findViewById<TextView>(R.id.menu_recommand).setOnClickListener {
            handleMenuItemClick(btn1, View.VISIBLE)
            binding.tvSelectedFilter.text = popupMenuView.findViewById<TextView>(R.id.menu_recommand).text.toString()
            popupWindow.dismiss()
        }

        popupMenuView.findViewById<TextView>(R.id.menu_review).setOnClickListener {
            handleMenuItemClick(btn2, View.VISIBLE)
            binding.tvSelectedFilter.text = popupMenuView.findViewById<TextView>(R.id.menu_review).text.toString()
            popupWindow.dismiss()
        }

        popupMenuView.findViewById<TextView>(R.id.menu_price_high).setOnClickListener {
            handleMenuItemClick(btn3, View.VISIBLE)
            binding.tvSelectedFilter.text = popupMenuView.findViewById<TextView>(R.id.menu_price_high).text.toString()
            popupWindow.dismiss()
        }

        popupMenuView.findViewById<TextView>(R.id.menu_price_low).setOnClickListener {
            handleMenuItemClick(btn4, View.VISIBLE)
            binding.tvSelectedFilter.text = popupMenuView.findViewById<TextView>(R.id.menu_price_low).text.toString()
            popupWindow.dismiss()
        }

        popupMenuView.findViewById<TextView>(R.id.menu_distance).setOnClickListener {
            handleMenuItemClick(btn5, View.VISIBLE)
            binding.tvSelectedFilter.text = popupMenuView.findViewById<TextView>(R.id.menu_distance).text.toString()
            popupWindow.dismiss()
        }

        // 팝업 메뉴가 표시되도록 클릭 이벤트 리스너 설정
        popupBtn.setOnClickListener {
            popupWindow.showAsDropDown(popupBtn)
        }


        return binding.root
    }



}