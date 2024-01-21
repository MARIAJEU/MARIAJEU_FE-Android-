package com.example.mariajeu

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mariajeu.databinding.ListviewListItemBinding

class RestaurantAdapter(private val context: Context, private val restaurantList: ArrayList<Restaurant>): BaseAdapter(), RestaurantTimeDialogInterface {

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

        val restaurantImg = view.findViewById<ImageView>(R.id.lv_item_img)
        val corkCharge = view.findViewById<Button>(R.id.btn_listview_cork_charge)
        val restaurantDescription = view.findViewById<TextView>(R.id.tv_listview_description)
        val restaurantRate = view.findViewById<TextView>(R.id.tv_listview_rating)
        val restaurantReview = view.findViewById<TextView>(R.id.tv_listview_review)
        val restaurantPrice = view.findViewById<TextView>(R.id.tv_listview_price)
        val restaurantLocation = view.findViewById<TextView>(R.id.tv_listview_location)

        // * 버튼 *//
        val restaurantTime1 = view.findViewById<Button>(R.id.listview_time1)
        val restaurantTime2 = view.findViewById<Button>(R.id.listview_time2)
        val restaurantTime3 = view.findViewById<Button>(R.id.listview_time3)
        val restaurantTime4 = view.findViewById<Button>(R.id.listview_time4)
        val restaurantTime5 = view.findViewById<Button>(R.id.listview_time5)

        restaurantTime1.setOnClickListener {
            showCustomDialog(restaurant)
        }

        restaurantTime2.setOnClickListener {
            showCustomDialog(restaurant)
        }
        restaurantTime3.setOnClickListener {
            showCustomDialog(restaurant)
        }
        restaurantTime4.setOnClickListener {
            showCustomDialog(restaurant)
        }
        restaurantTime5.setOnClickListener {
            showCustomDialog(restaurant)
        }


        return view
    }

    private fun showCustomDialog(restaurant: Restaurant) {

        val customDialog = RestaurantTimeDialog(context, this)

        val tvName = customDialog.findViewById<TextView>(R.id.tv_dialog_name)
        val tvDate = customDialog.findViewById<TextView>(R.id.tv_dialog_date)
        val tvTime = customDialog.findViewById<TextView>(R.id.tv_dialog_time)
        val tvPersonnel = customDialog.findViewById<TextView>(R.id.tv_dialog_cnt_personnel)

//        Log.d("TEST 네임", tvName.toString())

//        tvName.text = restaurant.restaurantName

        // TODO 날짜, 시간, 인원수 연동해서 tvDate.text = ... 작성

        customDialog.show()
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

    override fun onYesButtonClick(id: Int) {
        // 확인 버튼이 눌렸을 때의 동작 처리
        // 여기에서 필요한 로직을 추가하세요.
    }

    override fun onCancelButtonClick() {
        // 취소 버튼이 눌렸을 때의 동작 처리
        // 여기에서 필요한 로직을 추가하세요.
    }


}