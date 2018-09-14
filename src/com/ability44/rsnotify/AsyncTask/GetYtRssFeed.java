package com.ability44.rsnotify.AsyncTask;

import java.util.List;

import android.os.AsyncTask;
import android.os.Handler;

import com.ability44.rsnotify.FeedParser.YtXmlFeedParser;
import com.ability44.rsnotify.List.YtRSSFeed;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class GetYtRssFeed extends AsyncTask<String, Void, List<YtRSSFeed>> {

	private List<YtRSSFeed> mRssFeedList;
	private YtXmlFeedParser mNewsFeeder;
	String jsonStr = null;
	Handler innerHandler;
	
	@Override
	protected List<YtRSSFeed> doInBackground(String... params) {
		for (String urlVal : params) {
			mNewsFeeder = new YtXmlFeedParser(urlVal);
		}
		mRssFeedList = mNewsFeeder.parse();
		return mRssFeedList;
	}
}
