package com.example.moviesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ItemFilterLayoutBinding

class FiltersRecyclerViewAdapter(private val items: MutableList<String>):RecyclerView.Adapter<FiltersRecyclerViewAdapter.FiltersViewHolder>() {


    inner class FiltersViewHolder(private val binding: ItemFilterLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(){
            binding.filterTxt.text = items[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FiltersViewHolder(
        ItemFilterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size
}