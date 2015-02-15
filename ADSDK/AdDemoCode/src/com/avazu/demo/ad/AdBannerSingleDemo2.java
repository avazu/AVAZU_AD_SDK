package com.avazu.demo.ad;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.avazu.ad.AdView;
import com.avazu.ad.AdViewController;
import com.avazu.ad.AdViewFactory;
import com.avazu.ad.utils.Tools;

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
		int screenWidthDip = Tools.px2dp(this, screenWidth);
		int showHeightDip = screenWidthDip * 100 / 360;
		AdViewController controller = new AdViewController(screenWidthDip,
				showHeightDip, AdViewController.TYPE_BANNER_SINGLE, true);
		controller.setNeedBtn(true);
		controller.setNeedCat(true);
		controller.setNeedIcon(true);
		controller.setNeedInstalls(true);
		controller.setNeedRating(true);
		controller.setNeedReviewNum(true);
		controller.setNeedTitle(true);
		AdView adView = AdViewFactory.createAdView(this, controller);
		RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(
				screenWidth, Tools.dip2px(this, showHeightDip));
		mAdLayout.addView(adView, adViewLayoutParams);
	}
	
}
