package com.ability44.rsnotify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.ability44.rsnotify.AsyncTask.LoadReceivers;
import com.ability44.rsnotify.Pages.RsNews;
import com.ability44.rsnotify.Pages.YtVids;
import com.appfireworks.android.track.AppTracker;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class MainActivity extends BaseActivity {

	SharedPreferences preferences, ytChannelPref;
	String runVerson = "RunVersion", ytChannelp = "YoutubeChannel",
			AD_UNIT_ID = "ca-app-pub-6918182161737584/1670131951";
	int rvint = 7; // 7
	public static Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this.getApplicationContext();

		preferences = this
				.getSharedPreferences(runVerson, Context.MODE_PRIVATE);
		ytChannelPref = this.getSharedPreferences(ytChannelp,
				Context.MODE_PRIVATE);
		if (preferences.getInt(runVerson, 0) < rvint) {
			new LoadReceivers().execute();
			preferences.edit().putInt(runVerson, rvint).commit();
		}
		/*** Start Ad ***/
//		layout = (LinearLayout) this.findViewById(R.id.linMainPage);
//		AdBase.StartAd(adView, context, AD_UNIT_ID, layout);
		/*** End Ad ***/
	}

	public void gotoRecentNews(View v) {
		Intent rsnews = new Intent(this, RsNews.class);
		startActivity(rsnews);
		onPause();
	}

	public void gotoRunescapeVideo(View v) {
		Intent rsvids = new Intent(this, YtVids.class);
		ytChannelPref.edit().putString(ytChannelp, "Runescape").commit();
		startActivity(rsvids);
		onPause();
	}

	public void gotoWhatsUpWithRsVideo(View v) {
		Intent wuwrsvids = new Intent(this, YtVids.class);
		ytChannelPref.edit().putString(ytChannelp, "WhatsUpWithRs").commit();
		startActivity(wuwrsvids);
		onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		AppTracker.resume(getApplicationContext());
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
