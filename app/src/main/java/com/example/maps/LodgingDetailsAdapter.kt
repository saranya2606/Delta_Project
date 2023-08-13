package com.example.maps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LodgingDetailsAdapter(private var lodgingDetails: LodgingDetails) :
    RecyclerView.Adapter<LodgingDetailsAdapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val reviewTextView: TextView = itemView.findViewById(R.id.reviewTextView) // Example, replace with your TextView ID
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_review, parent, false) // Replace with your item layout
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val review = lodgingDetails.reviews[position]
            holder.reviewTextView.text = review
        }

        override fun getItemCount(): Int {
            return lodgingDetails.reviews.size
        }

    fun updateData(newData: LodgingDetails) {
        lodgingDetails = newData
        notifyDataSetChanged()
    }
}