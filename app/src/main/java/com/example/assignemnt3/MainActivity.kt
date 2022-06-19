package com.example.assignemnt3

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.icu.text.CaseMap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID="channelID"
    val CHANNEL_NAME="channelName"
    val NOTIFICATION_ID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn:Button=findViewById(R.id.button)
        val txt:EditText=findViewById(R.id.etName)
        createNotificationChannel()

        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Notification")
            .setContentText(txt.text.toString())
             .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        val notificationManager=NotificationManagerCompat.from(this,)
        btn.setOnClickListener{
            notificationManager.notify(NOTIFICATION_ID,notification)
            callActivity()
        }
    }
    fun  createNotificationChannel(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
           val channel=NotificationChannel(CHANNEL_ID,CHANNEL_ID,
               NotificationManager.IMPORTANCE_DEFAULT).apply {

           }
            val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel( channel)
           }
    }
    private fun callActivity() {
        val edittext:EditText=findViewById(R.id.etName)
        val msg=edittext.text.toString()

        val intent=Intent(this,MainActivity2::class.java).also{
            it.putExtra("EXTRA_MESSAGE",msg)
            startActivity(it)
        }
    }

}