package com.ability44.rsnotify.ListAdapaters;

import java.util.List;

import com.ability44.rsnotify.R;
import com.ability44.rsnotify.List.YtRSSFeed;
import com.ability44.rsnotify.Utilities.DateFormater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class YtListAdapter extends BaseAdapter {
	private List<YtRSSFeed> listData;

	private LayoutInflater layoutInflater;
	
	public YtListAdapter(Context context, List<YtRSSFeed> listData) {
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("SimpleDateFormat")
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.lv_row_video, null);
			holder = new ViewHolder();
			holder.headlineView = (TextView) convertView.findViewById(R.id.tvlistVidTitle);
			holder.reportedDateView = (TextView) convertView.findViewById(R.id.tvlistVidDate);
			holder.imageView = (ImageView) convertView.findViewById(R.id.ivVidIcon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		YtRSSFeed newsItem = (YtRSSFeed) listData.get(position);
		String time = newsItem.getPubdate();
		
		holder.headlineView.setText(newsItem.getTitle());
		holder.reportedDateView.setText(DateFormater.YoutubeDateFormater(time));

//		if (holder.imageView != null) {
//			String description = newsItem.getDescription();
//			String vidIcon = description.substring(
//					description.indexOf("http://i.ytimg.com/vi/"),
//					description.indexOf(".jpg") + 4);
//			new ImageDownloader(holder.imageView).execute(vidIcon);
//		}
		return convertView;
	}

	static class ViewHolder {
		TextView headlineView;
		TextView reporterNameView;
		TextView reportedDateView;
		ImageView imageView;
	}
}
