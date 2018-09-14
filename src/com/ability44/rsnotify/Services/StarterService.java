package com.ability44.rsnotify.Services;

import com.ability44.rsnotify.Receivers.RsNewsReceiver;
import com.ability44.rsnotify.Receivers.YtVidReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class StarterService extends Service {

	private static final String TAG = "StarterService";

	@Override
	public void onStart(Intent intent, int startid) {
		Intent rsNewsIntent = new Intent(getApplicationContext(),
				RsNewsReceiver.class);
		Intent ytVidIntent = new Intent(getApplicationContext(),
				YtVidReceiver.class);
		ytVidIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		rsNewsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		PendingIntent pRsNewsIntent = PendingIntent.getBroadcast(
				getApplicationContext(), 0, rsNewsIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		PendingIntent pYtVidIntent = PendingIntent.getBroadcast(
				getApplicationContext(), 0, ytVidIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
				AlarmManager.INTERVAL_DAY / (24 * 2), pRsNewsIntent);
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
				AlarmManager.INTERVAL_DAY / (24 * 2), pYtVidIntent);

		Log.d(TAG, "onStart");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "My Service stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, TAG + " onDestroy");
	}
}
