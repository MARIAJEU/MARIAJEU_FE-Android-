package com.example.mariajeu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mariajeu.databinding.FragmentFilterregionBinding

class FilterregionFragment : Fragment() {

    lateinit var binding: FragmentFilterregionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterregionBinding.inflate(inflater, container, false)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterRegionSec3ConfirmV.setOnClickListener {
            navigateToNextPriceFragment()
        }

    }

    private fun navigateToNextPriceFragment() {
        // 이동할 Fragment 생성
        val nextFragment = FilterpriceFragment()

        // Fragment 전환
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.filter_region_constraintlayout, nextFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}