package com.example.maps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter (private val categories:List<categories>):
        RecyclerView.Adapter<adapter.categoriesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.categoriesViewHolder {
        val itemView=LayoutInflater.from(parent.context)
            .inflate(R.layout.categories,parent,false)
        return categoriesViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: adapter.categoriesViewHolder, position: Int) {
        val categories=categories[position]
        holder.textcategories.text=categories.title
        holder.imagecategories.setImageResource(categories.image)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
    inner class categoriesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var imagecategories: ImageView=itemView.findViewById(R.id.ivc1)
        val textcategories: TextView=itemView.findViewById(R.id.tvc1)


    }

}