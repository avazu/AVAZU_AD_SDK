package com.avazu.demo.ad;

import avazu.nativead.android.ad.AdView;
import avazu.nativead.android.ad.AdViewCreator;
import avazu.nativead.android.ad.AdViewSettings;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

public class AdBannerSingleDemo extends Activity {
	
	private RelativeLayout mAdLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_ad_banner_single);
		
		mAdLayout = (RelativeLayout) findViewById(R.id.ad_layout);
		showAd();
	}
	
	private void showAd(){
		AdViewSettings controller = new AdViewSettings(400, 110, AdViewSettings.TYPE_BANNER_TRANSPARENT, false);
		controller.setNeedIcon(true);
		controller.setNeedBtn(true);
		controller.setNeedCat(true);
		controller.setNeedInstalls(true);
		controller.setNeedRating(true);
		controller.setNeedReviewNum(true);
		controller.setNeedTitle(true);
		controller.setAppTitleColor("#FFFFFF");
		controller.setMainBackColor("#f5f5f5");
		controller.setBlockBackColor("#080807");
		controller.setAlpha(80);
		AdView adView = AdViewCreator.createAdView(this, controller);
		RelativeLayout.LayoutParams adLayoutParams = new RelativeLayout.LayoutParams(
				Utils.dip2px(this, 400), Utils.dip2px(this, 110));
		
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int sw = dm.widthPixels;
		int sh = dm.heightPixels - Utils.getStatusBarHeight(this);
		int margin = (sh - 669 * sw / 1280) / 2;
		if (margin < 0){
			margin = 0;
		}
		adLayoutParams.bottomMargin = margin;
		mAdLayout.addView(adView, adLayoutParams);
	}
}
