package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import nativesdk.ad.adcore.common.utils.L;
import nativesdk.ad.adcore.modules.webviewad.AdViewSettings;
import nativesdk.ad.adcore.modules.webviewad.AdViewStateListener;
import nativesdk.ad.adcore.modules.webviewad.AvazuAdView;


public class AdBannerSingleDemo2 extends Activity implements AdViewStateListener {
	
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
		AdViewSettings adSettings = new AdViewSettings(screenWidthDip,
				showHeightDip, AdViewSettings.TYPE_BANNER_SINGLE, true);
		adSettings.setNeedBtn(true);
		adSettings.setNeedCat(true);
		adSettings.setNeedIcon(true);
		adSettings.setNeedInstalls(true);
		adSettings.setNeedRating(true);
		adSettings.setNeedReviewNum(true);
		adSettings.setNeedTitle(true);
		AvazuAdView adView = new AvazuAdView(this, adSettings);
		adView.loadWebviewAd();
		adView.setAdViewStateListener(this);
		RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(
				screenWidth, Utils.dip2px(this, showHeightDip));
		mAdLayout.addView(adView, adViewLayoutParams);
	}
	@Override
	public void onLoadAdStart(AvazuAdView view) {
		L.d("onLoadAdStart");
	}

	@Override
	public void onLoadAdFinish(AvazuAdView view, int adCount) {
		L.d("onLoadAdFinish");
	}

	@Override
	public boolean onLoadAdError(AvazuAdView view, String error) {
		L.d("onLoadAdError");
		return true;
	}
}
