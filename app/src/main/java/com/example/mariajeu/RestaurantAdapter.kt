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
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView

//        // ArrayList<Restaurant>의 변수 restaurant의 이미지와 데이터를 ImageView와 TextView에 담음
//        val restaurant = restaurantList[p0]
//        val resourceId = context.resources.getIdentifier(restaurant.restaurantImg, "drawable", context.packageName)
//        restaurantImg.setImageResource(resourceId)

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_list_item, parent, false)
        }

        val restaurant = restaurantList[position]
        val restaurantName = view!!.findViewById<TextView>(R.id.tv_listview_name)
        restaurantName.text = restaurant.restaurantName

//        val restaurantImg = view.findViewById<ImageView>(R.id.lv_item_img)
//        val corkCharge = view.findViewById<Button>(R.id.btn_listview_cork_charge)
//        val restaurantDescription = view.findViewById<TextView>(R.id.tv_listview_description)
//        val restaurantRate = view.findViewById<TextView>(R.id.tv_listview_rating)
//        val restaurantReview = view.findViewById<TextView>(R.id.tv_listview_review)
//        val restaurantPrice = view.findViewById<TextView>(R.id.tv_listview_price)
//        val restaurantLocation = view.findViewById<TextView>(R.id.tv_listview_location)

        return view
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