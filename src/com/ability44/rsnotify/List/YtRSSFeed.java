package com.ability44.rsnotify.List;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class YtRSSFeed {

	private String guid, pubDate, title, description, link, author;

	public YtRSSFeed() {
	}

	public YtRSSFeed(String guid, String pubDate, String title,
			String description, String link, String author) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.guid = guid;
		this.author = author;
		this.pubDate = pubDate;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getPubdate() {
		return pubDate;
	}

	public void setPubdate(String pubdate) {
		this.pubDate = pubdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
