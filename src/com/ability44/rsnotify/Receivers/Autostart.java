package com.ability44.rsnotify.Receivers;

import com.ability44.rsnotify.Services.StarterService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class Autostart extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		Log.d("Autostart",
				"BOOT_COMPLETED broadcast received. Executing starter service.");
		Intent intent = new Intent(context, StarterService.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.stopService(intent);
		context.startService(intent);
	}

}
