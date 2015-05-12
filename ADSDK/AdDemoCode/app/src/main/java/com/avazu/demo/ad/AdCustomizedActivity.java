package com.avazu.demo.ad;

import avazu.nativead.android.ad.AdView;
import avazu.nativead.android.ad.AdViewCreator;
import avazu.nativead.android.ad.AdViewSettings;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdCustomizedActivity extends Activity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
	
	private TextView mStyleShow,mSizeOrNumShow,mSizeOrNumName,mWbcShow,mBbcShow,mAtcShow,mBbc1Show,mBtcShow;
	private CheckBox mBtn,mCat,mIcon,mInstalls,mRating,mReviewNums,mSize,mTitle;
	private boolean mIsNeedBtn,mIsNeedCat,mIsNeedIcon,mIsNeedInstalls,mIsNeedRating,mIsNeedReviewNums,mIsNeedSize,mIsNeedTitle;
	private RelativeLayout mAdShowLayout;
	private View mMainSettingLayout;
	private int currentFormat,chosedFormat;
	private String mWbc = "";
	private String mBbc = "";
	private String mAtc = "";
	private String mBbc1 = "";
	private String mBtc = "";
	
	private int defWidth,defHeight,defAppNums;
	private int width,height,appNums;
	private int style;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent incoming = getIntent();
		if (incoming == null){
			finish();
			return;
		}
		style = incoming.getIntExtra(Utils.Constants.BUNDLE_KEY_ADSTYLE, -1);
		if (style < 0){
			finish();
			return;
		}
		setContentView(R.layout.ad_customized_layout);
		findViews();
		setListeners();
		caculateDefValues();
	}

	private void caculateDefValues(){
		defAppNums = 6;
		TextView titleView = (TextView) findViewById(R.id.show_ad_title);
		if (style == Utils.Constants.STYLE_RECT){
			defWidth = 120;
			defHeight = 165;
			titleView.setText(R.string.title_rect_customized);
		} else if (style == Utils.Constants.STYLE_BANNER){
			int sw = getResources().getDisplayMetrics().widthPixels;
			int swD = Utils.px2dp(this, sw);
			int hD = swD*100/360;
			defWidth = swD;
			defHeight = hD;
			mBtn.setChecked(true);
			mCat.setChecked(true);
			mInstalls.setChecked(true);
			mReviewNums.setChecked(true);
			mSize.setChecked(true);
			titleView.setText(R.string.title_banner_customized);
		}
		width = defWidth;
		height = defHeight;
		mStyleShow.setText(R.string.go_ad_single);
		mSizeOrNumShow.setText(defWidth + getString(R.string.muti) + defHeight);
		mIcon.setChecked(true);
		mRating.setChecked(true);
		mTitle.setChecked(true);
	}
	
	private void findViews(){
		mStyleShow = (TextView) findViewById(R.id.style_form_show);
		mSizeOrNumName = (TextView) findViewById(R.id.size_or_num_name);
		mSizeOrNumShow = (TextView) findViewById(R.id.size_or_num_show);
		mWbcShow = (TextView) findViewById(R.id.ad_wbc_show);
		mBbcShow = (TextView) findViewById(R.id.ad_bbc_show);
		mAtcShow = (TextView) findViewById(R.id.ad_atc_show);
		mBbc1Show = (TextView) findViewById(R.id.ad_bbc1_show);
		mBtcShow = (TextView) findViewById(R.id.ad_btc_show);
		
		mBtn = ((CheckBox)findViewById(R.id.need_btn));
		mCat = ((CheckBox)findViewById(R.id.need_cat));
		mIcon = ((CheckBox)findViewById(R.id.need_icon));
		mInstalls = ((CheckBox)findViewById(R.id.need_installs));
		mRating = ((CheckBox)findViewById(R.id.need_rating));
		mReviewNums = ((CheckBox)findViewById(R.id.need_review_nums));
		mSize = ((CheckBox)findViewById(R.id.need_size));
		mTitle = ((CheckBox)findViewById(R.id.need_title));
		
		mAdShowLayout = (RelativeLayout) findViewById(R.id.ad_show_layout);
		mMainSettingLayout = findViewById(R.id.main_setting_layout);
	}
	
	private void setListeners(){
		findViewById(R.id.style_chose_layout).setOnClickListener(this);
		findViewById(R.id.size_or_num_set).setOnClickListener(this);
		findViewById(R.id.wbc_set_layout).setOnClickListener(this);
		findViewById(R.id.bbc_set_layout).setOnClickListener(this);
		findViewById(R.id.atc_set_layout).setOnClickListener(this);
		findViewById(R.id.bbc1_set_layout).setOnClickListener(this);
		findViewById(R.id.btc_set_layout).setOnClickListener(this);
		findViewById(R.id.show_setting).setOnClickListener(this);
		findViewById(R.id.show_preview).setOnClickListener(this);
		findViewById(R.id.back).setOnClickListener(this);
		
		mBtn.setOnCheckedChangeListener(this);
		mCat.setOnCheckedChangeListener(this);
		mIcon.setOnCheckedChangeListener(this);
		mInstalls.setOnCheckedChangeListener(this);
		mRating.setOnCheckedChangeListener(this);
		mReviewNums.setOnCheckedChangeListener(this);
		mSize.setOnCheckedChangeListener(this);
		mTitle.setOnCheckedChangeListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.style_chose_layout:
			showFormatChoseDialog();
			break;

		case R.id.size_or_num_set:
			if (currentFormat == 0){
				showSizeInputDialog();
			}else if (currentFormat == 1){
				showAppNumsInputDialog();
			}
			break;
		case R.id.wbc_set_layout:
			showColorInputDialog(R.string.wbc, WBC);
			break;
		case R.id.bbc_set_layout:
			showColorInputDialog(R.string.bbc, BBC);
			break;
		case R.id.atc_set_layout:
			showColorInputDialog(R.string.atc, ATC);
			break;
		case R.id.bbc1_set_layout:
			showColorInputDialog(R.string.bbc1, BBC1);
			break;
		case R.id.btc_set_layout:
			showColorInputDialog(R.string.btc, BTC);
			break;
		case R.id.show_setting:
			showSettingView();
			break;
		case R.id.show_preview:
			showAdView();
			break;
		case R.id.back:
			finish();
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		int id = buttonView.getId();
		switch (id) {
		case R.id.need_btn:
			mIsNeedBtn = isChecked;
			break;
		case R.id.need_cat:
			mIsNeedCat = isChecked;
			break;
		case R.id.need_icon:
			mIsNeedIcon = isChecked;
			break;
		case R.id.need_installs:
			mIsNeedInstalls = isChecked;
			break;
		case R.id.need_rating:
			mIsNeedRating = isChecked;
			break;
		case R.id.need_review_nums:
			mIsNeedReviewNums = isChecked;
			break;
		case R.id.need_size:
			mIsNeedSize = isChecked;
			break;
		case R.id.need_title:
			mIsNeedTitle = isChecked;
			break;
		}
	}
	
	private void showFormatChoseDialog(){
		chosedFormat = currentFormat;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.show_type).setSingleChoiceItems(new CharSequence[]{"Single Line","Multiple Lines"}, currentFormat, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				chosedFormat = which;
			}
		}).setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (chosedFormat == currentFormat){
					return;
				}
				currentFormat = chosedFormat;
				if (currentFormat == 0){
					onSingleFormat();
				}else{
					onWallFormat();
				}
			}
		}).setNegativeButton("CANCEL", null);
		builder.show();
	}
	
	private void onSingleFormat(){
		mStyleShow.setText(R.string.go_ad_single);
		mSizeOrNumName.setText(R.string.ad_view_size);
		if (width == 0 || height == 0){
			width = defWidth;
			height = defHeight;
		}
		mSizeOrNumShow.setText(width + getString(R.string.muti) + height);
	}
	
	private void onWallFormat(){
		mStyleShow.setText(R.string.go_ad_wall);
		mSizeOrNumName.setText(R.string.ad_nums);
		if (appNums == 0){
			appNums = defAppNums;
		}
		mSizeOrNumShow.setText(String.valueOf(appNums));
	}
	
	private void showSizeInputDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View v = View.inflate(this, R.layout.view_two_edit, null);
		builder.setTitle(R.string.ad_view_size).setView(v).setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				EditText widthInput = (EditText) v.findViewById(R.id.width_input);
				EditText heightInput = (EditText) v.findViewById(R.id.height_input);
				String w = widthInput.getText().toString().trim();
				String h = heightInput.getText().toString().trim();
				
				if (TextUtils.isEmpty(w) || TextUtils.isEmpty(h)){
					return;
				}
				try {
					int wi = Integer.valueOf(w);
					int hi = Integer.valueOf(h);
					if (wi > 0 && hi > 0){
						width = wi;
						height = hi;
					}
					mSizeOrNumShow.setText(width + getString(R.string.muti) + height);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}).setNegativeButton("CANCEL", null);
		builder.show();
	}
	
	private void showAppNumsInputDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final EditText editText = new EditText(this);
		editText.setInputType(InputType.TYPE_CLASS_NUMBER);
		builder.setTitle(R.string.ad_nums).setView(editText)
			.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String input = editText.getText().toString();
					if (TextUtils.isEmpty(input)){
						return;
					}
					try {
						int newNums = Integer.valueOf(input);
						if (newNums <= 0){
							Toast.makeText(getApplicationContext(), "App nums can not be 0", Toast.LENGTH_SHORT).show();
						}else{
							appNums = newNums;
							mSizeOrNumShow.setText(String.valueOf(appNums));
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			})
			.setNegativeButton("CANCEL", null);
		builder.show();
	}
	
	private void showColorInputDialog(int titleResId, int color_type){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		EditText editText = (EditText) View.inflate(this, R.layout.view_edit_text, null);
		builder.setTitle(titleResId).setView(editText)
			.setPositiveButton("SAVE", new ColorInputDialogClickListener(color_type, editText))
			.setNegativeButton("CANCEL", null);
		builder.show();
	}
	
	private void showAdView(){
		mMainSettingLayout.setVisibility(View.GONE);
		mAdShowLayout.setVisibility(View.VISIBLE);
		int width_dip = -1;
		int height_dip = -1;
		if(currentFormat == 0){
			width_dip = width;
			height_dip = height;
		}
		int show_type = 0;
		if (style == Utils.Constants.STYLE_BANNER){
			if (currentFormat == 0){
				show_type = AdViewSettings.TYPE_BANNER_SINGLE;
			}else{
				show_type = AdViewSettings.TYPE_BANNER_WALL;
			}
		}else{
			if (currentFormat == 0){
				show_type = AdViewSettings.TYPE_RECT_SINGLE;
			}else{
				show_type = AdViewSettings.TYPE_RECT_WALL;
			}
		}
		AdViewSettings controller = new AdViewSettings(width_dip, height_dip, show_type, true);
		controller.setAppCount(appNums);
		controller.setNeedBtn(mIsNeedBtn);
		controller.setNeedCat(mIsNeedCat);
		controller.setNeedIcon(mIsNeedIcon);
		controller.setNeedInstalls(mIsNeedInstalls);
		controller.setNeedRating(mIsNeedRating);
		controller.setNeedReviewNum(mIsNeedReviewNums);
		controller.setNeedSize(mIsNeedSize);
		controller.setNeedTitle(mIsNeedTitle);
		controller.setMainBackColor(TextUtils.isEmpty(mWbc)?mWbc:"#"+mWbc);
		controller.setBlockBackColor(TextUtils.isEmpty(mBbc)?mBbc:"#"+mBbc);
		controller.setAppTitleColor(TextUtils.isEmpty(mAtc)?mAtc:"#"+mAtc);
		controller.setButtonBackColor(TextUtils.isEmpty(mBbc1)?mBbc1:"#"+mBbc1);
		controller.setButtonTextColor(TextUtils.isEmpty(mBtc)?mBtc:"#"+mBtc);
		AdView adView = AdViewCreator.createAdView(this, controller);
		int width = controller.getWidthDip() > 0 ? Utils.dip2px(this, controller.getWidthDip()) : getResources().getDisplayMetrics().widthPixels;
		int height = controller.getHeightDip() > 0 ? Utils.dip2px(this, controller.getHeightDip()) : getResources().getDisplayMetrics().heightPixels;
		RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(width, height);
		adViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		mAdShowLayout.addView(adView, adViewLayoutParams);
	}
	
	private void showSettingView(){
		mAdShowLayout.removeAllViews();
		mAdShowLayout.setVisibility(View.GONE);
		mMainSettingLayout.setVisibility(View.VISIBLE);
	}
	
	private static final int WBC = 1;
	private static final int BBC = 2;
	private static final int ATC = 3;
	private static final int BBC1 = 4;
	private static final int BTC = 5;
	
	private class ColorInputDialogClickListener implements DialogInterface.OnClickListener{

		private int mType;
		private EditText mInputBox;
		
		private ColorInputDialogClickListener(int type, EditText input){
			this.mType = type;
			this.mInputBox = input;
		}
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			String input = mInputBox.getText().toString().trim();
			if (input.length() != 6 && input.length() != 0){
				Toast.makeText(getApplicationContext(), "Please input correct color setting", Toast.LENGTH_SHORT).show();
				return;
			}
			switch (mType) {
			case WBC:
				mWbc = input;
				mWbcShow.setText("#"+input);
				break;

			case BBC:
				mBbc = input;
				mBbcShow.setText("#"+input);
				break;
				
			case ATC:
				mAtc = input;
				mAtcShow.setText("#"+input);
				break;
				
			case BBC1:
				mBbc1 = input;
				mBbc1Show.setText("#"+input);
				break;
				
			case BTC:
				mBtc = input;
				mBtcShow.setText("#"+input);
				break;
			}
		}
		
	}
}
