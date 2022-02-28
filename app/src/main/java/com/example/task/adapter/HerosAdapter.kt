package com.example.task.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.task.pojo.Hero
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.task.databinding.LayoutAdapterBinding
import java.util.ArrayList

class HerosAdapter(var context: Context) : RecyclerView.Adapter<HerosAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    var list: List<Hero> = ArrayList()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.charName.text = list[position].charName
        holder.binding.name.text = list[position].name
        Glide.with(context).load(list[position].photo)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.photo)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        var binding = LayoutAdapterBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    // update list data in recyclerview
    fun setData(list: List<Hero>) {
        this.list = list
        notifyDataSetChanged()
    }
}