package com.example.mariajeu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
}