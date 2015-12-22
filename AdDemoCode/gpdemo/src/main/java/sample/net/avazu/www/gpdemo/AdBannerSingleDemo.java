package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import nativesdk.ad.adsdk.app.AdViewStateListener;
import nativesdk.ad.adsdk.common.utils.L;
import nativesdk.ad.adsdk.modules.webviewad.AvazuAdView;


public class AdBannerSingleDemo extends Activity implements AdViewStateListener {
	
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
		AvazuAdView adView = new AvazuAdView(this, AvazuAdView.SINGLE_TRANSPARENT_BANNER, "15887", 400, 110);
		adView.setAdTitleColor("#FFFFFF");
		adView.setAdMainBackgroundColor("0");
		adView.setAdBlockBackgroundColor("#080807");
		adView.setTransparentBannerAlpha(80);
//		adView.setAdLoadingIndicatorVisibility(false);
		adView.loadWebviewAd(this);
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
		adView.setAdViewStateListener(this);
		mAdLayout.addView(adView, adLayoutParams);
	}

	@Override
	public void onLoadAdStart(AvazuAdView view) {
		L.d("onLoadAdStart");
	}

	@Override
	public void onLoadAdFinish(AvazuAdView view) {
		L.d("onLoadAdFinish");
	}

	@Override
	public boolean onLoadAdError(AvazuAdView view, String error) {
		L.d("onLoadAdError");
		return true;
	}
}
