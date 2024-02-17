package com.example.mariajeu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mariajeu.databinding.FragmentFiltermoodBinding

class FiltermoodFragment : Fragment() {
    lateinit var binding: FragmentFiltermoodBinding

    private lateinit var moodViews: Array<View>
    private lateinit var moodTexts: Array<TextView>
    private lateinit var resetImageView: ImageView
    private lateinit var resetTextView: TextView
    private var selectedIndexes = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiltermoodBinding.inflate(inflater, container, false)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 닫기 클릭시
        setMoodCancleClickListener()

        initViews()

        for (i in moodViews.indices) {
            val index = i
            moodViews[i].setOnClickListener {
                toggleMoodViewSelected(index)
            }

            moodTexts[i].setOnClickListener {
                toggleMoodViewSelected(index)
            }
        }

        resetImageView.setOnClickListener {
            resetMoodViews()
        }
        resetTextView.setOnClickListener {
            resetMoodViews()
        }


        binding.filterMoodSec3ConfirmV.setOnClickListener {
            navigateToNextMapFragment()
        }

    }

    private fun navigateToNextMapFragment() {
        // 이동할 Fragment 생성
        val nextFragment = FiltermapFragment()

        // Fragment 전환
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.filter_mood_constraintlayout, nextFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }



    private fun setMoodCancleClickListener() {
        binding.filterMoodSec3CancelV.setOnClickListener {
            navigateToSearchFragment()
        }

        binding.filterMoodSec3CancelTv.setOnClickListener {
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


    private fun initViews() {
        moodViews = arrayOf(
            binding.filterMoodSec2Choose01V,
            binding.filterMoodSec2Choose02V,
            binding.filterMoodSec2Choose03V,
            binding.filterMoodSec2Choose04V,
            binding.filterMoodSec2Choose05V,
            binding.filterMoodSec2Choose06V,
            binding.filterMoodSec2Choose07V,
            binding.filterMoodSec2Choose08V,
            binding.filterMoodSec2Choose09V,
            binding.filterMoodSec2Choose10V,
            binding.filterMoodSec2Choose11V
        )

        moodTexts = arrayOf(
            binding.filterMoodSec2Choose01Tv,
            binding.filterMoodSec2Choose02Tv,
            binding.filterMoodSec2Choose03Tv,
            binding.filterMoodSec2Choose04Tv,
            binding.filterMoodSec2Choose05Tv,
            binding.filterMoodSec2Choose06Tv,
            binding.filterMoodSec2Choose07Tv,
            binding.filterMoodSec2Choose08Tv,
            binding.filterMoodSec2Choose09Tv,
            binding.filterMoodSec2Choose10Tv,
            binding.filterMoodSec2Choose11Tv
        )

        resetImageView = binding.filterMoodSec1ResetIv
        resetTextView = binding.filterMoodSec1ResetTv
    }

    private fun toggleMoodViewSelected(index: Int) {
        if (selectedIndexes.contains(index)) {
            // 이미 선택된 경우 해제
            selectedIndexes.remove(index)
            resetMoodView(index)
        } else {
            // 선택되지 않은 경우 선택
            selectedIndexes.add(index)
            setMoodViewSelected(index)
        }
    }

    private fun setMoodViewSelected(index: Int) {
        val selectedBackground = resources.getDrawable(R.drawable.filter_mood_rectangle_7_black, null)
        moodViews[index].background = selectedBackground
        moodTexts[index].setTextColor(resources.getColor(android.R.color.white, null))
    }

    private fun resetMoodView(index: Int) {
        val defaultBackground = resources.getDrawable(R.drawable.filter_mood_rectangle_7, null)
        moodViews[index].background = defaultBackground
        moodTexts[index].setTextColor(resources.getColor(R.color.black, null))
    }

    private fun resetMoodViews() {
        for (index in selectedIndexes) {
            resetMoodView(index)
        }
        selectedIndexes.clear()
    }



}