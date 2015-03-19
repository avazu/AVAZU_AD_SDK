package com.avazu.demo.ad;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.avazu.ad.AdView;
import com.avazu.ad.AdViewController;
import com.avazu.ad.AdViewFactory;
import com.avazu.ad.AdViewStateListener;
import com.avazu.ad.utils.L;

public class AdBannerWallDemo extends Activity implements AdViewStateListener{

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
		AdViewController controller = 
				new AdViewController(RelativeLayout.LayoutParams.MATCH_PARENT, 
						RelativeLayout.LayoutParams.MATCH_PARENT, AdViewController.TYPE_BANNER_WALL, true);
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
		AdView adView = AdViewFactory.createAdView(this, controller);
		adView.setAdViewStateListener(this);
		RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		mLayout.addView(adView, adViewLayoutParams);
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
