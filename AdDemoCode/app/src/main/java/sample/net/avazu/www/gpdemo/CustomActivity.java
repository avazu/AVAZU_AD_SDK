package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import nativesdk.ad.adcore.modules.webviewad.AdViewSettings;

public class CustomActivity extends Activity implements OnClickListener{
	
	private static final String SOURCE_ID = "15887";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		/**
		 * enable SDK log
		 */
//		AvazuAdSdk.DEBUG = true;
		
		/**
		 * SDK initialization
		 */
//		AvazuAdSdk.initialize(getApplicationContext(), SOURCE_ID, "UA-60530824-8");
		
		setContentView(R.layout.main_layout);
		findViewById(R.id.go_ad_rect_wall).setOnClickListener(this);
		findViewById(R.id.go_ad_banner_single).setOnClickListener(this);
		findViewById(R.id.go_ad_banner_wall).setOnClickListener(this);
		findViewById(R.id.go_ad_rect_single).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.go_ad_rect_wall:
			goAdShow(AdViewSettings.TYPE_RECT_WALL);
			break;
		case R.id.go_ad_rect_single:
			goAdShow(AdViewSettings.TYPE_RECT_SINGLE);
			break;
		case R.id.go_ad_banner_single:
			goAdShow(AdViewSettings.TYPE_BANNER_SINGLE);
			break;
		case R.id.go_ad_banner_wall:
			goAdShow(AdViewSettings.TYPE_BANNER_WALL);
			break;
		
		}
	}
	
	private void goAdShow(int show_type){
		Intent goArea = new Intent(this, AdControlActivity.class);
		goArea.putExtra(Utils.Constants.BUNDLE_KEY_ADVIEWSHOWTYPE, show_type);
		startActivity(goArea);
	}
	
	@SuppressWarnings("unused")
	private void goActy(Class<? extends Activity> clazz){
		Intent fire = new Intent(this, clazz);
		startActivity(fire);
	}
}
