package com.example.mariajeu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mariajeu.databinding.FragmentFiltermapBinding

class FiltermapFragment  : Fragment(){

    lateinit var binding: FragmentFiltermapBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiltermapBinding.inflate(inflater, container, false)


        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 닫기 클릭시
        setMapCancleClickListener()

        binding.filterMapSec3ConfirmTv.setOnClickListener {
            navigateToNextResDetailFragment()
        }

    }

    private fun navigateToNextResDetailFragment() {
        // 이동할 Fragment 생성
        val nextFragment = RestaurantDetailFragment()

        // Fragment 전환
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.filter_map_constraintlayout, nextFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    private fun setMapCancleClickListener() {
        binding.filterMapSec3CancelV.setOnClickListener {
            navigateToSearchFragment()
        }

        binding.filterMapSec3CancelTv.setOnClickListener {
            navigateToSearchFragment()
        }
    }

    private fun navigateToSearchFragment() {
        // Clear the entire back stack and navigate back to the SearchFragment
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val searchFragment = SearchFragment()


        // Replace the current fragment with the SearchFragment
        fragmentManager.beginTransaction()
            .replace(R.id.searchFragment_id, searchFragment)
            .commit()

        // search 프래그먼트의 list 겹침 해결 (숨기기)
        val lvRestaurant: ListView? = requireActivity().findViewById(R.id.lv_restaurant)
        lvRestaurant?.visibility = View.GONE

    }


}