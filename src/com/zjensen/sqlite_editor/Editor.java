package com.zjensen.sqlite_editor;

import java.io.DataOutputStream;
import java.io.IOException;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

public class Editor extends Application {
	public static final String APP_NAME = "SQLite Database Editor";
	public static final String PACKAGE_NAME = "com.zjensen.sqlite_editor";
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	private static Process getRootAccess() {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("su");
			//process.waitFor();
		} catch (IOException e) {
			Log.d(APP_NAME, "Error getting root!");
			e.printStackTrace();
		}
		Log.d(APP_NAME, "Successfully got root... maybe!");
		return process;
	}
	
	public static boolean haveRoot() {
		Process process = getRootAccess();
		return process.exitValue() == 0;
	}
	
	static class RunCommandTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... commands) {
			Process process;
			try {
				process = Runtime.getRuntime().exec("su\n");
			} catch (IOException e) {
				Log.d(APP_NAME, "Error getting root!");
				e.printStackTrace();
				return null;
			}
			
			DataOutputStream shell = new DataOutputStream(process.getOutputStream());
			
			for (String command : commands) {
				Log.d(APP_NAME, "Running command: " + command);
				try {
					shell.writeBytes(command + "\n");
					shell.flush();
				} catch (IOException e) {
					Log.d(APP_NAME, "Error running command \"" + command + "\"");
					e.printStackTrace();
				}
				Log.d(APP_NAME, "Ran command: " + command);
			}
			
			try {
				shell.writeBytes("exit\n");
				shell.flush();
			} catch (IOException e) {
				Log.d(APP_NAME, "Error closing shell");
				e.printStackTrace();
			}
			
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				Log.d(APP_NAME, "Interrupted while running commands");
				e.printStackTrace();
			}
			
			return null;
		}
		
	}

}
