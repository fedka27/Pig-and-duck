package app.meat.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import javax.inject.Inject;

import app.meat.R;
import app.meat.model.data.db.NewsFirebaseDB;
import app.meat.util.L;
import app.meat.view.main.MainActivity;


public class MessageService extends FirebaseMessagingService {
    @Inject
    NewsFirebaseDB newsFirebaseDB;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null){
            showNotificationNews(remoteMessage.getNotification());
        }
    }

    private void showNotificationNews(RemoteMessage.Notification notification) {
        L.e(getClass().getSimpleName(), "News - " + notification.getTitle());
        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.notification_news)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder.setFullScreenIntent(pendingIntent, true);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(45, builder.build());
    }
}
