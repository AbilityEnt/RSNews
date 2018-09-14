package com.ability44.rsnotify.List;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class RsNewsFeed {

	private String title, enclosure, description, link, guid, category, pubDate;

	public RsNewsFeed() {
	}

	public RsNewsFeed(String title, String enclosure, String description, String link,
			String guid, String category, String pubDate) {
		this.title = title;
		this.enclosure = enclosure;
		this.link = link;
		this.description = description;
		this.guid = guid;
		this.category = category;
		this.pubDate = pubDate;
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

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPubdate() {
		return pubDate;
	}

	public void setPubdate(String pubdate) {
		this.pubDate = pubdate;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

}
