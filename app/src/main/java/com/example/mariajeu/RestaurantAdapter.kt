package com.example.mariajeu

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

class RestaurantAdapter(private val context: Context, private val restaurantList: ArrayList<Restaurant>): BaseAdapter(), RestaurantTimeDialogInterface {

    // postion에 위치한 데이터를 화면에 출력하는 데 사용되는 view를 리턴해줌
    private lateinit var rName: Restaurant
    private var rTimeIdx by Delegates.notNull<Int>()
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
            rName = restaurant
            rTimeIdx = 1
            showRestaurantDialog(restaurant, 1)
        }

        restaurantTime2.setOnClickListener {
            rName = restaurant
            rTimeIdx = 2
            showRestaurantDialog(restaurant, 2)
        }
        restaurantTime3.setOnClickListener {
            rName = restaurant
            rTimeIdx = 3
            showRestaurantDialog(restaurant, 3)
        }
        restaurantTime4.setOnClickListener {
            rName = restaurant
            rTimeIdx = 4
            showRestaurantDialog(restaurant, 4)
        }
        restaurantTime5.setOnClickListener {
            rName = restaurant
            rTimeIdx = 5
            showRestaurantDialog(restaurant, 5)
        }

        // -------------------------------------------------------------------------------

        // TODO 매장 detail 페이지로 이동 -> 왜 requireActivity() 안 되는지 알아낼 것
//         매장 detail 페이지로 이동

        restaurantImg.setOnClickListener {
            val restaurantDetailFragment = RestaurantDetailFragment()
            val appCompatActivity = context as? AppCompatActivity

            val transaction = appCompatActivity?.supportFragmentManager?.beginTransaction()
            transaction?.setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            transaction?.replace(R.id.fragment_restaurant_detail, restaurantDetailFragment)

            appCompatActivity?.supportFragmentManager?.addOnBackStackChangedListener {
                Log.d("FragmentManager", "BackStackChanged")
            }
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        return view
    }

    private fun showRestaurantDialog(restaurant: Restaurant, idx: Int) {

        val restaurantTimeDialog = RestaurantTimeDialog(context, this)
        Log.d("TEST 네임", restaurant.restaurantName)

        // 다이얼로그에 해당 아이템의 제목을 표시하도록 설정
        RestaurantTimeDialog.rName.text = restaurant.restaurantName

        when (idx) {
            1 -> RestaurantTimeDialog.rTime.text = "오후 6:00"
            2 -> RestaurantTimeDialog.rTime.text = "오후 6:30"
            3 -> RestaurantTimeDialog.rTime.text = "오후 7:00"
            4 -> RestaurantTimeDialog.rTime.text = "오후 7:30"
            5 -> RestaurantTimeDialog.rTime.text = "오후 8:00"
        }

        // TODO 날짜, 시간, 인원수 연동해서 tvDate.text = ... 작성


        restaurantTimeDialog.show()
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
        showReservationDialog(rName, rTimeIdx)

    }

    override fun onCancelButtonClick() {
        // 취소 버튼이 눌렸을 때의 동작 처리
        // 여기에서 필요한 로직을 추가하세요.
    }

    private fun showReservationDialog(restaurant: Restaurant, idx: Int) {

        // Dialog 객체 생성
        val reservationDialog = ReservationDialog(context, this)

        // 다이얼로그에 해당 아이템의 제목을 표시하도록 설정
        ReservationDialog.rName.text = restaurant.restaurantName

        when (idx) {
            1 -> ReservationDialog.rTime.text = "오후 6:00"
            2 -> ReservationDialog.rTime.text = "오후 6:30"
            3 -> ReservationDialog.rTime.text = "오후 7:00"
            4 -> ReservationDialog.rTime.text = "오후 7:30"
            5 -> ReservationDialog.rTime.text = "오후 8:00"
        }

        // Dialog를 화면에 표시
        reservationDialog.show()
    }




}
