package com.pigo.spicehub.util

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader


object CsvUtils {

    fun readCsvDataFromAssets(context: Context, fileName: String): List<Array<String>> {
        val result = mutableListOf<Array<String>>()
        context.assets.open(fileName).use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                var line: String? = reader.readLine()
                while (line != null) {
                    val data = line.split(",").toTypedArray()
                    result.add(data)
                    line = reader.readLine()
                }
            }
        }
        return result
    }
}
