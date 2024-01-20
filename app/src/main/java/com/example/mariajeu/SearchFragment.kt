package com.example.mariajeu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
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


        return binding.root
    }

}