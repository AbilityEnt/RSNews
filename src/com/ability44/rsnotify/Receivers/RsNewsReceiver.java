package com.ability44.rsnotify.Receivers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.ability44.rsnotify.R;
import com.ability44.rsnotify.AsyncTask.GetRsNewsRssFeed;
import com.ability44.rsnotify.AsyncTask.GetRssPubDate;
import com.ability44.rsnotify.List.PubDateFeed;
import com.ability44.rsnotify.List.RsNewsFeed;
import com.ability44.rsnotify.Utilities.NetConnect;
import com.ability44.rsnotify.Utilities.StringBuilder;
import com.ability44.rsnotify.Pages.RsNews;

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

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class RsNewsReceiver extends BroadcastReceiver {

	List<PubDateFeed> pubItem;
	List<RsNewsFeed> items;
	String pubDatep = "RsNewsPubDate", titlep = "RsNewsTitle",
			categoryp = "RsNewsCategory", descriptionp = "RsNewsDescription",
			enclosurep = "RsNewsEnclosure", linkp = "RsNewsLink",
			guidp = "RsNewsGuid";
	SharedPreferences pubDatePref, titlePref, categoryPref, descriptionPref,
			enclosurePref, linkPref, guidPref;

	@Override
	public void onReceive(Context context, Intent intent) {
		pubDatePref = context.getSharedPreferences(pubDatep,
				Context.MODE_PRIVATE);
		if (NetConnect.getConnectivityStatus(context) != NetConnect.TYPE_NOT_CONNECTED) {
			GetRssPubDate getpubrss = new GetRssPubDate();
			AsyncTask<String, Void, List<PubDateFeed>> pubexecute = getpubrss
					.execute("http://services.runescape.com/m=news/latest_news.rss");
			Log.d("RsNewsNotification", "onReceive");

			pubItem = null;
			try {
				pubItem = pubexecute.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			String oldpubS = pubDatePref.getString(pubDatep, StringBuilder
					.runescapePubDates(pubItem));
			String[] npub = StringBuilder.runescapePubDates(pubItem).split("`");
			if (oldpubS != StringBuilder.runescapePubDates(pubItem)) {
				titlePref = context.getSharedPreferences(titlep, Context.MODE_PRIVATE);
				categoryPref = context.getSharedPreferences(categoryp,
						Context.MODE_PRIVATE);
				descriptionPref = context.getSharedPreferences(descriptionp,
						Context.MODE_PRIVATE);
				enclosurePref = context.getSharedPreferences(enclosurep,
						Context.MODE_PRIVATE);
				linkPref = context.getSharedPreferences(linkp, Context.MODE_PRIVATE);
				guidPref = context.getSharedPreferences(guidp, Context.MODE_PRIVATE);
				GetRsNewsRssFeed getrss = new GetRsNewsRssFeed();
				AsyncTask<String, Void, List<RsNewsFeed>> rssexecute = getrss
						.execute("http://services.runescape.com/m=news/latest_news.rss");
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
						String title = items.get(i).getTitle();
						String text = "Click to read more!";

						NotificationManager notifyManager = (NotificationManager) context
								.getSystemService(Context.NOTIFICATION_SERVICE);

						Intent rsNews = new Intent(context, RsNews.class);
						PendingIntent piNotify = PendingIntent.getActivity(
								context, 0, rsNews, 0);

						NotificationCompat.Builder builder = new NotificationCompat.Builder(
								context);

						builder.setSmallIcon(R.drawable.runescape_logo)
								.setContentText(text).setContentTitle(title)
								.setContentIntent(piNotify)
								.setDefaults(Notification.DEFAULT_ALL)
								.setAutoCancel(true);

						notifyManager.notify(1, builder.build());
					}
				}
				// Cache system adding new rss strings
				pubDatePref.edit()
						.putString(pubDatep, StringBuilder.rsNewsPubDates(items))
						.commit();
				titlePref.edit().putString(titlep, StringBuilder.rsNewsTitles(items))
						.commit();
				categoryPref.edit()
						.putString(categoryp, StringBuilder.rsNewsCategories(items))
						.commit();
				descriptionPref
						.edit()
						.putString(descriptionp,
								StringBuilder.rsNewsDescriptions(items)).commit();
				enclosurePref.edit()
						.putString(enclosurep, StringBuilder.rsNewsEnclosures(items))
						.commit();
				linkPref.edit().putString(linkp, StringBuilder.rsNewsLinks(items))
						.commit();
				guidPref.edit().putString(guidp, StringBuilder.rsNewsGuids(items))
						.commit();
			}
		}
	}
}