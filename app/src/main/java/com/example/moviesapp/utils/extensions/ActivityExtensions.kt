package com.example.moviesapp.utils.extensions

import android.app.Activity
import com.example.moviesapp.R
import com.tapadoo.alerter.Alerter


fun Activity.showError(text: String) {
    Alerter.create(this)
        .setText(text)
        .setBackgroundColorRes(
            R.color.error_color
        )
        .enableSwipeToDismiss()
        .showIcon(false)
        .show()
}