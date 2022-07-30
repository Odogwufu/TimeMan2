package com.example.timeman

import android.app.NotificationChannel
import androidx.appcompat.app.AppCompatActivity


class MainActivity4 : AppCompatActivity() {
//    lateinit var notificationChannel: NotificationChannel
//    lateinit var notificationManager: NotificationManager
//    lateinit var builder: Notification.Builder
//    private val channelId = "12345"
//    private val description = "Test Notification"
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as
//                NotificationManager
//    }
//    fun btnNotify(view: View) {
//        val intent = Intent(this, LauncherActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            notificationChannel = NotificationChannel(channelId, description, NotificationManager .IMPORTANCE_HIGH)
//            notificationChannel.lightColor = Color.BLUE notificationChannel.enableVibration(true)
//            notificationManager.createNotificationChannel(notificationChannel)
//            builder = Notification.Builder(this, channelId).setContentTitle("NOTIFICATION USING " +
//                    "KOTLIN").setContentText("Test Notification").setSmallIcon(R.drawable .ic_brightness).setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable
//                .ic_launcher_background)).setContentIntent(pendingIntent)
//        }
//        notificationManager.notify(12345, builder.build())
//    }

}