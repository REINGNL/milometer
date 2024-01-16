package com.example.millometeradmin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.millometeradmin.R

class ItemAdapter(private val context: Context, private val userList: List<DataModel>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView = view.findViewById(R.id.idTextView)
        val phoneNumTextView: TextView = view.findViewById(R.id.phoneNumTextView)
        val roleTextView: TextView = view.findViewById(R.id.roleTextView)
        val millTextView: TextView = view.findViewById(R.id.millTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.idTextView.text = "id: ${user._id.oid}"
        holder.phoneNumTextView.text = "phonenum: ${user.phoneNum}"
        holder.roleTextView.text = "role: ${user.role}"

        // Handle Mills information
        val millsInfo = StringBuilder("mill:\n")
        for (millsList in user.mills) {
            for (mill in millsList) {
                millsInfo.append("  piID: ${mill.PiID}\n")

                // Handle Thresholds information
                for (threshold in mill.thresholds) {
                    millsInfo.append("thresholds:\n")
                    millsInfo.append("parameter: ${threshold.parameter}\n")
                    millsInfo.append("value: ${threshold.value}\n")
                }

                millsInfo.append("\n")
            }
        }
        holder.millTextView.text = millsInfo.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
