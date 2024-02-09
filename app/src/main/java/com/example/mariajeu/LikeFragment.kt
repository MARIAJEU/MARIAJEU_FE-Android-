package com.example.mariajeu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mariajeu.databinding.FragmentLikeBinding

class LikeFragment : Fragment() {
    private lateinit var binding: FragmentLikeBinding
    private lateinit var mainActivity: MainActivity

    companion object {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLikeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeBinding.inflate(inflater, container, false)
        var likeRestaurantList = RestaurantAdapter.likeRestaurantList

        // [setValues] RestaurantAdapter에서 받아온 정보를 바탕으로 찜한 매장 보여주기
        fun setValues() {
            val adapter = LikeAdapter(requireContext(), likeRestaurantList)
            binding.lvLike.adapter = adapter

            if (likeRestaurantList.size == 0) {
                binding.tvLikeNone.visibility = View.VISIBLE
                binding.tvLikeNone2.visibility = View.VISIBLE
            } else {
                binding.tvLikeNone.visibility = View.GONE
                binding.tvLikeNone2.visibility = View.GONE
            }

            // TODO 매장 개수 likeFragment 내에서 바로바로 연동이 안 됨 -> 다른 fragment로 전환 후 확인해야 연동이 됨.. 수정 필요!!
            var cntLikeRestaurant = adapter.count
            binding.tvCntLikeRestaurant.text = cntLikeRestaurant.toString() + "개의 매장"
        }

        setValues()

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    fun updateLikeRestaurantList(likeList: ArrayList<Restaurant>) {
        RestaurantAdapter.likeRestaurantList.clear()
        RestaurantAdapter.likeRestaurantList.addAll(likeList)
    }

}