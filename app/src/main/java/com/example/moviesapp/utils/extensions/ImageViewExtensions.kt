package com.example.moviesapp.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.R
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable


private val shimmer =
    Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
        .setDuration(1800) // how long the shimmering animation takes to do one full sweep
        .setBaseAlpha(0.7f) //the alpha of the underlying children
        .setHighlightAlpha(0.6f) // the shimmer alpha amount
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

// This is the placeholder for the imageView
val shimmerDrawable = ShimmerDrawable().apply {
    setShimmer(shimmer)
}
fun ImageView.setImageUrl(url: String?) {
    if (!url.isNullOrEmpty())
        Glide.with(context)
            .load(url)
            .placeholder(shimmerDrawable)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(8)))
            .into(this)
    else
        setImageResource(R.drawable.ic_baseline_image_not_supported_24)
}