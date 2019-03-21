package com.example.foobar

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class Car(val id:Int, val model:String) {
}

class MainActivity : MainActivityI() {

    override val fuel:Fuel = Fuel
    override val host: String = "http://10.0.2.2:9001"
}
abstract class MainActivityI : AppCompatActivity() {
    abstract val host:String
    abstract val fuel:Fuel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonClickEventForExampleCar()
        buttonClickEventForViewInformationOnExampleCar()
        buttonToLoadMainAPI()

    }

     fun buttonToLoadMainAPI() {
        mainAPI.setOnClickListener {
            val intent = Intent(it.context, MainAPIFunctionality::class.java)
            startActivity(intent)
        }
    }

    private fun buttonClickEventForViewInformationOnExampleCar() {
        fun intentSender(intent:Intent, value:JSONObject):Intent {
            return intent.putExtra("carInfo",value.toString())
        }
        getInfo.setOnClickListener{
            val intent = Intent(it.context, CarInfo::class.java)

            fuel.get("$host/getCarInfo/1").appendHeader("Host","localhost").response{ request, response, result ->
            val jsonObj = result.fold<JSONObject>({success ->
                    println("successful response from API:" + String(success))
                JSONObject(String(success))
                }, { failure ->
                    println("failed api call" + failure.message)
                    JSONObject("{}")
                })

               val intentUpdated = intentSender(intent, jsonObj)
                startActivity(intentUpdated)
            }

        }
    }

    private fun buttonClickEventForExampleCar() {
        getCar.setOnClickListener {

            fuel.get("$host/getCar").appendHeader("Host","localhost").response { request, response, result ->

                val carFromAPI: Car? = result.fold<Car?>({ success ->
                    println("successful response from API:"  + String(success))

                    val bodyOfResult = JSONObject(String(success))

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
