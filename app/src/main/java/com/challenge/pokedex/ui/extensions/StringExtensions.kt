package com.challenge.pokedex.ui.extensions

fun String.capitalizeFirst(): String{
    return this.replaceFirstChar { it.uppercase() }
}