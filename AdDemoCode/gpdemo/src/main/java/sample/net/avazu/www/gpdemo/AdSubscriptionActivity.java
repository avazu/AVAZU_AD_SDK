package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nativesdk.ad.adsdk.AdSdk;
import nativesdk.ad.adsdk.app.Constants;
import nativesdk.ad.adsdk.app.FetchRawDataListener;
import nativesdk.ad.adsdk.common.network.data.FetchAdResult;
import nativesdk.ad.adsdk.common.utils.L;


/**
 * Created by csc on 15/8/21.
 */
public class AdSubscriptionActivity extends Activity implements AdSubscriptionListener {
    TextView textView;

    private static final String TAG = "AdSubscription";

    private String clickUrl;

    private Context mContext;

    private AdSubscriptionManager mAdSubscriptionManager;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.demo_raw_data);
        textView = (TextView) findViewById(R.id.raw_data_title);
        textView.setText("Subscription Ad");

        dialog = new ProgressDialog(this);
        dialog.setMessage("loading...");
        dialog.setCanceledOnTouchOutside(false);

        mAdSubscriptionManager = new AdSubscriptionManager(mContext);

        /** 由于中国地区无法显示订阅类广告，所以在调试时可以将  Constants.SUBSCRIPTION_AD_DEBUG 设为true**/

        /** release版本时切记将  Constants.SUBSCRIPTION_AD_DEBUG 设为true*  这段代码去掉**/
        Constants.SUBSCRIPTION_AD_DEBUG = true;

        AdSdk.getAdRawData(getApplicationContext(), "15887", "", 5, 1, "optin", listener);
    }

    FetchRawDataListener listener = new FetchRawDataListener() {
        @Override
        public void onLoadRawDataSuccess(List<FetchAdResult.Ad> arg0) {
            if (arg0 == null) {
                return;
            }
            List<FetchAdResult.Ad> rawDataList = new ArrayList<FetchAdResult.Ad>();
            rawDataList = arg0;
            /** get random url**/
            /** sample中为了方便直接拉取一个随即的订阅类广告url, 实际使用中可以从数据库中拉取**/
            FetchAdResult.Ad ad = rawDataList.get(new Random().nextInt(rawDataList.size() - 0) + 0);
            mAdSubscriptionManager.init(ad.clickURL);
            mAdSubscriptionManager.loadAd();
        }

        @Override
        public void onLoadRawDataStart() {
            dialog.show();
            L.d(TAG, "onLoadRawDataStart");
        }

        @Override
        public void onLoadRawDataFail(Error mError) {
            dialog.dismiss();
            L.d(TAG, "onLoadRawDataFail");
        }
    };

    @Override
    public void onAdDismiss() {
        L.d(TAG, "onAdDismiss");
        dialog.dismiss();
    }

    @Override
    public void onAdLoadFailed() {
        L.d(TAG, "onAdDismiss");
        dialog.dismiss();
    }


    @Override
    public void onAdLoadSuccess(){
        L.d(TAG, "onAdDismiss");
        dialog.dismiss();
    }
}

