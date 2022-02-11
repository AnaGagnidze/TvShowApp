package com.example.moviesapp.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.R

fun ImageView.setImageUrl(url: String?) {
    if (!url.isNullOrEmpty())
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(8)))
            .into(this)
    else
        setImageResource(R.drawable.ic_baseline_image_not_supported_24)
}