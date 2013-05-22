package com.zjensen.sqlite_editor;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.zjensen.sqlite_editor.Adapters.DatabaseListAdapter;

public class DatabaseListActivity extends Activity {
	private static Context context;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_list);
		Log.d(Editor.APP_NAME, "in DatabaseListActivity onCreate");
		
		context = this;
		
		ListView databaseListView = (ListView) findViewById(R.id.list_view);
		
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
		task.execute("ls /data/data/com.endpoint.timesheets/databases/");
		*/
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String packageName = "";
		if (extras != null) {
			packageName = extras.getString("packageName");
			Log.d(Editor.APP_NAME, "Got extras: " + packageName);
		} else {
			Log.d(Editor.APP_NAME, "Extras are null!");
		}
		
		File databaseDir = getAppDatabaseDir(packageName);
		
		if (databaseDir.exists()) {
			String[] filenames = databaseDir.list();
			
			Log.d(Editor.APP_NAME, "filenames is " + filenames);
			DatabaseListAdapter adapter = new DatabaseListAdapter(this, filenames);
			databaseListView.setAdapter(adapter);
		}
	}

	
	public static File getAppDatabaseDir(String packageName) {
		File appDir = context.getFilesDir();
		String path = appDir.getPath();
		String dataPath = path.split(Editor.PACKAGE_NAME)[0];
		Log.d(Editor.APP_NAME, "using path " + dataPath + packageName + "/databases/");
		return new File(dataPath + packageName + "/databases/");
	}
}
