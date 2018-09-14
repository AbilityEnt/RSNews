package com.ability44.rsnotify.ListAdapaters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ability44.rsnotify.R;
import com.ability44.rsnotify.List.RsNewsFeed;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class RsNewsListAdapter extends BaseAdapter {

	private List<RsNewsFeed> listData;

	private LayoutInflater layoutInflater;

	public RsNewsListAdapter(Context context, List<RsNewsFeed> listData) {
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.lv_row_news, null);
			holder = new ViewHolder();
			holder.headlineView = (TextView) convertView
					.findViewById(R.id.tvlistRsNewsTitle);
			holder.reportedDateView = (TextView) convertView
					.findViewById(R.id.tvlistRsNewsDate);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (listData != null) {
			RsNewsFeed item = listData.get(position);
			String time = item.getPubdate();
			holder.headlineView.setText(item.getTitle());
			holder.reportedDateView.setText(time);
		}

		return convertView;
	}

	static class ViewHolder {
		TextView headlineView;
		TextView reporterNameView;
		TextView reportedDateView;
	}

}
