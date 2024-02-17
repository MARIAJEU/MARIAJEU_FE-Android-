package com.example.mariajeu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mariajeu.databinding.FragmentFilterpriceBinding

class FilterpriceFragment  : Fragment(){

    lateinit var binding: FragmentFilterpriceBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterpriceBinding.inflate(inflater, container, false)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 닫기 클릭시
        setPriceCancleClickListener()

        binding.filterPriceSec3ConfirmV.setOnClickListener {
            navigateToNextMoodFragment()
        }


    }

    private fun navigateToNextMoodFragment() {
        // 이동할 Fragment 생성
        val nextFragment = FiltermoodFragment()

        // Fragment 전환
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.filter_price_constraintlayout, nextFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun setPriceCancleClickListener() {
        binding.filterPriceSec3CancelV.setOnClickListener {
            navigateToSearchFragment()
        }

        binding.filterPriceSec3CancelTv.setOnClickListener {
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