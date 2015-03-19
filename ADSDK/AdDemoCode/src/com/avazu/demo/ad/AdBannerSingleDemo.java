package com.avazu.demo.ad;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import com.avazu.ad.AdView;
import com.avazu.ad.AdViewController;
import com.avazu.ad.AdViewFactory;
import com.avazu.ad.AdViewStateListener;
import com.avazu.ad.utils.L;

public class AdBannerSingleDemo extends Activity implements AdViewStateListener{
	
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
		AdViewController controller = new AdViewController(400, 110, AdViewController.TYPE_BANNER_TRANSPARENT, false);
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
		AdView adView = AdViewFactory.createAdView(this, controller);
		adView.setAdViewStateListener(this);
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
	
	@Override
	public void onLoadAdStart(AdView view) {
		// TODO Auto-generated method stub
		L.d(toString() + "++++++++start");
	}

	@Override
	public void onLoadAdFinish(AdView view, int adCount) {
		// TODO Auto-generated method stub
		L.d(toString() + "++++++++finish+++count: "+adCount);
	}

	@Override
	public boolean onLoadAdError(AdView view, String error) {
		// TODO Auto-generated method stub
		L.d(toString() + "++++++++error+++detail: "+error);
		return true;
	}
}
