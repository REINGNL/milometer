package com.example.millometeradmin.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.millometeradmin.R


class MillAdapter(
    private val millList: List<MillData>,
    private val onItemClick: (MillData) -> Unit,
    private val onOptionsClick: (MillData, View) -> Unit
) : RecyclerView.Adapter<MillAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.milllist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val millItem = millList[position]

        holder.textMillId.text = millItem.millId
        holder.textMillStatus.text = millItem.status

        // Set click listeners
        holder.itemView.setOnClickListener {
            onItemClick.invoke(millItem)
        }

        holder.imageButtonOptions.setOnClickListener {
            onOptionsClick.invoke(millItem, holder.itemView)
        }
    }



    override fun getItemCount(): Int {
        return millList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textMillId: TextView = itemView.findViewById(R.id.textMillId)
        val textMillStatus: TextView = itemView.findViewById(R.id.textMillStatus)
        val imageButtonOptions: ImageButton = itemView.findViewById(R.id.imageButtonOptions)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(millList[position])
                }
            }

            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onOptionsClick(millList[position], itemView)
                    true
                } else {
                    false
                }
            }
            }
        }
}