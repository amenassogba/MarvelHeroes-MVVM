package com.ioinsiders.marvelheroes.helpers

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun ImageView.load(uri: Uri, placeholder: Int? = null) {
    var requestOptions = Glide.with(this)
            .load(uri)
            .centerCrop()
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.DATA)

    placeholder?.let { requestOptions = requestOptions.placeholder(it) }
    requestOptions.into(this)
}