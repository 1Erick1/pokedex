package com.challenge.pokedex.data.datasource.local.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream


class ImageDownloader(
    private val context: Context,
    private val okHttpClient: OkHttpClient
) {
    suspend fun downloadImage(id: String, remoteUrl: String?): String? {
        var path: String?
        return withContext(Dispatchers.IO){
            try {
                val req = Request.Builder().url(remoteUrl?:"").build()
                val response = okHttpClient.newCall(req).execute()
                val bitmap =   BitmapFactory.decodeStream(
                    response.body()?.byteStream(),
                    null,
                    BitmapFactory.Options()
                        .apply {
                            inPreferredConfig = Bitmap.Config.ARGB_8888
                        }
                )

                val root = ContextCompat.getExternalFilesDirs(context, Environment.DIRECTORY_PICTURES)[0]
                var myDir = File("$root/thumbnails")
                if (!myDir.exists()) {
                    myDir.mkdirs()
                }
                val name = "$id.png"
                myDir = File(myDir, name)
                val out = FileOutputStream(myDir)
                bitmap?.compress(Bitmap.CompressFormat.PNG, 100, out)
                out.flush()
                out.close()
                path = myDir.absolutePath
            } catch (e: Exception) {
                e.printStackTrace()
                path=null
            }
            path
        }
    }
}