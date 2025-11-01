package com.example.cyoapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var rvHeroes: RecyclerView
    private val heroes = mutableListOf<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rvHeroes)
        val adapter = HeroAdapter(heroes)
        rvHeroes.adapter = adapter
        rvHeroes.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()
        val url = "https://rickandmortyapi.com/api/character"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val results = json.jsonObject.getJSONArray("results")
                for (i in 0 until results.length()) {
                    val obj = results.getJSONObject(i)
                    val name = obj.getString("name")
                    val status = obj.getString("status")
                    val image = obj.getString("image")
                    heroes.add(Hero(name, status, image))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.e("API_ERROR", "Failed: $response")
            }
        })
    }
}
