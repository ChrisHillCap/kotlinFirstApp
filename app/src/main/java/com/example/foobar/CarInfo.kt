package com.example.foobar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_car_info.*

class CarInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_info)
        val valueOfCarInfo: String = intent.getStringExtra("carInfo")
        carInfoViewer.setText(valueOfCarInfo)
    }



}
