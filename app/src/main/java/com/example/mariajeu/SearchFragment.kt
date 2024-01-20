package com.example.mariajeu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        restaurantList.add(Restaurant("식당1"))
        restaurantList.add(Restaurant("식당2"))
        restaurantList.add(Restaurant("식당3"))
        restaurantList.add(Restaurant("식당4"))
        restaurantList.add(Restaurant("식당5"))
        restaurantList.add(Restaurant("식당6"))
        restaurantList.add(Restaurant("식당7"))
        restaurantList.add(Restaurant("식당8"))
        restaurantList.add(Restaurant("식당9"))
        restaurantList.add(Restaurant("식당10"))
        Log.d("TEST레스토랑TEST", "Size: ${restaurantList.size}")

        val adapter = RestaurantAdapter(requireContext(), restaurantList)
        binding.lvRestaurant.adapter = adapter

        return binding.root
    }
}