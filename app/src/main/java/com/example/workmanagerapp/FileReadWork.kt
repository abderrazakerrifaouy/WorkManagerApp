package com.example.workmanagerapp

import android.content.ContentValues.TAG
import android.content.Context
import android.nfc.Tag
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class FileReadWork(
    private var context: Context,
    private var workerParams: WorkerParameters
) : Worker(context, workerParams) {

private val TAG = "FileReadWork"


    override fun doWork(): Result {

    var stream: InputStream? = null


    try {
        stream = context.assets.open("test.txt")
        val reader = InputStreamReader(stream)
        val text = StringBuilder()
        val br = BufferedReader(reader)
        var line: String?
        while (br.readLine().also { line = it } != null) {
            text.append(line)
            text.append("\n")
            Log.d(TAG, line.toString())
        }
        br.close()
    } catch (e: IOException) {
        e.printStackTrace()
        Log.d(TAG, e.message.toString())
        return Result.failure()
    }
    return Result.success()
}


}
