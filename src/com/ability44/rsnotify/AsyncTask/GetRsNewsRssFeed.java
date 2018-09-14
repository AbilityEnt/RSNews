package com.ability44.rsnotify.AsyncTask;

import java.util.List;

import android.os.AsyncTask;
import android.os.Handler;

import com.ability44.rsnotify.FeedParser.RsNewsXmlFeedParser;
import com.ability44.rsnotify.List.RsNewsFeed;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class GetRsNewsRssFeed extends AsyncTask<String, Void, List<RsNewsFeed>> {

	private List<RsNewsFeed> mRssFeedList;
	private RsNewsXmlFeedParser mNewsFeeder;
	String jsonStr = null;
	Handler innerHandler;
	
	@Override
	protected List<RsNewsFeed> doInBackground(String... params) {
		for (String urlVal : params) {
			mNewsFeeder = new RsNewsXmlFeedParser(urlVal);
		}
		mRssFeedList = mNewsFeeder.parse();
		return mRssFeedList;
	}

}
