package com.ability44.rsnotify.FeedParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ability44.rsnotify.List.YtRSSFeed;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class YtXmlFeedParser {
	private InputStream urlStream;
	private XmlPullParserFactory factory;
	private XmlPullParser parser;

	private List<YtRSSFeed> rssFeedList;
	private YtRSSFeed rssFeed;

	private String urlString;
	private String tagName;

	private String guid;
	private String pubDate;
	private String title;
	private String description;
	private String link;
	private String author;

	public static final String ITEM = "item";
	public static final String CHANNEL = "channel";

	public static final String GUID = "guid";
	public static final String PUBLISHEDDATE = "pubDate";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String LINK = "link";
	public static final String AUTHOR = "author";

	public YtXmlFeedParser(String urlString) {
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

	public List<YtRSSFeed> parse() {
		try {
			factory = XmlPullParserFactory.newInstance();
			parser = factory.newPullParser();
			urlStream = downloadUrl(urlString);
			parser.setInput(urlStream, null);
			int eventType = parser.getEventType();
			boolean done = false;
			rssFeed = new YtRSSFeed();
			rssFeedList = new ArrayList<YtRSSFeed>();
			while (eventType != XmlPullParser.END_DOCUMENT && !done) {
				tagName = parser.getName();

				switch (eventType) {
					case XmlPullParser.START_DOCUMENT :
						break;
					case XmlPullParser.START_TAG :
						if (tagName.equals(ITEM)) {
							rssFeed = new YtRSSFeed();
						}
						if (tagName.equals(GUID)) {
							guid = parser.nextText().toString();
						}
						if (tagName.equals(PUBLISHEDDATE)) {
							pubDate = parser.nextText().toString();
						}
						if (tagName.equals(TITLE)) {
							title = parser.nextText().toString();
						}
						if (tagName.equals(DESCRIPTION)) {
							description = parser.nextText().toString();
						}
						if (tagName.equals(LINK)) {
							link = parser.nextText().toString();
						}
						if (tagName.equals(AUTHOR)) {
							author = parser.nextText().toString();
						}
						break;
					case XmlPullParser.END_TAG :
						if (tagName.equals(CHANNEL)) {
							done = true;
						} else if (tagName.equals(ITEM)) {
							rssFeed = new YtRSSFeed(guid, pubDate,
									title, description, link, author);
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
