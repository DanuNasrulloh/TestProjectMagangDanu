package com.example.app.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import model.Riwayat
import java.io.IOException

object JsonHelper {
    fun loadRiwayatFromAssets(context: Context): List<Riwayat> {
        val json: String
        try {
            json = context.assets.open("riwayat.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val listType = object : TypeToken<List<Riwayat>>() {}.type
        return Gson().fromJson(json, listType)
    }
}
