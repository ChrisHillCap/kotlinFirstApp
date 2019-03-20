package com.example.foobar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class Car(val id:Int, val model:String) {
}
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonClickEvent()

    }

    private fun buttonClickEvent() {
        getCar.setOnClickListener {
            Fuel.get("http://10.0.2.2:9001/getCar").response { request, response, result ->

                val carFromAPI: Car? = result.fold<Car?>({ success ->
                    println("//" + String(success))
                    val bodyOfResult = JSONObject(String(success))
                    println(bodyOfResult)
                    getIdAndModelFromJson(bodyOfResult)
                }, { e: Exception ->
                    println("Something went wrong on the call to getCar" + e.message)
                    println(e.stackTrace)
                    null
                })
                updateView(carFromAPI)
            }
        }
    }


    private fun updateView(car: Car?) {
        runOnUiThread(  Runnable({
                if (car != null) {
                    carId.setText(car.id.toString())
                    carModel.setText(car.model)
                }
            }))

    }

    private fun getIdAndModelFromJson(jsonObject: JSONObject): Car {
        val idOfCar = jsonObject.getInt("id")
        val modelOfCar = jsonObject.getString("model")
        return Car(idOfCar,modelOfCar)
    }


}
