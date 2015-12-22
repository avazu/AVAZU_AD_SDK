package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import nativesdk.ad.adsdk.modules.webviewad.AvazuAdView;


public class AdRectSingleDemo extends Activity {
	
	private ImageView mImg;
	private RelativeLayout mLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_ad_rect_single);
		mImg = (ImageView) findViewById(R.id.image_up);
		mLayout = (RelativeLayout) findViewById(R.id.ad_layout);
		resizeImg();
		showAd();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}
	
	private void resizeImg(){
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		int height1 = screenWidth * 638 / 720;
		ViewGroup.LayoutParams img1LayoutParams = mImg.getLayoutParams();
		img1LayoutParams.width = screenWidth;
		img1LayoutParams.height = height1;
		mImg.setLayoutParams(img1LayoutParams);
		
	}

	private void showAd(){
//		mLayout.setPadding(Tools.dip2px(this, 10), 0, Tools.dip2px(this, 10), 0);
		mLayout.addView(createSingleAdView());
	}
	
	private AvazuAdView createSingleAdView(){
		Log.i("SDK","createSingleAdView");
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		int screenWidthDip = Utils.px2dp(this, screenWidth);
		int showWidthDip = screenWidthDip * 2 ;
		int showHeightDip = showWidthDip * 175 / (97 * 6);

		AvazuAdView adView = new AvazuAdView(this, AvazuAdView.SINGLE_LINE_RECTANGLE, "15887", showWidthDip, showHeightDip);
		adView.setAdCatagoryVisible(false);
		adView.setAdSizeVisible(false);
		adView.setAdInstallButtonVisible(false);
		adView.setAdReviewNumberVisible(false);
		adView.setAdNumer(6);
		adView.loadWebviewAd(this);
		adView.setLayoutParams(createLayoutParams(Utils.dip2px(this, showWidthDip + 1),
				Utils.dip2px(this, showHeightDip + 1)));
		return adView;
	}
	
	private RelativeLayout.LayoutParams createLayoutParams(int width,int height){
		RelativeLayout.LayoutParams ret = new RelativeLayout.LayoutParams(width, height);
		return ret;
	}
}
