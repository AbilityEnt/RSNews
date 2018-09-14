package com.ability44.rsnotify.FeedParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ability44.rsnotify.List.PubDateFeed;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class PubDateXmlFeedParser {
	private InputStream urlStream;
	private XmlPullParserFactory factory;
	private XmlPullParser parser;

	private List<PubDateFeed> rssFeedList;
	private PubDateFeed rssFeed;

	private String urlString, tagName, pubDate;

	public static final String ITEM = "item", CHANNEL = "channel", PUBLISHEDDATE = "pubDate";

	public PubDateXmlFeedParser(String urlString) {
		this.urlString = urlString;
	}

	public static InputStream downloadUrl(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.connect();
		InputStream stream = conn.getInputStream();
		return stream;
	}

	public List<PubDateFeed> parse() {
		try {
			factory = XmlPullParserFactory.newInstance();
			parser = factory.newPullParser();
			urlStream = downloadUrl(urlString);
			parser.setInput(urlStream, null);
			int eventType = parser.getEventType();
			boolean done = false;
			rssFeed = new PubDateFeed();
			rssFeedList = new ArrayList<PubDateFeed>();
			while (eventType != XmlPullParser.END_DOCUMENT && !done) {
				tagName = parser.getName();

				switch (eventType) {
					case XmlPullParser.START_DOCUMENT :
						break;
					case XmlPullParser.START_TAG :
						if (tagName.equals(ITEM)) {
							rssFeed = new PubDateFeed();
						}
						if (tagName.equals(PUBLISHEDDATE)) {
							pubDate = parser.nextText().toString();
						}
						break;
					case XmlPullParser.END_TAG :
						if (tagName.equals(CHANNEL)) {
							done = true;
						} else if (tagName.equals(ITEM)) {
							rssFeed = new PubDateFeed(pubDate);
							rssFeedList.add(rssFeed);
						}
						break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rssFeedList;
	}
}
