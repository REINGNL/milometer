package com.example.millometeradmin

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.millometeradmin.adapter.AddMillsActivity
import com.example.millometeradmin.adapter.MillAdapter
import com.example.millometeradmin.adapter.MillData
import com.example.millometeradmin.adapter.UpdateMillsActivity


class MainActivity : AppCompatActivity(), GetMills.OnDataFetchedListener {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.milllistpage)

        val millService = GetMills(this)
        millService.execute()

        val addButton: ImageButton = findViewById(R.id.addButton)

        addButton.setOnClickListener {
            // Create an Intent to navigate to AddMillsActivity
            val intent = Intent(this, AddMillsActivity::class.java)

            // Start the new activity
            startActivity(intent)
        }
    }

    override fun onDataFetched(data: List<MillData>) {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = MillAdapter(data,
            onItemClick = { selectedMillData ->
                // Handle item click
                navigateToMillDetail(selectedMillData.millId)
            },
            onOptionsClick = { selectedMillData, anchorView ->
                // Handle options click
                showOptionsPopupMenu(selectedMillData, anchorView)
            })

        recyclerView.adapter = adapter
    }

    private fun showOptionsPopupMenu(selectedMillData: MillData, anchorView: View) {
        // Implement your logic to show a popup menu with "Edit" and "Delete" options
        // You can use PopupMenu or create a custom dialog
//        val popupMenu = PopupMenu(this, anchorView)
//        popupMenu.menuInflater.inflate(R.menu.menu_item, popupMenu.menu)
//
//        popupMenu.setOnMenuItemClickListener { item ->
//            when (item.itemId) {
//                R.id.action_edit -> {
//                    navigateToUpdateMills(selectedMillData.millId)
//                    true
//                }
//                R.id.action_delete -> {
//                    // Handle delete option
//                    Toast.makeText(this, "Delete Clicked: ${selectedMillData.millId}", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                else -> false
//            }
//        }

        // Show the popup menu below the anchor view
//        popupMenu.show()
    }

    private fun navigateToUpdateMills(millId: String) {
        // Create an Intent to navigate to UpdateMillsActivity
        val intent = Intent(this, UpdateMillsActivity::class.java)
        // Pass the millId to UpdateMillsActivity
        intent.putExtra("PiID", millId)
        // Start the new activity
        startActivity(intent)
    }

    private fun navigateToMillDetail(millId: String) {
        // Create an Intent to navigate to MillDetailActivity (replace with your actual MillDetailActivity)
//        val intent = Intent(this, MillDetailActivity::class.java)
//
//        // Pass the millId to MillDetailActivity
//        intent.putExtra("PiID", millId)
//
//        // Start the new activity
//        startActivity(intent)
//    }
}
}
