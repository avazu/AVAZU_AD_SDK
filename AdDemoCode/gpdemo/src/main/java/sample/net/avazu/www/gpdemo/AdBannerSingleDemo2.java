package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import nativesdk.ad.adsdk.app.AdViewStateListener;
import nativesdk.ad.adsdk.common.utils.L;
import nativesdk.ad.adsdk.modules.webviewad.AvazuAdView;


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
		AvazuAdView adView = new AvazuAdView(this, AvazuAdView.SINGLE_BANNER, "15887", screenWidthDip, showHeightDip);
		adView.loadWebviewAd(this);
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
	public void onLoadAdFinish(AvazuAdView view) {
		L.d("onLoadAdFinish");
	}

	@Override
	public boolean onLoadAdError(AvazuAdView view, String error) {
		L.d("onLoadAdError");
		return true;
	}
}
