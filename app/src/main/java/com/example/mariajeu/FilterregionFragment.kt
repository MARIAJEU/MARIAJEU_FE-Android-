package com.example.mariajeu

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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


        setClickListeners()

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

    private fun setClickListeners() {
        val textViews = arrayOf(
            binding.filterRegionSec2Right01Tv,
            binding.filterRegionSec2Right02Tv,
            binding.filterRegionSec2Right03Tv,
            binding.filterRegionSec2Right04Tv,
            binding.filterRegionSec2Right05Tv,
            binding.filterRegionSec2Right06Tv,
            binding.filterRegionSec2Right07Tv,
            binding.filterRegionSec2Right08Tv,
            binding.filterRegionSec2Right09Tv,
            binding.filterRegionSec2Right10Tv,
            binding.filterRegionSec2Right11Tv,
            binding.filterRegionSec2Right12Tv
        )

        for (textView in textViews) {
            textView.setOnClickListener {
                toggleStyle(textView)
            }
        }

        // 초기화 (텍스트뷰, 이미지뷰) 클릭 리스너 설정
        val resetTextView = binding.filterRegionSec1ResetTv
        val resetImageView = binding.filterRegionSec1ResetIv

        resetTextView.setOnClickListener {
            resetStyles(textViews)
        }

        resetImageView.setOnClickListener {
            resetStyles(textViews)
        }
    }

    private fun toggleStyle(textView: TextView) {
        if (textView.tag == null || textView.tag == false) {
            // 선택되지 않았을 때 스타일 변경
            textView.setBackgroundColor(Color.GRAY)
            textView.setTextColor(Color.WHITE)
            textView.tag = true
        } else {
            // 선택되었을 때 스타일 복구
            textView.setBackgroundColor(Color.TRANSPARENT)
            textView.setTextColor(Color.parseColor("#000000"))
            textView.tag = false
        }
    }

    private fun resetStyles(textViews: Array<TextView>) {
        // 모든 TextView의 스타일 초기화
        for (textView in textViews) {
            textView.setBackgroundColor(Color.TRANSPARENT)
            textView.setTextColor(Color.parseColor("#000000"))
            textView.tag = false
        }
    }


}