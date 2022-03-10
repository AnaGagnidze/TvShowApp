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


    var currentFilterPosition: Int = 0
    lateinit var onDrawableClick: OnFilterClick


    companion object {
        private const val VIEW_TYPE_FILTER = 0
        private const val VIEW_TYPE_GENRE = 1
    }

    inner class FiltersViewHolder(private val binding: ItemFilterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bindGenres() {
            binding.filterTxt.text = items[absoluteAdapterPosition]
        }

        fun bindFilter() {
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
        if (getItemViewType(position) == VIEW_TYPE_FILTER) holder.bindFilter() else holder.bindGenres()

    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when {
            items[position] == "Popular" || items[position] == "Top Rated" ||
                    items[position] == "Airing Today" -> VIEW_TYPE_FILTER
            else -> VIEW_TYPE_GENRE
        }
    }
}