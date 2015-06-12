package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import nativesdk.ad.adcore.modules.webviewad.AdViewSettings;
import nativesdk.ad.adcore.modules.webviewad.AvazuAdView;


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
		AdViewSettings adSettings = new AdViewSettings(400, 110, AdViewSettings.TYPE_BANNER_TRANSPARENT, false);
		adSettings.setNeedIcon(true);
		adSettings.setNeedBtn(true);
		adSettings.setNeedCat(true);
		adSettings.setNeedInstalls(true);
		adSettings.setNeedRating(true);
		adSettings.setNeedReviewNum(true);
		adSettings.setNeedTitle(true);
		adSettings.setAppTitleColor("#FFFFFF");
		adSettings.setMainBackColor("#f5f5f5");
		adSettings.setBlockBackColor("#080807");
		adSettings.setAlpha(80);
		AvazuAdView adView = new AvazuAdView(this, adSettings);
		adView.loadWebviewAd();
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
