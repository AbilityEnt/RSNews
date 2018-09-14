package com.ability44.rsnotify.Pages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.ability44.rsnotify.BaseActivity;
import com.ability44.rsnotify.MainActivity;
import com.ability44.rsnotify.R;
import com.ability44.rsnotify.AsyncTask.GetYtRssFeed;
import com.ability44.rsnotify.List.YtRSSFeed;
import com.ability44.rsnotify.ListAdapaters.YtListAdapter;
import com.ability44.rsnotify.Utilities.NetConnect;
import com.ability44.rsnotify.Utilities.StringBuilder;
import com.keyes.youtube.OpenYouTubePlayerActivity;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class YtVids extends BaseActivity implements OnItemClickListener {

	private ListView mRssListView;
	private List<YtRSSFeed> items;
	private YtListAdapter adapter;
	Activity activity;
	Context context;

	String[] ytChannels = {"Runescape", "WhatsUpWithRs"};
	String pubDatep = "VidPubDate", titlep = "VidTitle",
			descriptionp = "VidDescription", linkp = "VidLink",
			guidp = "VidGuid", authorp = "VidAuthor",
			ytChannelp = "YoutubeChannel";
	SharedPreferences pubDatePref, titlePref, descriptionPref, linkPref,
			guidPref, authorPref, ytChannelPref;
	String AD_UNIT_ID = "ca-app-pub-6918182161737584/1670131951";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_rss);
		activity = this;
		context = this.getApplicationContext();
		items = new ArrayList<YtRSSFeed>();

		/*** Start Ad ***/
		/*** End Ad ***/

		ytChannelPref = context.getSharedPreferences(ytChannelp,
				Context.MODE_PRIVATE);
		int j = 0;
		for (int i = 0; i < ytChannels.length; i++) {
			if (ytChannels[i] == ytChannelPref.getString(ytChannelp, null)) {
				j = i;
			}
		}

		pubDatePref = context.getSharedPreferences(ytChannels[j] + pubDatep,
				Context.MODE_PRIVATE);

		if (pubDatePref.getString(ytChannels[j] + pubDatep, null) != null) {
			titlePref = context.getSharedPreferences(ytChannels[j] + titlep,
					Context.MODE_PRIVATE);
			descriptionPref = context.getSharedPreferences(ytChannels[j]
					+ descriptionp, Context.MODE_PRIVATE);
			authorPref = context.getSharedPreferences(ytChannels[j] + authorp,
					Context.MODE_PRIVATE);
			linkPref = context.getSharedPreferences(ytChannels[j] + linkp,
					Context.MODE_PRIVATE);
			guidPref = context.getSharedPreferences(ytChannels[j] + guidp,
					Context.MODE_PRIVATE);
			String[] pubDate = pubDatePref.getString(ytChannels[j] + pubDatep,
					null).split("`");
			String[] title = titlePref.getString(ytChannels[j] + titlep, null)
					.split("`");
			String[] description = descriptionPref.getString(
					ytChannels[j] + descriptionp, null).split("`");
			String[] link = linkPref.getString(ytChannels[j] + linkp, null)
					.split("`");
			String[] guid = guidPref.getString(ytChannels[j] + guidp, null)
					.split("`");
			String[] author = authorPref.getString(ytChannels[j] + authorp,
					null).split("`");
			for (int i = 0; i < pubDate.length; i++) {
				items.add(new YtRSSFeed(guid[i], pubDate[i], title[i],
						description[i], link[i], author[i]));
			}
			adapter = new YtListAdapter(activity, items);
			mRssListView = (ListView) findViewById(R.id.lvRss);
			mRssListView.setAdapter(adapter);
			mRssListView.setOnItemClickListener(this);
		} else {
			if (NetConnect.getConnectivityStatus(context) != NetConnect.TYPE_NOT_CONNECTED) {
				pubDatePref = context.getSharedPreferences(ytChannels[j]
						+ pubDatep, Context.MODE_PRIVATE);
				titlePref = context.getSharedPreferences(
						ytChannels[j] + titlep, Context.MODE_PRIVATE);
				descriptionPref = context.getSharedPreferences(ytChannels[j]
						+ descriptionp, Context.MODE_PRIVATE);
				authorPref = context.getSharedPreferences(ytChannels[j]
						+ authorp, Context.MODE_PRIVATE);
				linkPref = context.getSharedPreferences(ytChannels[j] + linkp,
						Context.MODE_PRIVATE);
				guidPref = context.getSharedPreferences(ytChannels[j] + guidp,
						Context.MODE_PRIVATE);
				ytChannelPref = context.getSharedPreferences(ytChannelp,
						Context.MODE_PRIVATE);

				GetYtRssFeed getrss = new GetYtRssFeed();
				AsyncTask<String, Void, List<YtRSSFeed>> rssexecute = getrss
						.execute("http://www.youtube.com/rss/user/"
								+ ytChannels[j] + "/videos.rss");
				Log.d("VideoBroadcast", "onReceive");

				items = null;
				try {
					items = rssexecute.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				pubDatePref
						.edit()
						.putString(ytChannels[j] + pubDatep,
								StringBuilder.ytPubDates(items)).commit();
				titlePref
						.edit()
						.putString(ytChannels[j] + titlep,
								StringBuilder.ytTitles(items)).commit();
				descriptionPref
						.edit()
						.putString(ytChannels[j] + descriptionp,
								StringBuilder.ytDescriptions(items)).commit();
				linkPref.edit()
						.putString(ytChannels[j] + linkp,
								StringBuilder.ytLinks(items)).commit();
				guidPref.edit()
						.putString(ytChannels[j] + guidp,
								StringBuilder.ytGuids(items)).commit();
				authorPref
						.edit()
						.putString(ytChannels[j] + authorp,
								StringBuilder.ytAuthor(items)).commit();
				Toast.makeText(
						context,
						"Information is being added now.. The page will load shortly.",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(
						context,
						"You need internet connection to gather the information.",
						Toast.LENGTH_LONG).show();
				finish();
				Intent mainAct = new Intent(this, MainActivity.class);
				startActivity(mainAct);
			}
			finish();
			Intent ytvids = new Intent(this, YtVids.class);
			startActivity(ytvids);

		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View v, final int pos,
			long id) {

		String url = items.get(pos).getLink();
		Uri uri = Uri.parse(url);
		String videoId = uri.getQueryParameter("v");
		Intent videoIntent = new Intent(null, Uri.parse("ytv:" + videoId),
				activity, OpenYouTubePlayerActivity.class);
		startActivity(videoIntent);
	}

	public Bitmap getBitmap(String bitmapUrl) {
		try {
			URL url = new URL(bitmapUrl);
			return BitmapFactory.decodeStream(url.openConnection()
					.getInputStream());
		} catch (Exception ex) {
			return null;
		}
	}

	public void gotoHome(View v) {
		onBackPressed();
	}
	
	  @Override
	  public void onResume() {
	    super.onResume();
	  }

	  @Override
	  public void onPause() {
	    super.onPause();
	  }

	  /** Called before the activity is destroyed. */
	  @Override
	  public void onDestroy() {
	    super.onDestroy();
	  }
}
