package com.example.mariajeu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mariajeu.databinding.FragmentRestaurantDetailBinding

class RestaurantDetailFragment : Fragment() {

//    var mainActivity: MainActivity ?= null
    lateinit var binding: FragmentRestaurantDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is MainActivity) mainActivity = context
//    }
}