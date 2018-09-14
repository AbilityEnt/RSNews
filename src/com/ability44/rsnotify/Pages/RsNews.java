package com.ability44.rsnotify.Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ability44.rsnotify.BaseActivity;
import com.ability44.rsnotify.MainActivity;
import com.ability44.rsnotify.R;
import com.ability44.rsnotify.AsyncTask.GetBitmap;
import com.ability44.rsnotify.AsyncTask.GetRsNewsRssFeed;
import com.ability44.rsnotify.List.RsNewsFeed;
import com.ability44.rsnotify.ListAdapaters.RsNewsListAdapter;
import com.ability44.rsnotify.Utilities.NetConnect;
import com.ability44.rsnotify.Utilities.StringBuilder;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class RsNews extends BaseActivity implements OnItemClickListener {

	ImageView imageView;
	private ListView mRssListView;
	private List<RsNewsFeed> items;
	private RsNewsListAdapter adapter;
	Activity activity;
	Context context;

	String pubDatep = "RsNewsPubDate", titlep = "RsNewsTitle",
			categoryp = "RsNewsCategory", descriptionp = "RsNewsDescription",
			enclosurep = "RsNewsEnclosure", linkp = "RsNewsLink",
			guidp = "RsNewsGuid";
	SharedPreferences pubDatePref, titlePref, categoryPref, descriptionPref,
			enclosurePref, linkPref, guidPref;
	String AD_UNIT_ID = "ca-app-pub-6918182161737584/1670131951";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_rss);
		activity = this;
		context = this.getApplicationContext();
		items = new ArrayList<RsNewsFeed>();

		/*** Start Ad ***/
		/*** End Ad ***/

		pubDatePref = context.getSharedPreferences(pubDatep,
				Context.MODE_PRIVATE);

		if (pubDatePref.getString(pubDatep, null) != null) {
			titlePref = context.getSharedPreferences(titlep,
					Context.MODE_PRIVATE);
			categoryPref = context.getSharedPreferences(categoryp,
					Context.MODE_PRIVATE);
			descriptionPref = context.getSharedPreferences(descriptionp,
					Context.MODE_PRIVATE);
			enclosurePref = context.getSharedPreferences(enclosurep,
					Context.MODE_PRIVATE);
			linkPref = context
					.getSharedPreferences(linkp, Context.MODE_PRIVATE);
			guidPref = context
					.getSharedPreferences(guidp, Context.MODE_PRIVATE);
			String[] pubDate = pubDatePref.getString(pubDatep, null).split("`");
			String[] title = titlePref.getString(titlep, null).split("`");
			String[] category = categoryPref.getString(categoryp, null).split(
					"`");
			String[] description = descriptionPref
					.getString(descriptionp, null).split("`");
			String[] enclosure = enclosurePref.getString(enclosurep, null)
					.split("`");
			String[] link = linkPref.getString(linkp, null).split("`");
			String[] guid = guidPref.getString(guidp, null).split("`");

			for (int i = 0; i < pubDate.length; i++) {
				items.add(new RsNewsFeed(title[i], enclosure[i],
						description[i], link[i], guid[i], category[i],
						pubDate[i]));
			}
			adapter = new RsNewsListAdapter(activity, items);
			mRssListView = (ListView) findViewById(R.id.lvRss);
			mRssListView.setAdapter(adapter);
			mRssListView.setOnItemClickListener(this);
		} else {
			pubDatePref = context.getSharedPreferences(pubDatep,
					Context.MODE_PRIVATE);
			titlePref = context.getSharedPreferences(titlep,
					Context.MODE_PRIVATE);
			categoryPref = context.getSharedPreferences(categoryp,
					Context.MODE_PRIVATE);
			descriptionPref = context.getSharedPreferences(descriptionp,
					Context.MODE_PRIVATE);
			enclosurePref = context.getSharedPreferences(enclosurep,
					Context.MODE_PRIVATE);
			linkPref = context
					.getSharedPreferences(linkp, Context.MODE_PRIVATE);
			guidPref = context
					.getSharedPreferences(guidp, Context.MODE_PRIVATE);
			if (NetConnect.getConnectivityStatus(context) != NetConnect.TYPE_NOT_CONNECTED) {

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
				pubDatePref
						.edit()
						.putString(pubDatep,
								StringBuilder.rsNewsPubDates(items)).commit();
				titlePref.edit()
						.putString(titlep, StringBuilder.rsNewsTitles(items))
						.commit();
				categoryPref
						.edit()
						.putString(categoryp,
								StringBuilder.rsNewsCategories(items)).commit();
				descriptionPref
						.edit()
						.putString(descriptionp,
								StringBuilder.rsNewsDescriptions(items))
						.commit();
				enclosurePref
						.edit()
						.putString(enclosurep,
								StringBuilder.rsNewsEnclosures(items)).commit();
				linkPref.edit()
						.putString(linkp, StringBuilder.rsNewsLinks(items))
						.commit();
				guidPref.edit()
						.putString(guidp, StringBuilder.rsNewsGuids(items))
						.commit();
				Toast.makeText(
						context,
						"Information is being added now.. The page will load shortly.",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(
						context,
						"You need to be connected to a network for us to gather any data..",
						Toast.LENGTH_LONG).show();
				finish();
				Intent mainAct = new Intent(this, MainActivity.class);
				startActivity(mainAct);
			}
			finish();
			Intent rsnews = new Intent(this, RsNews.class);
			startActivity(rsnews);
		}

	}

	@SuppressLint("InflateParams") @Override
	public void onItemClick(AdapterView<?> adapterView, View v, final int pos,
			long id) {

		final Builder alertDialog = new AlertDialog.Builder(activity);
		LayoutInflater inflater = LayoutInflater.from(activity);
		View dialog_layout = inflater.inflate(R.layout.alert_dialog, null);

		TextView tvDisc = (TextView) dialog_layout.findViewById(R.id.tvDisc);
		imageView = (ImageView) dialog_layout.findViewById(R.id.ivUpdateImg);

		if (NetConnect.getConnectivityStatus(context) != NetConnect.TYPE_NOT_CONNECTED) {
			alertDialog.setPositiveButton("Go to update",
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							startActivity(new Intent(Intent.ACTION_VIEW, Uri
									.parse(items.get(pos).getLink())));
						}
					});
		}

		alertDialog.setNeutralButton("Close", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

			}
		});

		alertDialog.setTitle(items.get(pos).getTitle());
		alertDialog.setView(dialog_layout);
		alertDialog.create();
		if (NetConnect.getConnectivityStatus(context) != NetConnect.TYPE_NOT_CONNECTED) {
			String enclosure = items.get(pos).getEnclosure();
			if (enclosure != null) {
				imageView.setTag(enclosure);
				new GetBitmap().execute(imageView);
			}

		}
		tvDisc.setText(items.get(pos).getDescription());
		alertDialog.show();

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
