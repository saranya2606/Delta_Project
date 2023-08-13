package com.example.maps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class popularadapter (private val popular:List<popular>):
    RecyclerView.Adapter<popularadapter.popularViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): popularadapter.popularViewHolder {
        val itemView=LayoutInflater.from(parent.context)
            .inflate(R.layout.popular,parent,false)
        return popularViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: popularadapter.popularViewHolder, position: Int) {
        val popular=popular[position]
        holder.title.text=popular.title
        holder.desc.text=popular.desc
        holder.imagePopular.setImageResource(popular.image)
    }

    override fun getItemCount(): Int {
        return popular.size
    }
    inner class popularViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var imagePopular: ImageView=itemView.findViewById(R.id.imagep)
        val title: TextView=itemView.findViewById(R.id.titletv)
        val desc: TextView=itemView.findViewById(R.id.desctv)


    }

}