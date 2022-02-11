package com.example.moviesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ItemFilterLayoutBinding

typealias OnFilterClick = (position: Int) -> Unit

class FiltersRecyclerViewAdapter(private val items: MutableList<String>) :
    RecyclerView.Adapter<FiltersRecyclerViewAdapter.FiltersViewHolder>() {


    private var currentFilterPosition: Int = 0
    lateinit var onDrawableClick: OnFilterClick

    inner class FiltersViewHolder(private val binding: ItemFilterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind() {
            binding.filterTxt.text = items[absoluteAdapterPosition]
            binding.root.setOnClickListener(this)

            if (currentFilterPosition == absoluteAdapterPosition) {
                binding.rootLayout.isSelected = true
                binding.filterTxt.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.white
                    )
                )
            }else{
                binding.rootLayout.isSelected = false
                binding.filterTxt.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.filter_txt_clr
                    )
                )
            }
        }

        override fun onClick(p0: View?) {
            currentFilterPosition = absoluteAdapterPosition
            notifyDataSetChanged()
            onDrawableClick.invoke(currentFilterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FiltersViewHolder(
        ItemFilterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size
}