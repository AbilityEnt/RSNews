package com.ability44.rsnotify.Utilities;

import java.util.List;

import com.ability44.rsnotify.List.PubDateFeed;
import com.ability44.rsnotify.List.RsNewsFeed;
import com.ability44.rsnotify.List.YtRSSFeed;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class StringBuilder {
	
	public static String runescapePubDates(List<PubDateFeed> items) {
		String itemsPub = "";
		for (int i = 0; i < items.size(); i++) {
			String time = items.get(i).getPubdate();
			String formatedDate = DateFormater.RsNewsDateFormater(time) + "`";
			itemsPub = itemsPub + formatedDate;
		}
		return itemsPub;
	}
	
	public static String youtubePubDates(List<PubDateFeed> items) {
		String itemsPub = "";
		for (int i = 0; i < items.size(); i++) {
			String time = items.get(i).getPubdate() + "`";
			itemsPub = itemsPub + time;
		}
		return itemsPub;
	}
	
	public static String rsNewsPubDates(List<RsNewsFeed> items) {
		String itemsPub = "";
		for (int i = 0; i < items.size(); i++) {
			String time = items.get(i).getPubdate();
			String formatedDate = DateFormater.RsNewsDateFormater(time) + "`";
			itemsPub = itemsPub + formatedDate;
		}
		return itemsPub;
	}
	
	public static String rsNewsTitles(List<RsNewsFeed> items){
		String itemsTitle = "";
		for (int i = 0; i < items.size(); i++) {
			String title = items.get(i).getTitle() + "`";
			itemsTitle = itemsTitle + title;
		}
		return itemsTitle;
	}
	
	public static String rsNewsCategories(List<RsNewsFeed> items){
		String itemsCat = "";
		for (int i = 0; i < items.size(); i++) {
			String cat = items.get(i).getCategory() + "`";
			itemsCat = itemsCat + cat;
		}
		return itemsCat;
	}
	
	public static String rsNewsDescriptions(List<RsNewsFeed> items){
		String itemsDec = "";
		for (int i = 0; i < items.size(); i++) {
			String dec = items.get(i).getDescription() + "`";
			itemsDec = itemsDec + dec;
		}
		return itemsDec;
	}
	
	public static String rsNewsEnclosures(List<RsNewsFeed> items){
		String itemsEnc = "";
		for (int i = 0; i < items.size(); i++) {
			String enc = items.get(i).getEnclosure() + "`";
			itemsEnc = itemsEnc + enc;
		}
		return itemsEnc;
	}

	public static String rsNewsLinks(List<RsNewsFeed> items){
		String itemsLink = "";
		for (int i = 0; i < items.size(); i++) {
			String link = items.get(i).getLink() + "`";
			itemsLink = itemsLink + link;
		}
		return itemsLink;
	}

	public static String rsNewsGuids(List<RsNewsFeed> items){
		String itemsGuid = "";
		for (int i = 0; i < items.size(); i++) {
			String guid = items.get(i).getGuid() + "`";
			itemsGuid = itemsGuid + guid;
		}
		return itemsGuid;
	}

	public static String ytPubDates(List<YtRSSFeed> items) {
		String itemsPub = "";
		for (int i = 0; i < items.size(); i++) {
			String time = items.get(i).getPubdate() + "`";
			itemsPub = itemsPub + time;
		}
		return itemsPub;
	}

	public static String ytTitles(List<YtRSSFeed> items) {
		String itemsTitle = "";
		for (int i = 0; i < items.size(); i++) {
			String title = items.get(i).getTitle() + "`";
			itemsTitle = itemsTitle + title;
		}
		return itemsTitle;
	}

	public static String ytDescriptions(List<YtRSSFeed> items) {
		String itemsDec = "";
		for (int i = 0; i < items.size(); i++) {
			String dec = items.get(i).getDescription() + "`";
			itemsDec = itemsDec + dec;
		}
		return itemsDec;
	}

	public static String ytLinks(List<YtRSSFeed> items) {
		String itemsLink = "";
		for (int i = 0; i < items.size(); i++) {
			String link = items.get(i).getLink() + "`";
			itemsLink = itemsLink + link;
		}
		return itemsLink;
	}

	public static String ytGuids(List<YtRSSFeed> items) {
		String itemsGuid = "";
		for (int i = 0; i < items.size(); i++) {
			String guid = items.get(i).getGuid() + "`";
			itemsGuid = itemsGuid + guid;
		}
		return itemsGuid;
	}

	public static String ytAuthor(List<YtRSSFeed> items) {
		String itemsAuthor = "";
		for (int i = 0; i < items.size(); i++) {
			String author = items.get(i).getGuid() + "`";
			itemsAuthor = itemsAuthor + author;
		}
		return itemsAuthor;
	}
}
