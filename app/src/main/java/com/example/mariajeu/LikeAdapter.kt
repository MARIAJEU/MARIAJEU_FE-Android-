package com.example.mariajeu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class LikeAdapter(private val context: Context, private val likeList: ArrayList<RestaurantDTO>): BaseAdapter() {
    private lateinit var lName: LikeRestaurant
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_list_like, parent, false)

            val likeRestaurant = getItem(position) as RestaurantDTO
            val likeRestaurantName = view!!.findViewById<TextView>(R.id.tv_listview_name)
            likeRestaurantName.text = likeRestaurant.restaurantName

            val restaurantImg = view.findViewById<ImageView>(R.id.lv_item_img)
            val corkCharge = view.findViewById<Button>(R.id.btn_listview_cork_charge)
            val restaurantDescription = view.findViewById<TextView>(R.id.tv_listview_description)
            val restaurantRate = view.findViewById<TextView>(R.id.tv_listview_rating)
            val restaurantReview = view.findViewById<TextView>(R.id.tv_listview_review)
            val restaurantPrice = view.findViewById<TextView>(R.id.tv_listview_price)
            val restaurantLocation = view.findViewById<TextView>(R.id.tv_listview_location)

            val tvLikeNone = view.findViewById<TextView>(R.id.tv_like_none)
            val tvLikeNone2 = view.findViewById<TextView>(R.id.tv_like_none2)

        }

        return view
    }

    override fun getCount(): Int {
        return likeList.size
    }

    override fun getItem(p0: Int): Any {
        return likeList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

}