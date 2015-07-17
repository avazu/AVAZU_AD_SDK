package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import nativesdk.ad.adcore.modules.webviewad.AdViewSettings;
import nativesdk.ad.adcore.modules.webviewad.AvazuAdView;


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
		AdViewSettings adSettings =
				new AdViewSettings(RelativeLayout.LayoutParams.MATCH_PARENT,
						RelativeLayout.LayoutParams.MATCH_PARENT, AdViewSettings.TYPE_BANNER_WALL, true);
		adSettings.setAppCount(6);
		adSettings.setNeedIcon(true);
		adSettings.setNeedRating(true);
		adSettings.setNeedReviewNum(true);
		adSettings.setNeedTitle(true);
		adSettings.setNeedBtn(true);
		adSettings.setNeedInstalls(true);
		adSettings.setNeedCat(true);
		adSettings.setMainBackColor("#F5F5F5");
		adSettings.setBlockBackColor("#FFFFFF");
		adSettings.setAppTitleColor("#333333");
		AvazuAdView adView = new AvazuAdView(this, adSettings);
		adView.setSourceId("15887");
		adView.loadWebviewAd();
		RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		mLayout.addView(adView, adViewLayoutParams);
	}
}
