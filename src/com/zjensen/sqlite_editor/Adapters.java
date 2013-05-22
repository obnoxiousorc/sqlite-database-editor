package com.zjensen.sqlite_editor;

import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapters {
	
	public static class AppsListAdapter extends BaseAdapter {
	
		private List<ApplicationInfo> appsList;
		private Context context;
		private LayoutInflater inflater;
		
		public AppsListAdapter(Context c, List<ApplicationInfo> aL) {
			context = c;
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			appsList = aL;
		}	
	
		@Override
		public int getCount() {
			return appsList.size();
		}
	
		@Override
		public Object getItem(int position) {
			return position;
		}
	
		@Override
		public long getItemId(int position) {
			return position;
		}
	
		@Override
		public View getView(int position, View mainView, ViewGroup parentView) {
			View view = mainView;
			if (view == null) view = inflater.inflate(R.layout.layout_list_item, null);
			
			TextView appNameView = (TextView) view.findViewById(R.id.text_view);
			ApplicationInfo appInfo = appsList.get(position);
			//Log.d(MainActivity.APP_NAME, "in getView: app name " + appInfo.);
			appNameView.setText(appInfo.packageName);
			
			return view;
		}
	}
	
	public static class DatabaseListAdapter extends BaseAdapter {
		
		private String[] databaseList;
		private Context context;
		private LayoutInflater inflater;
		
		public DatabaseListAdapter(Context c, String[] aL) {
			context = c;
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			databaseList = aL;
		}	
	
		@Override
		public int getCount() {
			return databaseList.length;
		}
	
		@Override
		public Object getItem(int position) {
			return position;
		}
	
		@Override
		public long getItemId(int position) {
			return position;
		}
	
		@Override
		public View getView(int position, View mainView, ViewGroup parentView) {
			View view = mainView;
			if (view == null) view = inflater.inflate(R.layout.layout_list_item, null);
			
			TextView databaseNameView = (TextView) view.findViewById(R.id.text_view);
			String pathName = databaseList[position];
			String fileName = pathName.split("/")[-1];
			databaseNameView.setText(fileName);
			//Log.d(MainActivity.APP_NAME, "in getView: app name " + appInfo.);
			//appNameView.setText(appInfo.packageName);
			
			return view;
		}
	}

}
