package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import nativesdk.ad.adsdk.modules.webviewad.AvazuAdView;


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
	}
	
	private void showAd(){
		AvazuAdView adView = new AvazuAdView(this, AvazuAdView.MULTIPLE_LINE_BANNER, "15887", RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		adView.setAdMainBackgroundColor("#F5F5F5");
		adView.setAdTitleColor("#333333");
		adView.setAdBlockBackgroundColor("#FFFFFF");
		adView.loadWebviewAd(this);
		RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		mLayout.addView(adView, adViewLayoutParams);
	}
}
