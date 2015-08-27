package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import nativesdk.ad.adcore.AdSdk;
import nativesdk.ad.adcore.common.network.data.FetchAdResult;
import nativesdk.ad.adcore.common.utils.L;
import nativesdk.ad.adcore.modules.rawdata.FetchRawDataListener;


public class AdRawDataShow extends Activity {

    TextView textView;

    ProgressDialog dialog;

    private static final String TAG = "AdRawDataShow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_raw_data);
        textView = (TextView) findViewById(R.id.raw_data_show);
        AdSdk.getAdRawData(getApplicationContext(), "15887", "", 5, 1, "google", listener);
        /** 直接跳转gp的广告**/
//        AdSdk.getAdRawData(getApplicationContext(), "15887", "", 5, 1, "ddl", listener);
        /** 直接下载的广告 **/
//        AdSdk.getAdRawData(getApplicationContext(), "15887", "", 5, 1, "optin", listener);
        /** 订阅类全屏广告 **/
    }

    FetchRawDataListener listener = new FetchRawDataListener() {
        @Override
        public void onLoadRawDataSuccess(List<FetchAdResult.Ad> arg0) {
            if (arg0 == null) {
                return;
            }
            StringBuilder mStringBuilder = new StringBuilder();
            for (int i = 0; i < arg0.size(); i++) {
                FetchAdResult.Ad data = arg0.get(i);
                mStringBuilder.append("{")
                        .append("appcategory=").append(data.appCategory).append(",")
                        .append("appinstalls=").append(data.appInstalls).append(",")
                        .append("apprating=").append(data.appRating).append(",")
                        .append("appsize=").append(data.appSize).append(",")
                        .append("appreviewnum=").append(data.appReviewNum).append(",")
                        .append("campaignid=").append(data.campaignID).append(",")
                        .append("clkurl=").append(data.clickURL).append(",")
                        .append("description=").append(data.description).append(",")
                        .append("icon=").append(data.icon).append(",")
                        .append("payout=").append(data.payOut).append(",")
                        .append("pkgname=").append(data.packageName).append(",")
                        .append("title=").append(data.title).append(",")
                        .append("creatives=").append(data.creatives).append("")
                        .append("}").append(",");
                FetchAdResult.creatives datadata = data.creatives;
                if (datadata.tablet_fullscreen != null) {
                    L.d(datadata.tablet_fullscreen);
                }
            }
            textView.setText(mStringBuilder);
        }

        @Override
        public void onLoadRawDataStart() {
            L.d(TAG, "onLoadRawDataStart");
        }

        @Override
        public void onLoadRawDataFail(Error mError) {
            L.d(TAG, "onLoadRawDataFail");
        }
    };
}
