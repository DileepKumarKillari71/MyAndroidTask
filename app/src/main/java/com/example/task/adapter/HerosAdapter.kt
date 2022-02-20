package com.example.task.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.task.pojo.Hero
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.task.R
import android.widget.TextView
import java.util.ArrayList

class HerosAdapter(var context: Context) : RecyclerView.Adapter<HerosAdapter.ViewHolder>() {
    var list: List<Hero> = ArrayList()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.charName.text = list[position].charName
        holder.name.text = list[position].name
        Glide.with(context).load(list[position].photo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val photoView = inflater.inflate(R.layout.layout_adapter, parent, false)
        return ViewHolder(photoView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    // update list data in recyclerview
    fun setData(list: List<Hero>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photo: ImageView
        var charName: TextView
        var name: TextView

        init {
            photo = itemView.findViewById<View>(R.id.photo) as ImageView
            name = itemView.findViewById<View>(R.id.name) as TextView
            charName = itemView.findViewById<View>(R.id.charName) as TextView
        }
    }
}