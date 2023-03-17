package com.challenge.pokedex.ui.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.io.File

fun ImageView.loadUrl(url: String){
    Picasso.get()
        .load(url)
        .into(this)
}

fun ImageView.loadFile(filePath: String){
    Picasso.get()
        .load(File(filePath))
        .into(this)
}