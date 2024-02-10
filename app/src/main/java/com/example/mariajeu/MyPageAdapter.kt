package com.example.mariajeu

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MyPageAdapter(private val context: Context, private val myPageList: ArrayList<ReservedRestaurant>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_list_mypage, parent, false)

            val mypageRestaurant = getItem(position) as ReservedRestaurant
            val mypageRestaurantName = view!!.findViewById<TextView>(R.id.tv_mypage_name)
            val mypageRestaurantTime = view!!.findViewById<TextView>(R.id.mypage_time)

            mypageRestaurantName.text = mypageRestaurant.restaurantName
            when (mypageRestaurant.reservedTime) {
                1 -> mypageRestaurantTime.text = " - 오후 6:00"
                2 -> mypageRestaurantTime.text = " - 오후 6:30"
                3 -> mypageRestaurantTime.text = " - 오후 7:00"
                4 -> mypageRestaurantTime.text = " - 오후 7:30"
                5 -> mypageRestaurantTime.text = " - 오후 8:00"
            }

            val restaurantImg = view.findViewById<ImageView>(R.id.lv_mypage_img)
            val btnCancel = view.findViewById<Button>(R.id.btn_cancel_reservation)

            btnCancel.setOnClickListener {
                //TODO 예약 취소 버튼 눌렀을 때 이벤트 처리
            }

        }

        return view
    }

    override fun getCount(): Int {
        return myPageList.size
    }

    override fun getItem(p0: Int): Any {
        return myPageList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

}