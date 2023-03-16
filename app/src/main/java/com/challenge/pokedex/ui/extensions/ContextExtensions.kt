package com.challenge.pokedex.ui.extensions

import android.content.Context
import android.widget.Toast

fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, msg, duration).show()
}