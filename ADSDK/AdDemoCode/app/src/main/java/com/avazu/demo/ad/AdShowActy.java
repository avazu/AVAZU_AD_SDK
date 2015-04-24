package com.avazu.demo.ad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import avazu.nativead.android.ad.AdView;
import avazu.nativead.android.ad.AdViewCreator;
import avazu.nativead.android.ad.AdViewSettings;

public class AdShowActy extends Activity implements View.OnClickListener{
	
	private RelativeLayout adLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent incoming = getIntent();
		if (incoming == null){
			finish();
			return ;
		}
		AdViewSettings controller = incoming.getParcelableExtra(Utils.Constants.BUNDLE_KEY_ADVIEWCONTROLLER);
		if (controller == null){
			finish();
			return;
		}
		
		setContentView(R.layout.ad_show_layout);
		setShowTitle(controller.getShowType());
		findViewById(R.id.back).setOnClickListener(this);
		
		adLayout = (RelativeLayout) findViewById(R.id.ad_layout);
		AdView adView = AdViewCreator.createAdView(this, controller);
		int width = controller.getWidthDip() > 0 ? Utils.dip2px(this, controller.getWidthDip()) : controller.getWidthDip();
		int height = controller.getHeightDip() > 0 ? Utils.dip2px(this, controller.getHeightDip()) : controller.getHeightDip();
		RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(width, height);
		adViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		adLayout.addView(adView, adViewLayoutParams);
	}

	private void setShowTitle(int show_type){
		TextView titleView = (TextView) findViewById(R.id.show_ad_title);
		int resId = -1;
		switch (show_type) {
		case AdViewSettings.TYPE_RECT_SINGLE:
			resId = R.string.title_rect_single;
			break;
			
		case AdViewSettings.TYPE_RECT_WALL:
			resId = R.string.title_rect_wall;
			break;
			
		case AdViewSettings.TYPE_BANNER_SINGLE:
			resId = R.string.title_banner_single;
			break;
			
		case AdViewSettings.TYPE_BANNER_WALL:
			resId = R.string.title_banner_wall;
			break;
		
		}
		if (resId > 0 && titleView != null){
			titleView.setText(resId);
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.back:
			finish();
			break;

		default:
			break;
		}
	}
}
