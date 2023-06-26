package org.bedu.cafebedu.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import org.bedu.cafebedu.LoginActivity
import org.bedu.cafebedu.R
import org.bedu.cafebedu.notifications.NotificationApp

const val ACTION_RECEIVED = "action_received"
val GRUPO_SIMPLE = "GRUPO_SIMPLE"


@SuppressLint("MissingPermission")
fun simpleNotification(context: Context){
    with(context) {
        val builder = NotificationCompat.Builder(this, NotificationApp.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_donut_icon)
            .setColor(getColor(R.color.black))
            .setContentTitle(getString(R.string.simple_title))
            .setContentText(getString(R.string.simple_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(getString(R.string.simple_body))
            )
            .setGroup(GRUPO_SIMPLE)

        //lanzamos la notificación
        NotificationManagerCompat
            .from(this).run {
                notify(1, builder.build()) // en este caso pusimos un id genérico
            }
    }
}

@SuppressLint("MissingPermission")
fun touchNotification(activity: Activity) {
    //Un PendingIntent para dirigirnos a una actividad pulsando la notificación
    val intent = Intent(activity, LoginActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        activity,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(activity, NotificationApp.CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_donut_icon)
        .setContentTitle(activity.getString(R.string.action_login_title))
        .setContentText(activity.getString(R.string.action_body))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent) // se define aquí el content intend
        .setAutoCancel(true) // la notificación desaparece al dar click sobre ella

    with(NotificationManagerCompat.from(activity)) {
        notify(2, builder.build())
    }
}