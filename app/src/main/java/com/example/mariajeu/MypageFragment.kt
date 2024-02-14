package com.example.mariajeu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mariajeu.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {

    lateinit var binding: FragmentMypageBinding
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMypageBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        var myRestaurantList = RestaurantAdapter.myPageRestaurantList

        fun setValues() {
            val adapter = MyPageAdapter(requireContext(), myRestaurantList)
            binding.lvMypage.adapter = adapter
        }

        setValues()

        binding.mypageLoginTv.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    fun mypageLogin() {
        binding.mypageLoginTv.visibility = View.GONE
        binding.mypageLogoutTv.visibility = View.VISIBLE
    }

    fun mypageLogout() {
        binding.mypageLoginTv.visibility = View.VISIBLE
        binding.mypageLogoutTv.visibility = View.GONE
    }

}