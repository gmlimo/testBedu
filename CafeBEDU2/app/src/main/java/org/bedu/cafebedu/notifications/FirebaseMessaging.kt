package org.bedu.cafebedu.notifications

import com.google.firebase.messaging.FirebaseMessagingService

class FirebaseMessaging: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}