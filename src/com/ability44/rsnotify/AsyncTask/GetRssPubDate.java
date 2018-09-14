package com.ability44.rsnotify.AsyncTask;

import java.util.List;

import android.os.AsyncTask;
import android.os.Handler;

import com.ability44.rsnotify.FeedParser.PubDateXmlFeedParser;
import com.ability44.rsnotify.List.PubDateFeed;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class GetRssPubDate extends AsyncTask<String, Void, List<PubDateFeed>> {

	private List<PubDateFeed> mRssFeedList;
	private PubDateXmlFeedParser mNewsFeeder;
	String jsonStr = null;
	Handler innerHandler;
	
	@Override
	protected List<PubDateFeed> doInBackground(String... params) {
		for (String urlVal : params) {
			mNewsFeeder = new PubDateXmlFeedParser(urlVal);
		}
		mRssFeedList = mNewsFeeder.parse();
		return mRssFeedList;
	}

}
