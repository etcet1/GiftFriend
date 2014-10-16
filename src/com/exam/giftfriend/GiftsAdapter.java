package com.exam.giftfriend;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.exam.giftfriend.sqlite.models.Gift;

public class GiftsAdapter extends ArrayAdapter<Gift>{
	private Context context;
	private int layoutId;
	private List<Gift> data;
	public GiftsAdapter(Context context, int resource, List<Gift> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layoutId = resource;
		this.data = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		View rowView = inflater.inflate(layoutId, parent, false);
		
		TextView giftName = (TextView)rowView.findViewById(R.id.gift_name);
		giftName.setText(data.get(position).getName());
		
		TextView giftLocation = (TextView)rowView.findViewById(R.id.gift_location);
		giftLocation.setText(data.get(position).getLocation());
				
		return rowView;
	}

	
}