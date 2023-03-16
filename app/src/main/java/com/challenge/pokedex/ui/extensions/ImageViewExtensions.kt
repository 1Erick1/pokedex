package com.challenge.pokedex.ui.extensions

import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.io.File

fun ImageView.loadUrl(url: String){
    Log.d("XXX","Loading url $url")
    Picasso.get()
        .load(url)
        .into(this)
}

fun ImageView.loadFile(filePath: String){
    Log.d("XXX","Loading path $filePath")
    Picasso.get()
        .load(File(filePath))
        .into(this)
}