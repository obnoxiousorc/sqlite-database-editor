package com.zjensen.sqlite_editor;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.zjensen.sqlite_editor.Adapters.AppsListAdapter;

public class MainActivity extends Activity {
	private static ListView appsListView;
	private static Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		context = this;
		
		appsListView = (ListView) findViewById(R.id.list_view);
		
		final PackageManager pm = getPackageManager();
		// Get a list of installed apps.
		List<ApplicationInfo> appsList = pm.getInstalledApplications(PackageManager.GET_META_DATA);

		AppsListAdapter adapter = new AppsListAdapter(this, appsList);
		appsListView.setAdapter(adapter);
		appsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				/*
				RunCommandTask task = new RunCommandTask() {
					ProgressDialog dialog;
					
					@Override
					protected void onPreExecute() {
						dialog = new ProgressDialog(context);
						dialog.setTitle("Getting root access...");
						dialog.show();
					}
					
					@Override
					protected void onPostExecute(Void result) {
						dialog.dismiss();
						Toast.makeText(context, "Tried to get root", Toast.LENGTH_SHORT).show();
					}
				};
				task.execute("echo \"hello\" > /data/data/com.endpoint.timesheets/x.txt");	
				*/
				TextView nameView = (TextView) view.findViewById(R.id.text_view);
				
				Intent intent = new Intent(context, DatabaseListActivity.class);
				intent.putExtra("packageName", nameView.getText());
				startActivity(intent);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
