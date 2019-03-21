package com.example.foobar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import org.json.JSONArray
import org.json.JSONObject


class MainAPIFunctionality : AppCompatActivity() {

    val fuel:Fuel = Fuel
    val host = "http://10.0.2.2:9001"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_apifunctionality)
        getListOfCarsOnLoad()
    }




    private fun getListOfCarsOnLoad(){
        fuel.get("$host/getListOfEntities").response{req,resp,res ->
            res.fold<List<JSONObject>?> ({ success ->
              val array = JSONArray(String(success))
                println("//////////////" + array)
                val listOfObj = Helpers.arrayToListOfJsObjectNullable(array)
                println("list of JsonObjects" + listOfObj.toString())
                listOfObj
            },{fail -> null})
        }
    }
}
