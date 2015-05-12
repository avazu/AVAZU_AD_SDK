package com.avazu.demo.ad;

import avazu.nativead.android.ad.AdView;
import avazu.nativead.android.ad.AdViewCreator;
import avazu.nativead.android.ad.AdViewSettings;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class AdBannerSingleDemo2 extends Activity {
	
	private ImageView mImg;
	private ImageView mImg2;
	private RelativeLayout mAdLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_ad_single_2);
		
		mImg = (ImageView) findViewById(R.id.image_1);
		mImg2 = (ImageView) findViewById(R.id.image_2);
		mAdLayout = (RelativeLayout) findViewById(R.id.ad_layout);
		
		resizeImage();
		showAd();
	}

	private void resizeImage() {
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		int showHeight = 914 * screenWidth / 720;
		ViewGroup.LayoutParams originalParams = mImg.getLayoutParams();
		originalParams.width = screenWidth;
		originalParams.height = showHeight;
		mImg.setLayoutParams(originalParams);
		
		int showH2 = 69 * screenWidth / 720;
		ViewGroup.LayoutParams img2Params = mImg2.getLayoutParams();
		img2Params.width = screenWidth;
		img2Params.height = showH2;
		mImg2.setLayoutParams(img2Params);
	}
	
	private void showAd(){
		int screenWidth = getResources().getDisplayMetrics().widthPixels;;
		int screenWidthDip = Utils.px2dp(this, screenWidth);
		int showHeightDip = screenWidthDip * 100 / 360;
		AdViewSettings controller = new AdViewSettings(screenWidthDip,
				showHeightDip, AdViewSettings.TYPE_BANNER_SINGLE, true);
		controller.setNeedBtn(true);
		controller.setNeedCat(true);
		controller.setNeedIcon(true);
		controller.setNeedInstalls(true);
		controller.setNeedRating(true);
		controller.setNeedReviewNum(true);
		controller.setNeedTitle(true);
		AdView adView = AdViewCreator.createAdView(this, controller);
		RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(
				screenWidth, Utils.dip2px(this, showHeightDip));
		mAdLayout.addView(adView, adViewLayoutParams);
	}
	
}
