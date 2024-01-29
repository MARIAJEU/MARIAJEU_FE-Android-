package com.example.mariajeu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
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
}