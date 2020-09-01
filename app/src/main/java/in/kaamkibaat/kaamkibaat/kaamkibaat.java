package in.kaamkibaat.kaamkibaat;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.content.Context;
import com.google.firebase.database.FirebaseDatabase;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import in.kaamkibaat.kaamkibaat.bio.BioActivity;
import in.kaamkibaat.kaamkibaat.entertainment.EntertainmentActivity;
import in.kaamkibaat.kaamkibaat.news.NewsActivity;
import in.kaamkibaat.kaamkibaat.politics.PoliticsActivity;

public class kaamkibaat extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        Picasso.Builder picassoBuilder = new Picasso.Builder(this);
        picassoBuilder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso picasso = picassoBuilder.build();
        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);
        Picasso.setSingletonInstance(picasso);

//        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.DEBUG);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationOpenedHandler(new NotificationOpenedHandler(getApplicationContext()))
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        // e8f79f50-8811-4a32-b338-c851fcc6d3b4
    }
    public class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

        private final Context context;

        public NotificationOpenedHandler(Context context) {
            this.context = context;
        }
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            JSONObject data = result.notification.payload.additionalData;
            String message;
            if (data != null) {
                message = data.optString("message", null);
                if (message != null && message.equals("pol")) {
                    Log.i("OneSignal", "customkey set with value: " + message);
                    Intent intent = new Intent(context, PoliticsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else if (message != null && message.equals("news")) {
                    Intent intent = new Intent(context, NewsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else if (message != null && message.equals("bio")) {
                    Intent intent = new Intent(context, BioActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if (message != null && message.equals("ent")) {
                    Intent intent = new Intent(context, EntertainmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if (message != null && message.equals("video")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse("https://www.youtube.com/channel/UC3dzH7JgfIzm2na8QhIIscg"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if (message != null && message.equals("pod")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse("https://www.youtube.com/channel/UCXO9Pm-55cV34EShFbpsVQA"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }

            }
        }
    }
}
