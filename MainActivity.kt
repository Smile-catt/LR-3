package com.example.lr

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/Smile-catt/moskjson/db/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.fetchAllUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                d("marsuret", "onResponse: ${response.body()!![0].manufacturer}" )
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
               d("m arsuret", "onFailure")
            }
        })


        val users = mutableListOf<User>()
        for (i in 0..100) {
            users.add(
                User(
                    "Marsuret",
                    "Italy",
                    "Marsuret",
                    "11.5%",
                    "White",
                    "Dry",
                    "1 034 ru"
                )
            )
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = UserAdapter(users)
        }
    }
}
