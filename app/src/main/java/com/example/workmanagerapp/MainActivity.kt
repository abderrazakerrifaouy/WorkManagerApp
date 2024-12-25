package com.example.workmanagerapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var workManager : WorkManager
    private lateinit var workRequest : WorkRequest
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        workManager = WorkManager.getInstance(this)
        workRequest = PeriodicWorkRequest.Builder(FileReadWork::class ,  15 , TimeUnit.MINUTES).build()

    }

    public  fun startWork(v : View){
        workManager.enqueue(workRequest)
    }
    public  fun stopWork(v : View){
        workManager.cancelWorkById(workRequest.id)
    }
}