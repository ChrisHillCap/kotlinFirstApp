package com.example.foobar

import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONStringer
import org.json.JSONTokener
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun helper_returns_list_JsonObjects() {
        val testList: List<JSONObject>? = mutableListOf(JSONObject("{}"), JSONObject("{}"))
        val testData: JSONArray = JSONArray("[{\"foo\": \"bar\"},{\"wizz\":\"bang\"}]")
        println("////////"  + testData)
        val res = Helpers.arrayToListOfJsObjectNullable(testData)
        println("//////////"  + res)
        assertTrue(res.isNullOrEmpty())
    }

}