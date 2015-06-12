package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import nativesdk.ad.adcore.modules.webviewad.AdViewSettings;
import nativesdk.ad.adcore.modules.webviewad.AvazuAdView;

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
		AdViewSettings adSettings = incoming.getParcelableExtra(Utils.Constants.BUNDLE_KEY_ADVIEWCONTROLLER);
		if (adSettings == null){
			finish();
			return;
		}
		
		setContentView(R.layout.ad_show_layout);
		setShowTitle(adSettings.getShowType());
		findViewById(R.id.back).setOnClickListener(this);
		
		adLayout = (RelativeLayout) findViewById(R.id.ad_layout);
		AvazuAdView adView = new AvazuAdView(this, adSettings);
		int width = adSettings.getWidthDip() > 0 ? Utils.dip2px(this, adSettings.getWidthDip()) : adSettings.getWidthDip();
		int height = adSettings.getHeightDip() > 0 ? Utils.dip2px(this, adSettings.getHeightDip()) : adSettings.getHeightDip();
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
