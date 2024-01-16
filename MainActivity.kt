package com.example.millometeradmin;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.millometeradmin.adapter.DataModel
import com.example.millometeradmin.adapter.ItemAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchData()
    }

    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://asia-south1.gcp.data.mongodb-api.com/app/application-0-gacpq/endpoint/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getUsers()
                withContext(Dispatchers.Main) {
                    Log.d("API Response", response.toString()) // Log the API response
                    itemAdapter = ItemAdapter(this@MainActivity, response)
                    recyclerView.adapter = itemAdapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("API Error", e.message ?: "Unknown error") // Log the error
            }
        }
    }


    interface ApiService {
    @GET("getusers")
    suspend fun getUsers(): List<DataModel>
}}