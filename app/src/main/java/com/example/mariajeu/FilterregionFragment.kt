package com.example.mariajeu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
}