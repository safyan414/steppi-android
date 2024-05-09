package com.steppi.steppifitness.fcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.ui.home.STHomeActivity
import com.steppi.steppifitness.utils.STPreference
import java.util.*

class STNotifyFireBaseMessagingService : FirebaseMessagingService() {
    companion object {
        private val TAG = "NotifyFCMService"

        /*
     * The Twilio Notify message data keys are as follows:
     *  "twi_body"   // The body of the message
     *  "twi_title"  // The title of the message
     *
     * You can find a more detailed description of all supported fields here:
     * https://www.twilio.com/docs/api/notifications/rest/notifications#generic-payload-parameters
     */
        private const val NOTIFY_TITLE_DATA_KEY = "twi_title"
        private const val NOTIFY_BODY_DATA_KEY = "twi_body"
        private const val NOTIFY_IMAGE_DATA_KEY = "image"
    }

    override fun onNewToken(refreshedToken: String) {
        super.onNewToken(refreshedToken)
        STPreference.saveDeviceId(applicationContext, refreshedToken)
    }

    override fun onMessageReceived(message: RemoteMessage) {
//        super.onMessageReceived(message)
        /*
         * The Notify service adds the message body to the remote message data so that we can
         * show a simple notification.
         */
        if (null == message.data /*|| null == STPreference.getUserId(this)*/)
            return
        val data = message.data
//        Log.e("message data", "message data : $data")

        val from = message.from
        val title = data[NOTIFY_TITLE_DATA_KEY]
        val body = data[NOTIFY_BODY_DATA_KEY]
        val image = data[NOTIFY_IMAGE_DATA_KEY]

        sendNotification(title, body, data)
        val broadCastIntent = Intent(STConstants.BROADCAST_NOTIFICATION_UPDATE)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(broadCastIntent)
    }

    private fun sendNotification(
        title: String?,
        message: String?,
        data: MutableMap<String, String>
    ) {
        val intent = Intent(this, STHomeActivity::class.java)
//        intent.action = Intent.ACTION_MAIN
//        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.putExtra(STConstants.IS_NOTIFICATION, true)
        intent.putExtra(STConstants.NOTIFICATION_TYPE, data[STConstants.NOTIFICATION_TYPE])
        intent.putExtra(
            STConstants.NOTIFICATION_CHALLENGE_ID,
            data[STConstants.NOTIFICATION_CHALLENGE_ID]
        )
        intent.putExtra(
            STConstants.NOTIFICATION_CHALLENGE_TYPE,
            data[STConstants.NOTIFICATION_CHALLENGE_TYPE]
        )
        intent.putExtra(
            STConstants.NOTIFICATION_REWARD_ID,
            data[STConstants.NOTIFICATION_REWARD_ID]
        )
        intent.putExtra(
            STConstants.NOTIFICATION_REWARD_TYPE,
            data[STConstants.NOTIFICATION_REWARD_TYPE]
        )
        intent.putExtra(
            STConstants.CHALLENGES_ISPRIVATE_NOTIFICATION,
            data[STConstants.CHALLENGES_ISPRIVATE_NOTIFICATION]
        )
        intent.putExtra(
            STConstants.PRIVATE_CHALLENGE_JOIN_CODE_NOTIFICATION,
            data[STConstants.PRIVATE_CHALLENGE_JOIN_CODE_NOTIFICATION]
        )
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )
//        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            PendingIntent.getActivity(
//                this, 0, intent,
//                PendingIntent.FLAG_IMMUTABLE
//            )
//        } else {
//            PendingIntent.getActivity(
//                this, 0, intent,
//                PendingIntent.FLAG_CANCEL_CURRENT
//            )
//        }
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.app_icon)
        val builder = NotificationCompat.Builder(this)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(
                resources.getString(R.string.app_name),
                resources.getString(R.string.app_name), importance
            )
            notificationManager.createNotificationChannel(channel)
        }
        builder.setSmallIcon(getNotificationIcon())
            .setLargeIcon(largeIcon)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setChannelId(resources.getString(R.string.app_name))
            .setSound(defaultSoundUri)
            .setPriority(Notification.PRIORITY_HIGH)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentIntent(pendingIntent)
        builder.setStyle(
            NotificationCompat.BigTextStyle().bigText(message)
        )
        val calendar = Calendar.getInstance()
        notificationManager.notify(
            STNotificationID.id/*calendar.timeInMillis.toInt()*/,
            builder.build()
        )
    }

    private fun getNotificationIcon(): Int {
        val useWhiteIcon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        return if (useWhiteIcon) R.drawable.ic_stat_name/*ic_small_icon*/ else R.drawable.ic_stat_name
    }
}
