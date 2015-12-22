package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import nativesdk.ad.adsdk.modules.webviewad.AvazuAdView;


public class CustomActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

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
			goAdShow(AvazuAdView.MULTIPLE_LINE_RECTANGLE);
			break;
		case R.id.go_ad_rect_single:
			goAdShow(AvazuAdView.SINGLE_LINE_RECTANGLE);
			break;
		case R.id.go_ad_banner_single:
			goAdShow(AvazuAdView.SINGLE_BANNER);
			break;
		case R.id.go_ad_banner_wall:
			goAdShow(AvazuAdView.MULTIPLE_LINE_BANNER);
			break;
		
		}
	}
	
	private void goAdShow(String show_type){
//		Intent goArea = new Intent(this, AdControlActivity.class);
//		goArea.putExtra(Utils.Constants.BUNDLE_KEY_ADVIEWSHOWTYPE, show_type);
//		startActivity(goArea);
	}
	
	@SuppressWarnings("unused")
	private void goActy(Class<? extends Activity> clazz){
		Intent fire = new Intent(this, clazz);
		startActivity(fire);
	}
}
