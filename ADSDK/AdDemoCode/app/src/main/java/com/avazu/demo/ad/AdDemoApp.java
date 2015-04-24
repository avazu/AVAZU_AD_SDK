package com.avazu.demo.ad;

import android.app.Application;
import android.util.Log;

import avazu.nativead.android.AvazuAdSdk;

public class AdDemoApp extends Application {
	
	private static final String SOURCE_ID = "6395";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("demo", "app onCreate");
        AvazuAdSdk.initialize(getApplicationContext(), SOURCE_ID);
	}
}
