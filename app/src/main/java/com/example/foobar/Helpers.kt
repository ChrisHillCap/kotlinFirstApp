package com.example.foobar

import org.json.JSONArray
import org.json.JSONObject

object Helpers {

     fun arrayToListOfJsObjectNullable (json: JSONArray): List<JSONObject>? {
        var listOfJsObjects = mutableListOf<JSONObject>()
        val res: List<JSONObject>? =  try {
            for (i: Int in 0..json.length() - 1) {
                listOfJsObjects.add(i,
                    JSONObject(json.get(i).toString())
                )
            }
           val s = listOfJsObjects
            println("/////" + s)
            return s
        } catch (ex: Exception) {
            println("Array did not contain all JSON objects, exception message: " + ex.message)
            null
        }

        return res
    }
}