package com.avazu.demo.ad;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import avazu.nativead.android.ad.AdView;
import avazu.nativead.android.ad.AdViewCreator;
import avazu.nativead.android.ad.AdViewSettings;

public class AdBannerWallDemo extends Activity {

	private ImageView mImg1/*,mImg2*/;
	private RelativeLayout mLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_ad_banner_wall);
		
		mImg1 = (ImageView) findViewById(R.id.image1);
//		mImg2 = (ImageView) findViewById(R.id.image2);
		
		mLayout = (RelativeLayout) findViewById(R.id.ad_layout_demo);
		
		resizeImage();
		showAd();
	}
	
	private void resizeImage(){
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		int height1 = screenWidth * 638 / 720;
		ViewGroup.LayoutParams img1LayoutParams = mImg1.getLayoutParams();
		img1LayoutParams.width = screenWidth;
		img1LayoutParams.height = height1;
		mImg1.setLayoutParams(img1LayoutParams);
		
		/*int height2 = screenWidth * 210 / 720;
		ViewGroup.LayoutParams img2LayoutParams = mImg2.getLayoutParams();
		img2LayoutParams.width = screenWidth;
		img2LayoutParams.height = height2;
		mImg2.setLayoutParams(img2LayoutParams);*/
	}
	
	private void showAd(){
		AdViewSettings controller = 
				new AdViewSettings(RelativeLayout.LayoutParams.MATCH_PARENT, 
						RelativeLayout.LayoutParams.MATCH_PARENT, AdViewSettings.TYPE_BANNER_WALL, true);
		controller.setAppCount(6);
		controller.setNeedIcon(true);
		controller.setNeedRating(true);
		controller.setNeedReviewNum(true);
		controller.setNeedTitle(true);
		controller.setNeedBtn(true);
		controller.setNeedInstalls(true);
		controller.setNeedCat(true);
		controller.setMainBackColor("#F5F5F5");
		controller.setBlockBackColor("#FFFFFF");
		controller.setAppTitleColor("#333333");
		AdView adView = AdViewCreator.createAdView(this, controller);
		RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		mLayout.addView(adView, adViewLayoutParams);
	}
}
