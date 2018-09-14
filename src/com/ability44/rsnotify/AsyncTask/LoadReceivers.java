package com.ability44.rsnotify.AsyncTask;

import com.ability44.rsnotify.MainActivity;
import com.ability44.rsnotify.Receivers.Autostart;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class LoadReceivers extends AsyncTask<Void, Integer, String> {

	ProgressDialog prog;
	
	@Override
	protected String doInBackground(Void... params) {
		Context context = MainActivity.context;
		Autostart autoS = new Autostart();
		autoS.getAbortBroadcast();
		autoS.onReceive(context, null);
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}
}
