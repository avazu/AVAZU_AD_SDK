package sample.net.avazu.www.gpdemo;

import android.app.Application;
import android.util.Log;

public class AdDemoApp extends Application {
	
	private static final String SOURCE_ID = "15887";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("demo", "app onCreate");
	}
}
