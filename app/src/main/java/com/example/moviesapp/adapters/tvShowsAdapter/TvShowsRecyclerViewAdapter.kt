package com.example.moviesapp.adapters.tvShowsAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ItemShowLayoutBinding
import com.example.moviesapp.models.showsModels.ShowsResultModel
import com.example.moviesapp.utils.extensions.setImageUrl

typealias OnTvShowClick = (tvId: Int) -> Unit

class TvShowsRecyclerViewAdapter :
    PagingDataAdapter<ShowsResultModel, TvShowsRecyclerViewAdapter.MyViewHolder>(TvShowsComparator) {

    private var tvId: Int = 0
    lateinit var onDrawableClick: OnTvShowClick

    inner class MyViewHolder(private val binding: ItemShowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind() {
            val show = getItem(absoluteAdapterPosition)
            binding.showTitleTV.text = show?.name.toString()
            binding.yearTV.text = "(${show?.firstAirDate})"
            binding.showRatingNumTV.text = show?.voteAverage.toString()
            binding.showImgV.setImageUrl(show?.generatePosterUrl())
        }

        override fun onClick(p0: View?) {
            tvId = getItem(absoluteAdapterPosition)?.id!!
            notifyDataSetChanged()
            onDrawableClick.invoke(tvId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            ItemShowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }
}
