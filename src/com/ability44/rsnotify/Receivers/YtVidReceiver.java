package com.ability44.rsnotify.Receivers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.ability44.rsnotify.R;
import com.ability44.rsnotify.AsyncTask.GetRssPubDate;
import com.ability44.rsnotify.AsyncTask.GetYtRssFeed;
import com.ability44.rsnotify.List.PubDateFeed;
import com.ability44.rsnotify.List.YtRSSFeed;
import com.ability44.rsnotify.Pages.YtVids;
import com.ability44.rsnotify.Utilities.NetConnect;
import com.ability44.rsnotify.Utilities.StringBuilder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class YtVidReceiver extends BroadcastReceiver {

	List<PubDateFeed> pubItem;
	List<YtRSSFeed> items;
	String[] ytChannels = {"Runescape", "WhatsUpWithRs"};
	String pubDatep = "VidPubDate", titlep = "VidTitle",
			descriptionp = "VidDescription", linkp = "VidLink",
			guidp = "VidGuid", authorp = "VidAuthor",
			ytChannelp = "YoutubeChannel";
	SharedPreferences pubDatePref, titlePref, categoryPref, descriptionPref,
			linkPref, guidPref, authorPref, ytChannelPref;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (NetConnect.getConnectivityStatus(context) != NetConnect.TYPE_NOT_CONNECTED) {
			for (int j = 0; j < ytChannels.length; j++) {
				pubDatePref = context.getSharedPreferences(ytChannels[j]
						+ pubDatep, Context.MODE_PRIVATE);
				GetRssPubDate getpubrss = new GetRssPubDate();
				AsyncTask<String, Void, List<PubDateFeed>> pubexecute = getpubrss
						.execute("http://www.youtube.com/rss/user/"
								+ ytChannels[j] + "/videos.rss");
				Log.d("VideoBroadcast", "onReceive");

				pubItem = null;
				try {
					pubItem = pubexecute.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

				String oldpubS = pubDatePref.getString(
						ytChannels[j] + pubDatep,
						StringBuilder.youtubePubDates(pubItem));
				String[] npub = StringBuilder.youtubePubDates(pubItem).split("`");
				if (oldpubS != StringBuilder.youtubePubDates(pubItem)) {
					titlePref = context.getSharedPreferences(ytChannels[j]
							+ titlep, Context.MODE_PRIVATE);
					descriptionPref = context.getSharedPreferences(
							ytChannels[j] + descriptionp, Context.MODE_PRIVATE);
					authorPref = context.getSharedPreferences(ytChannels[j]
							+ authorp, Context.MODE_PRIVATE);
					linkPref = context.getSharedPreferences(ytChannels[j]
							+ linkp, Context.MODE_PRIVATE);
					guidPref = context.getSharedPreferences(ytChannels[j]
							+ guidp, Context.MODE_PRIVATE);
					ytChannelPref = context.getSharedPreferences(ytChannelp,
							Context.MODE_PRIVATE);
					GetYtRssFeed getrss = new GetYtRssFeed();
					AsyncTask<String, Void, List<YtRSSFeed>> rssexecute = getrss
							.execute("http://www.youtube.com/rss/user/"
									+ ytChannels[j] + "/videos.rss");

					items = null;
					try {
						items = rssexecute.get();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < npub.length; i++) {
						if (oldpubS.contains(npub[i]) == false) {

							String title = ytChannels[j] + " uploaded a video!";
							String text = items.get(i).getTitle();

							NotificationManager notifyManager = (NotificationManager) context
									.getSystemService(Context.NOTIFICATION_SERVICE);

							Intent rsVid = new Intent(context, YtVids.class);
							PendingIntent piNotify = PendingIntent.getActivity(
									context, 0, rsVid, 0);

							ytChannelPref.edit()
									.putString(ytChannelp, ytChannels[j])
									.commit();
							NotificationCompat.Builder builder = new NotificationCompat.Builder(
									context);

							builder.setSmallIcon(R.drawable.runescape_logo)
									.setContentText(text)
									.setContentTitle(title)
									.setContentIntent(piNotify)
									.setDefaults(Notification.DEFAULT_ALL)
									.setAutoCancel(true);

							notifyManager.notify(2, builder.build());
						}
					}
					// Cache system adding new rss strings
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
									StringBuilder.ytDescriptions(items))
							.commit();
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
				}
			}

		}
	}
}
