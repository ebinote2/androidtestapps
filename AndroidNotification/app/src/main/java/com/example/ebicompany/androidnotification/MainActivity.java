package com.example.ebicompany.androidnotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.ebicompany.androidnotification.R.drawable.fire_eye_alien;

public class MainActivity extends Activity {

    public static final int MY_NOTIFICATION_ID = 1;

    private int numOfClicks = 0;
    private final CharSequence tikerText = "Ebi send this notification";
    private final CharSequence contentTitle = "Be careful";
    private final CharSequence conentText = "This is a virus";

    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    private Uri soundURI = Uri
            .parse("android.resource://com.example.ebicompany.androidnotification/"
                    + R.raw.alarm_rooster);
    private long[] mVibratePattern = { 0, 200, 200, 300 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowHint = (Button)findViewById(R.id.btnShowHint);
        btnShowHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setView(getLayoutInflater().inflate(R.layout.toast_view, null));
                toast.show();
            }
        });

        mNotificationIntent = new Intent(getApplicationContext(), NotificationSubActivity.class);
        mContentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

        final Notification.Builder notificationBuilder = new Notification.Builder(getApplicationContext())
                .setContentInfo("110")
                .setSmallIcon(R.drawable.icon)
                .setContentIntent(mContentIntent)
                .setContentTitle(contentTitle)
                .setContentText(conentText)
                .setTicker(tikerText)
                .setSound(soundURI)
                .setAutoCancel(true)
                .setLargeIcon((((BitmapDrawable) this.getResources().getDrawable(fire_eye_alien)).getBitmap()));
        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Button btnShowNotification = (Button)findViewById(R.id.btnShowNotification);
        btnShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationBuilder.setWhen(System.currentTimeMillis());
                mNotificationManager.notify(MY_NOTIFICATION_ID,
                        notificationBuilder.build());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
