package com.ioinsiders.marvelheroes.helpers

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(uri: String, placeholder: Int? = null) {
    var requestOptions = Glide.with(this)
        .load(uri)
        .fitCenter()
    placeholder?.let { requestOptions = requestOptions.placeholder(it) }
    requestOptions.into(this)
}