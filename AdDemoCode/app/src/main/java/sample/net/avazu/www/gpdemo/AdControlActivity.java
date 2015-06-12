package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

import nativesdk.ad.adcore.modules.webviewad.AdViewSettings;


public class AdControlActivity extends Activity implements OnClickListener,OnCheckedChangeListener{
	
	public static final int STYLE_RECT = 1;
	public static final int STYLE_BANNER = 2;
	
	private EditText mWidthInput,mHeightInput,mWbcInput,mBbcInput,mAtcInput,mBbc1Input,mBtcInput,mAppCountInput;
	private CheckBox mIconSet,mTitleSet,mRatingSet,mSizeSet,mCatSet,mBtnSet,mInstallsSet,mReviewsNumSet;
	private Button mConfirm;
	@SuppressWarnings("unused")
	private View mSizeSettingLayout,mElementsSettingLayout,mColorSettingLayout,mBannerElementsLayout;
	private boolean mIsNeedIcon,mIsNeedTtile,mIsNeedRating,mIsNeedCat,mIsNeedSize,mIsNeedBtn,mIsNeedInstalls,mIsNeedReviewsNum;
	private int mShowType,mAdWidth,mAdHeight,mAppCount,mStyle;
	private String mWbc,mBbc,mAtc,mBbc1,mBtc;
	private RadioGroup mShowTypeChoose;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Intent data = getIntent();
		if (data == null){
			finish();
			return;
		}
		mStyle = data.getIntExtra(Utils.Constants.BUNDLE_KEY_ADSTYLE, -1);
		if (mStyle < 0){
			finish();
			return;
		}
		setContentView(R.layout.ad_control_layout);
		findViews();
		setListeners();
		if (mStyle == STYLE_BANNER){
			findViewById(R.id.show_type_rect_single).setVisibility(View.GONE);
			findViewById(R.id.show_type_rect_wall).setVisibility(View.GONE);
			mBbc1Input.setText(R.string.no_need_set);
			mBbc1Input.setEnabled(false);
			mBtcInput.setText(R.string.no_need_set);
			mBtcInput.setEnabled(false);
			mShowTypeChoose.check(R.id.show_type_banner_single);
		}else if(mStyle == STYLE_RECT){
			findViewById(R.id.show_type_banner_single).setVisibility(View.GONE);
			findViewById(R.id.show_type_banner_wall).setVisibility(View.GONE);
			mBannerElementsLayout.setVisibility(View.GONE);
			mShowTypeChoose.check(R.id.show_type_rect_single);
		}
		
	}
	
	private void findViews(){
		mWidthInput = (EditText) findViewById(R.id.width_set);
		mHeightInput = (EditText) findViewById(R.id.height_set);
		mWbcInput = (EditText) findViewById(R.id.wbc_set);
		mBbcInput = (EditText) findViewById(R.id.bbc_set);
		mAtcInput = (EditText) findViewById(R.id.atc_set);
		mBbc1Input = (EditText) findViewById(R.id.bbc1_set);
		mBtcInput = (EditText) findViewById(R.id.btc_set);
		mAppCountInput = (EditText) findViewById(R.id.appcount_set);
		mIconSet = (CheckBox) findViewById(R.id.need_icon);
		mTitleSet = (CheckBox) findViewById(R.id.need_title);
		mRatingSet = (CheckBox) findViewById(R.id.need_rating);
		mSizeSet = (CheckBox) findViewById(R.id.need_size);
		mCatSet = (CheckBox) findViewById(R.id.need_cat);
		mBtnSet = (CheckBox) findViewById(R.id.need_btn);
		mInstallsSet = (CheckBox) findViewById(R.id.need_installs);
		mReviewsNumSet = (CheckBox) findViewById(R.id.need_review_nums);
		mConfirm = (Button) findViewById(R.id.setting_confirm);
		mSizeSettingLayout = findViewById(R.id.size_setting_layout);
		mElementsSettingLayout = findViewById(R.id.elements_setting_layout);
		mColorSettingLayout = findViewById(R.id.color_setting_layout);
		mShowTypeChoose = (RadioGroup) findViewById(R.id.show_type_chose);
		mBannerElementsLayout = findViewById(R.id.banner_elements);
	}
	
	private void setListeners(){
		mIconSet.setOnCheckedChangeListener(this);
		mTitleSet.setOnCheckedChangeListener(this);
		mRatingSet.setOnCheckedChangeListener(this);
		mSizeSet.setOnCheckedChangeListener(this);
		mCatSet.setOnCheckedChangeListener(this);
		mBtnSet.setOnCheckedChangeListener(this);
		mInstallsSet.setOnCheckedChangeListener(this);
		mReviewsNumSet.setOnCheckedChangeListener(this);
		mConfirm.setOnClickListener(this);
		mShowTypeChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.show_type_rect_single:
					mShowType = AdViewSettings.TYPE_RECT_SINGLE;
					onSingleTypeChose();
					break;

				case R.id.show_type_rect_wall:
					mShowType = AdViewSettings.TYPE_RECT_WALL;
					onWallTypeChose();
					break;
					
				case R.id.show_type_banner_single:
					mShowType = AdViewSettings.TYPE_BANNER_SINGLE;
					onSingleTypeChose();
					break;
					
				case R.id.show_type_banner_wall:
					mShowType = AdViewSettings.TYPE_BANNER_WALL;
					onWallTypeChose();
					break;
				}
			}
		});
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		int id = buttonView.getId();
		switch (id) {
		case R.id.need_icon:
			mIsNeedIcon = isChecked;
			break;
		case R.id.need_title:
			mIsNeedTtile = isChecked;
			break;
		case R.id.need_rating:
			mIsNeedRating = isChecked;
			break;
		case R.id.need_size:
			mIsNeedSize = isChecked;
			break;
		case R.id.need_cat:
			mIsNeedCat = isChecked;
			break;
		case R.id.need_btn:
			mIsNeedBtn = isChecked;
			break;
		case R.id.need_installs:
			mIsNeedInstalls = isChecked;
			break;
		case R.id.need_review_nums:
			mIsNeedReviewsNum = isChecked;
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.setting_confirm:
			if(checkInput()){
				goShowAd();
			}
			break;

		}
	}
	
	private void onSingleTypeChose(){
		mSizeSettingLayout.setVisibility(View.VISIBLE);
		mWbcInput.setText(R.string.no_need_set);
		mWbcInput.setEnabled(false);
		mAppCountInput.setText(R.string.no_need_set);
		mAppCountInput.setEnabled(false);
//		mAppCountInput.setVisibility(View.INVISIBLE);
	}
	
	private void onWallTypeChose(){
		mSizeSettingLayout.setVisibility(View.GONE);
		mWbcInput.setText("");
		mWbcInput.setEnabled(true);
		mAppCountInput.setText("");
		mAppCountInput.setEnabled(true);
		/*mWbcInput.setVisibility(View.VISIBLE);
		mAppCountInput.setVisibility(View.VISIBLE);*/
	}
	
	private boolean checkInput(){
		if (!checkElementsSelect()){
			return false;
		}
		if (!checkColorInput()){
			return false;
		}
		if (!checkSizeInput()){
			return false;
		}
		return true;
	}
	
	private boolean checkElementsSelect(){
		boolean ret = (mIsNeedIcon || mIsNeedCat || mIsNeedBtn || mIsNeedRating || mIsNeedSize || mIsNeedTtile || mIsNeedInstalls || mIsNeedReviewsNum);
		if(!ret){
			Toast.makeText(this, R.string.hint_pls_select_one_element, Toast.LENGTH_SHORT).show();
		}
		if (mShowType == AdViewSettings.TYPE_BANNER_WALL || mShowType == AdViewSettings.TYPE_RECT_WALL){
			try {
				mAppCount = Integer.valueOf(mAppCountInput.getText().toString());
				if (mAppCount <= 0){
					ret = false;
					Toast.makeText(this, R.string.hint_pls_enter_correct_ads_number, Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				// TODO: handle exception
				ret = false;
				Toast.makeText(this, R.string.hint_pls_enter_correct_ads_number, Toast.LENGTH_SHORT).show();
			}
		}
		
		return ret;
	}
	
	private boolean checkSizeInput(){
		

		if (mShowType == AdViewSettings.TYPE_BANNER_WALL || mShowType == AdViewSettings.TYPE_RECT_WALL){
			return true;
		}
		
		String width = mWidthInput.getText().toString();
		if (TextUtils.isEmpty(width)){
			Toast.makeText(this, "please input adView width first!", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		try {
			mAdWidth = Integer.valueOf(width);
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, "please input correct adView width first!", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		String height = mHeightInput.getText().toString();
		if (TextUtils.isEmpty(height)){
			Toast.makeText(this, "please input adView height first!", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		try {
			mAdHeight = Integer.valueOf(height);
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, "please input correct adView height first!", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		return true;
	}
	
	private boolean checkColorInput(){
		
		String noNeedSet = getString(R.string.no_need_set);
		
		mWbc = mWbcInput.getText().toString();
		if (!TextUtils.isEmpty(mWbc) && mWbc.length() != 6 && !mWbc.equals(noNeedSet)){
			Toast.makeText(this, R.string.hint_pls_enter_correct_wbc, Toast.LENGTH_SHORT).show();
			mWbc = "";
			return false;
		}
		
		mBbc = mBbcInput.getText().toString();
		if (!TextUtils.isEmpty(mBbc) && mBbc.length() != 6 && !mBbc.equals(noNeedSet)){
			Toast.makeText(this, R.string.hint_pls_enter_correct_bbc, Toast.LENGTH_SHORT).show();
			mBbc = "";
			return false;
		}
		
		mAtc = mAtcInput.getText().toString();
		if (!TextUtils.isEmpty(mAtc) && mAtc.length() != 6 && !mAtc.equals(noNeedSet)){
			Toast.makeText(this, R.string.hint_pls_enter_correct_atc, Toast.LENGTH_SHORT).show();
			mAtc = "";
			return false;
		}
		
		mBbc1 = mBbc1Input.getText().toString();
		if (!TextUtils.isEmpty(mBbc1) && mBbc1.length() != 6 && !mBbc1.equals(noNeedSet)){
			Toast.makeText(this, R.string.hint_pls_enter_correct_btbc, Toast.LENGTH_SHORT).show();
			mBbc1 = "";
			return false;
		}
		
		mBtc = mBtcInput.getText().toString();
		if (!TextUtils.isEmpty(mBtc) && mBtc.length() != 6 && !mBtc.equals(noNeedSet)){
			Toast.makeText(this, R.string.hint_pls_enter_correct_btc, Toast.LENGTH_SHORT).show();
			mBtc = "";
			return false;
		}
		return true;
	}
	
	private void goShowAd(){
		if (mShowType == AdViewSettings.TYPE_RECT_WALL || mShowType == AdViewSettings.TYPE_BANNER_WALL){
			mAdWidth = -1;
			mAdHeight = -1;
		}
		AdViewSettings controller = new AdViewSettings(mAdWidth, mAdHeight, mShowType, true);
		controller.setNeedIcon(mIsNeedIcon);
		controller.setNeedTitle(mIsNeedTtile);
		controller.setNeedRating(mIsNeedRating);
		controller.setNeedSize(mIsNeedSize);
		controller.setNeedCat(mIsNeedCat);
		controller.setNeedBtn(mIsNeedBtn);
		controller.setNeedInstalls(mIsNeedInstalls);
		controller.setNeedReviewNum(mIsNeedReviewsNum);
		controller.setAppCount(mAppCount);
		
		controller.setMainBackColor(TextUtils.isEmpty(mWbc)?"":"#"+mWbc.toUpperCase(Locale.getDefault()));
		controller.setBlockBackColor(TextUtils.isEmpty(mBbc)?"":"#"+mBbc.toUpperCase(Locale.getDefault()));
		controller.setAppTitleColor(TextUtils.isEmpty(mAtc)?"":"#"+mAtc.toUpperCase(Locale.getDefault()));
		controller.setButtonBackColor(TextUtils.isEmpty(mBbc1)?"":"#"+mBbc1.toUpperCase(Locale.getDefault()));
		controller.setButtonTextColor(TextUtils.isEmpty(mBtc)?"":"#"+mBtc.toUpperCase(Locale.getDefault()));
		Intent fire = new Intent(this, AdShowActy.class);
		fire.putExtra(Utils.Constants.BUNDLE_KEY_ADVIEWCONTROLLER, controller);
		startActivity(fire);
//		finish();
	}
}
