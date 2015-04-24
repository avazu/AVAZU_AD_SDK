package com.avazu.demo.ad;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import avazu.nativead.android.ad.AdView;
import avazu.nativead.android.ad.AdViewCreator;
import avazu.nativead.android.ad.AdViewSettings;

public class AdRectWallDemo extends Activity {
	
	private ImageView mImg;
	private RelativeLayout mLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_ad_rect_wall);
		
		mImg = (ImageView) findViewById(R.id.left_img);
		mLayout = (RelativeLayout) findViewById(R.id.ad_layout);
		resizeImage();
		showAd();
	}
	
	private void resizeImage(){
//		int screenHeight = getResources().getDisplayMetrics().heightPixels;
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		int widthDip = Utils.px2dp(this, screenWidth);
		int imgWidthDip = widthDip - 245;
		int imgHeightDip = imgWidthDip * 670 / 721;
		/*int statusHeight = Utils.getStatusBarHeight(this);
		int showHeight = screenHeight - statusHeight;
		int showWidth = 721 * showHeight / 670;*/
		
		ViewGroup.LayoutParams imgLayoutParams = mImg.getLayoutParams();
		imgLayoutParams.height = Utils.dip2px(this, imgHeightDip);
		imgLayoutParams.width = Utils.dip2px(this, imgWidthDip);
		mImg.setLayoutParams(imgLayoutParams);
	}
	
	private void showAd(){
		AdViewSettings controller = new AdViewSettings(-1, -1, AdViewSettings.TYPE_RECT_WALL, true);
		controller.setNeedIcon(true);
		controller.setNeedTitle(true);
		controller.setNeedRating(true);
		controller.setNeedReviewNum(true);
		controller.setAppCount(12);
		AdView adView = AdViewCreator.createAdView(this, controller);
		RelativeLayout.LayoutParams adLayoutParams = new RelativeLayout.LayoutParams(-1,-1);
		mLayout.addView(adView, adLayoutParams);
	}
	
	
}
