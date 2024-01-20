package com.example.mariajeu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class RestaurantAdapter(private val context: Context, private val restaurantList: ArrayList<Restaurant>): BaseAdapter() {

    // postion에 위치한 데이터를 화면에 출력하는 데 사용되는 view를 리턴해줌
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.listview_list_item, null)

//        // ArrayList<Restaurant>의 변수 restaurant의 이미지와 데이터를 ImageView와 TextView에 담음
//        val restaurant = restaurantList[p0]
//        val resourceId = context.resources.getIdentifier(restaurant.restaurantImg, "drawable", context.packageName)
//        restaurantImg.setImageResource(resourceId)

        var convertView = p1
        if (convertView == null) {
            convertView = LayoutInflater.from(p2?.context).inflate(R.layout.listview_list_item, p2, false)
        }
        val restaurantName = view.findViewById<TextView>(R.id.tv_listview_name)
//        val restaurantImg = view.findViewById<ImageView>(R.id.lv_item_img)
//        val corkCharge = view.findViewById<Button>(R.id.btn_listview_cork_charge)
//        val restaurantDescription = view.findViewById<TextView>(R.id.tv_listview_description)
//        val restaurantRate = view.findViewById<TextView>(R.id.tv_listview_rating)
//        val restaurantReview = view.findViewById<TextView>(R.id.tv_listview_review)
//        val restaurantPrice = view.findViewById<TextView>(R.id.tv_listview_price)
//        val restaurantLocation = view.findViewById<TextView>(R.id.tv_listview_location)

        return convertView
    }
    override fun getCount(): Int {
        return restaurantList.size
    }

    override fun getItem(p0: Int): Any {
        return restaurantList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }


}