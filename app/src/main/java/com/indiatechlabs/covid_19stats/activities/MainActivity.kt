package com.indiatechlabs.covid_19stats.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.indiatechlabs.covid_19stats.R
import com.indiatechlabs.covid_19stats.adapter.RecyclerAdapter
import com.indiatechlabs.covid_19stats.model.MainModel

class MainActivity : AppCompatActivity() {

    lateinit var countryName : EditText
    lateinit var search : Button
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = arrayListOf<MainModel>()

        countryName = findViewById(R.id.etCountry)
        search = findViewById(R.id.btnSearch)
        recyclerView = findViewById(R.id.recyclerView)

        val queue = Volley.newRequestQueue(this)
        val queue2 = Volley.newRequestQueue(this)
        val url1 = "https://api.covid19api.com/summary"

        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url1, null,
            Response.Listener {
                val countries = it.getJSONArray("Countries")
                for(i in 0 until countries.length()){
                    val jsonObject = countries.getJSONObject(i)
                    val name = jsonObject.getString("Country")
                    var flagUrl = "www.google.com"
                    val url2 = "https://restcountries.eu/rest/v2/name/$name?fullText=true"
                    val jsonObjectRequest1 = object : JsonObjectRequest(Request.Method.GET, url2, null,
                        Response.Listener { it ->
                            val jsonArray = it.getJSONArray("")
                            for(j in 0 until jsonArray.length()){
                                val jsonObject1 = jsonArray.getJSONObject(j)
                                flagUrl = jsonObject1.getString("flag")
                            }
                        },
                        Response.ErrorListener {
                            Toast.makeText(this, "Flag Url not loaded", Toast.LENGTH_SHORT).show()
                        }
                    )
                    {

                    }
                    queue2.add(jsonObjectRequest1)
                    val items = MainModel(
                        name,
                        jsonObject.getInt("NewConfirmed"),
                        jsonObject.getInt("TotalConfirmed"),
                        jsonObject.getInt("NewDeaths"),
                        jsonObject.getInt("TotalDeaths"),
                        jsonObject.getInt("NewRecovered"),
                        jsonObject.getInt("TotalRecovered"),
                        flagUrl
                    )
                    itemList.add(items)
                    recyclerView.adapter = RecyclerAdapter(this, itemList)
                    recyclerView.layoutManager = LinearLayoutManager(this)
//                    recyclerView.addItemDecoration(
//                        DividerItemDecoration(this, LinearLayoutManager(this).orientation)
//                    )
                }
            },
            Response.ErrorListener {
                Toast.makeText(this, "Service Error", Toast.LENGTH_SHORT).show()
            }
        )
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Access-Token"] = "5cf9dfd5-3449-485e-b5ae-70a60e997864"
                return headers
            }
        }
        queue.add(jsonObjectRequest)
    }
}