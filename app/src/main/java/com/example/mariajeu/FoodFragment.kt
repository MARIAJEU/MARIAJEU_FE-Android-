package com.example.mariajeu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mariajeu.databinding.FragmentFoodBinding
import com.example.mariajeu.databinding.FragmentStartBinding

class FoodFragment : Fragment() {

    lateinit var binding: FragmentFoodBinding

    private var selectedView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodBinding.inflate(inflater, container, false)
        return binding.root
    }



}