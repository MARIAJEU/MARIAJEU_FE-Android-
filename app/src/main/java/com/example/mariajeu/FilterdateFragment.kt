package com.example.mariajeu


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.example.mariajeu.databinding.FragmentFilterdateBinding

class FilterdateFragment : Fragment() {
    lateinit var binding: FragmentFilterdateBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterdateBinding.inflate(inflater, container, false)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDateChangeListener()
        setupPersonnelClickListeners()
        setupTimeFilterClickListeners()

        binding.filterDateConfirmV.setOnClickListener {
            navigateToNextRegionFragment()
        }


    }


    private fun navigateToNextRegionFragment() {
        // 이동할 Fragment 생성
        val nextFragment = FilterregionFragment()

        // Fragment 전환
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.filter_date_constraintlayout, nextFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }



    private fun setupDateChangeListener() {
        binding.filterDateCalendarV.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // 선택된 날짜에 대한 처리
            val selectedDate = "$year-$month-$dayOfMonth"
            Toast.makeText(requireContext(), "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()

            // TODO: 선택된 날짜에 따라 스타일 변경 등의 작업 수행
        }
    }


    private fun setupPersonnelClickListeners() {
        val filterViews = arrayOf(
            binding.filterDateSec2Per01V, binding.filterDateSec2Per02V,
            binding.filterDateSec2Per03V, binding.filterDateSec2Per04V,
            binding.filterDateSec2Per05V, binding.filterDateSec2Per06V,
            binding.filterDateSec2Per07V
        )

        val filterTextViews = arrayOf(
            binding.filterDateSec2Per01Tv, binding.filterDateSec2Per02Tv,
            binding.filterDateSec2Per03Tv, binding.filterDateSec2Per04Tv,
            binding.filterDateSec2Per05Tv, binding.filterDateSec2Per06Tv,
            binding.filterDateSec2Per07Tv
        )

        for (i in filterViews.indices) {
            filterViews[i].setOnClickListener {
                // 클릭 시 처리
                updatePersonnelView(i)
            }
        }
    }
    private fun updatePersonnelView(selectedIndex: Int) {
        val filterViews = arrayOf(
            binding.filterDateSec2Per01V, binding.filterDateSec2Per02V,
            binding.filterDateSec2Per03V, binding.filterDateSec2Per04V,
            binding.filterDateSec2Per05V, binding.filterDateSec2Per06V,
            binding.filterDateSec2Per07V
        )

        val filterTextViews = arrayOf(
            binding.filterDateSec2Per01Tv, binding.filterDateSec2Per02Tv,
            binding.filterDateSec2Per03Tv, binding.filterDateSec2Per04Tv,
            binding.filterDateSec2Per05Tv, binding.filterDateSec2Per06Tv,
            binding.filterDateSec2Per07Tv
        )

        // 전체 뷰 초기화
        resetPersonnelViews()

        // 선택된 뷰에 대한 처리
        filterViews[selectedIndex].background =
            requireContext().getDrawable(R.drawable.filter_date_ellipse_25)
        filterTextViews[selectedIndex].setTextColor(requireContext().getColor(R.color.white))
    }
    private fun resetPersonnelViews() {
        val filterViews = arrayOf(
            binding.filterDateSec2Per01V, binding.filterDateSec2Per02V,
            binding.filterDateSec2Per03V, binding.filterDateSec2Per04V,
            binding.filterDateSec2Per05V, binding.filterDateSec2Per06V,
            binding.filterDateSec2Per07V
        )

        val filterTextViews = arrayOf(
            binding.filterDateSec2Per01Tv, binding.filterDateSec2Per02Tv,
            binding.filterDateSec2Per03Tv, binding.filterDateSec2Per04Tv,
            binding.filterDateSec2Per05Tv, binding.filterDateSec2Per06Tv,
            binding.filterDateSec2Per07Tv
        )

        for (i in filterViews.indices) {
            filterViews[i].background = requireContext().getDrawable(R.drawable.filter_date_ellipse_26)
            filterTextViews[i].setTextColor(requireContext().getColor(R.color.black))
        }
    }

    private fun setupTimeFilterClickListeners() {
        val timeFilterViews = arrayOf(
            binding.filterDateSec2Time01V,
            binding.filterDateSec2Time02V,
            binding.filterDateSec2Time03V,
            binding.filterDateSec2Time04V,
            binding.filterDateSec2Time05V,
            binding.filterDateSec2Time06V
        )

        val timeFilterTextViews = arrayOf(
            binding.filterDateSec2Time01Tv,
            binding.filterDateSec2Time02Tv,
            binding.filterDateSec2Time03Tv,
            binding.filterDateSec2Time04Tv,
            binding.filterDateSec2Time05Tv,
            binding.filterDateSec2Time06Tv
        )

        for (i in timeFilterViews.indices) {
            timeFilterViews[i]?.setOnClickListener {
                updateTimeFilterView(i, timeFilterViews, timeFilterTextViews)
            }
        }
    }

    private fun updateTimeFilterView(selectedIndex: Int, timeFilterViews: Array<View>, timeFilterTextViews: Array<TextView>) {
        resetTimeFilterViews(timeFilterViews, timeFilterTextViews)
        timeFilterViews[selectedIndex]?.background =
            requireContext().getDrawable(R.drawable.filter_date_rectangle_9)
        timeFilterTextViews[selectedIndex]?.setTextColor(requireContext().getColor(R.color.white))
    }

    private fun resetTimeFilterViews(timeFilterViews: Array<View>, timeFilterTextViews: Array<TextView>) {
        for (i in timeFilterViews.indices) {
            timeFilterViews[i]?.background =
                requireContext().getDrawable(R.drawable.filter_date_rectangle_8)
            timeFilterTextViews[i]?.setTextColor(requireContext().getColor(R.color.black))
        }
    }


}