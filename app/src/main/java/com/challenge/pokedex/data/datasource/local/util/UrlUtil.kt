package com.challenge.pokedex.data.datasource.local.util

object UrlUtil {
    fun getIdFromUrl(url: String): String{
        return url.split("/".toRegex()).dropLast(1).last()
    }
}