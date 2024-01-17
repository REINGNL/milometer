package com.example.millometeradmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.millometeradmin.adapter.UserAdapter
import com.example.millometeradmin.adapter.UserData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FetchMillData.OnDataFetchedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fetchMillData = FetchMillData(this)
        fetchMillData.execute()


        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    override fun onDataFetched(data: List<UserData>) {

        val adapter = UserAdapter(data)
        recyclerView.adapter = adapter
    }
}