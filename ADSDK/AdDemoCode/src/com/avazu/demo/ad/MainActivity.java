package com.avazu.demo.ad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.avazu.ad.AdViewController;
import com.avazu.ad.AvazuAdSdk;

public class MainActivity extends Activity implements OnClickListener{
	
	private static final String SOURCE_ID = "6395";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		/**
		 * enable SDK log
		 */
		AvazuAdSdk.DEBUG = true;
		
		/**
		 * SDK initialization
		 */
		AvazuAdSdk.initialize(getApplicationContext(), SOURCE_ID);
		
		
		setContentView(R.layout.ad_main_layout);
		
		findViewById(R.id.go_banner_single).setOnClickListener(this);
		findViewById(R.id.go_banner_wall).setOnClickListener(this);
		findViewById(R.id.go_banner_custom).setOnClickListener(this);
		findViewById(R.id.go_rect_single).setOnClickListener(this);
		findViewById(R.id.go_rect_wall).setOnClickListener(this);
		findViewById(R.id.go_rect_custom).setOnClickListener(this);
		findViewById(R.id.open_install_jack).setOnClickListener(this);
		findViewById(R.id.set_short_cut).setOnClickListener(this);
		findViewById(R.id.set_try_your_luck).setOnClickListener(this);
		findViewById(R.id.browser_open_jack).setOnClickListener(this);
		
	}
	
	private void goDefaultRectWall(){
		AdViewController controller = new AdViewController(-1, -1, AdViewController.TYPE_RECT_WALL, true);
		controller.setAppCount(6);
		controller.setNeedBtn(false);
		controller.setNeedIcon(true);
		controller.setNeedRating(true);
		controller.setNeedTitle(true);
		controller.setNeedSize(false);
		controller.setNeedCat(false);
		controller.setMainBackColor("#E8E6E6");
		controller.setBlockBackColor("#EDEDE1");
		controller.setAppTitleColor("#000000");
		/*controller.setButtonBackColor("#ADCF7C");
		controller.setButtonTextColor("#000000");*/
		goShowAd(controller);
	}
	
	private void goDefaultRectSingle(){
		AdViewController controller = new AdViewController(120, 165, AdViewController.TYPE_RECT_SINGLE, true);
		controller.setNeedIcon(true);
		controller.setNeedRating(true);
		controller.setNeedTitle(true);
		controller.setBlockBackColor("#EDEDE1");
		controller.setAppTitleColor("#000000");
		goShowAd(controller);
	}
	
	private void goDefaultBannerWall(){
		AdViewController controller = new AdViewController(-1, -1, AdViewController.TYPE_BANNER_WALL, true);
		controller.setAppCount(12);
		controller.setNeedBtn(true);
		controller.setNeedCat(true);
		controller.setNeedIcon(true);
		controller.setNeedInstalls(true);
		controller.setNeedRating(true);
		controller.setNeedReviewNum(true);
		controller.setNeedSize(true);
		controller.setNeedTitle(true);
		controller.setMainBackColor("#EDEDE1");
		controller.setAppTitleColor("#000000");
		goShowAd(controller);
	}
	
	private void goDefaultBannerSingle(){
		
		int sw = getResources().getDisplayMetrics().widthPixels;
		int swD = Utils.px2dp(this, sw);
		int hD = swD*100/360;
		AdViewController controller = new AdViewController(swD, hD, AdViewController.TYPE_BANNER_SINGLE, true);
		controller.setNeedBtn(true);
		controller.setNeedCat(true);
		controller.setNeedIcon(true);
		controller.setNeedInstalls(true);
		controller.setNeedRating(true);
		controller.setNeedReviewNum(true);
		controller.setNeedSize(true);
		controller.setNeedTitle(true);
		controller.setBlockBackColor("#EDEDE1");
		controller.setAppTitleColor("#000000");
		goShowAd(controller);
	}
	
	private void goCustomAd(int style){
		Intent custom = new Intent(this, AdCustomizedActivity.class);
		custom.putExtra(Utils.Constants.BUNDLE_KEY_ADSTYLE, style);
		startActivity(custom);
	}
	
	private void goShowAd(AdViewController controller){
		Intent fire = new Intent(this, AdShowActy.class);
		fire.putExtra(Utils.Constants.BUNDLE_KEY_ADVIEWCONTROLLER, controller);
		startActivity(fire);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.go_banner_single:
//			goDefaultBannerSingle();
			showTwoPickDialog();
			break;

		case R.id.go_banner_wall:
//			goDefaultBannerWall();
			goBannerWallNew();
			break;
			
		case R.id.go_rect_single:
//			goDefaultRectSingle();
			goRectSingleNew();
			break;
			
		case R.id.go_rect_wall:
//			goDefaultRectWall();
			goRectWallNew();
			break;
			
		case R.id.go_rect_custom:
			goCustomAd(Utils.Constants.STYLE_RECT);
			break;
			
		case R.id.go_banner_custom:
			goCustomAd(Utils.Constants.STYLE_BANNER);
			break;
		case R.id.open_install_jack:
			AvazuAdSdk.setInstallHijackEnable(this, true);
			Toast.makeText(this, "App-Installed Ad activated, please install a new app for demonstration.", Toast.LENGTH_SHORT).show();
			break;
		case R.id.set_short_cut:
			AvazuAdSdk.setRecommendation(this,getString(R.string.recommendation));
			Toast.makeText(this, "Desktop Button Ad activated, please check your desktop.", Toast.LENGTH_SHORT).show();
			break;
		case R.id.set_try_your_luck:
			AvazuAdSdk.setTryLuck(this,getString(R.string.try_luck), BitmapFactory.decodeResource(getResources(), R.drawable.ic_recommend));
			Toast.makeText(this, "Lucky Draw Ad activated, please check your desktop.", Toast.LENGTH_SHORT).show();
			break;
		case R.id.browser_open_jack:
			AvazuAdSdk.setAppOpenHijackEnable(this, true);
			Toast.makeText(this, "Browser Footer Ad activated, please launch your browser for demonstration.", Toast.LENGTH_SHORT).show();
			break;
		}
	}
	
	private void goBannerWallNew(){
		Intent fire = new Intent(this, AdBannerWallDemo.class);
		startActivity(fire);
	}
	
	private void goBannerSingleNew1(){
		Intent fire = new Intent(this, AdBannerSingleDemo.class);
		startActivity(fire);
	}
	
	private void goBannerSingleNew0(){
		Intent fire = new Intent(this, AdBannerSingleDemo2.class);
		startActivity(fire);
	}
	
	private void goRectWallNew(){
		Intent fire = new Intent(this, AdRectWallDemo.class);
		startActivity(fire);
	}
	
	private void goRectSingleNew(){
		Intent fire = new Intent(this, AdRectSingleDemo.class);
		startActivity(fire);
	}
	
	private void showTwoPickDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setItems(new CharSequence[]{"Integrated right inside of your app","Integrated above your app"}, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (which == 0){
					goBannerSingleNew0();
				} else if (which == 1){
					goBannerSingleNew1();
				}
			}
		});
		builder.show();
	}
}
