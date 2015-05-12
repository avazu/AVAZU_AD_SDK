package com.avazu.demo.ad;

import java.util.List;

import avazu.nativead.android.AdRawData;
import avazu.nativead.android.AvazuAdSdk;
import avazu.nativead.android.app.LoadRawDataCallback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AdRawDataShow extends Activity implements LoadRawDataCallback {

    TextView textView;

    ProgressDialog dialog;

    String mExcludePackages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_raw_data);
        textView = (TextView) findViewById(R.id.raw_data_show);

        dialog = new ProgressDialog(this);
        dialog.setMessage("loading...");
        dialog.setCanceledOnTouchOutside(false);
        // 传入不想显示（或已经显示过）的应用的campaignid, 若有多个，则用","进行分隔，如“27841,27813,27910”
        mExcludePackages = "27841";
        AvazuAdSdk.preloadRawData(getApplicationContext(), "6395", 5, mExcludePackages, false, this);
    }

    @Override
    public void onLoadRawDataFinish(List<AdRawData> arg0) {
        // TODO Auto-generated method stub
        Log.e("AdRawDataShow", "onLoadRawDataFinish");
        dialog.dismiss();
        if (arg0 == null) {
            return;
        }

        Log.d("demo", "size:" + arg0.size());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arg0.size(); i++) {
            AdRawData data = arg0.get(i);
            sb.append("{")
                    .append("appcategory=").append(data.appcategory).append(",")
                    .append("appinstalls=").append(data.appinstalls).append(",")
                    .append("apprating=").append(data.apprating).append(",")
                    .append("appreviewnum=").append(data.appreviewnum).append(",")
                    .append("campaignid=").append(data.campaignid).append(",")
                    .append("clkurl=").append(data.clkurl).append(",")
                    .append("description=").append(data.description).append(",")
                    .append("icon=").append(data.icon).append(",")
                    .append("payout=").append(data.payout).append(",")
                    .append("pkgname=").append(data.pkgname).append(",")
                    .append("title=").append(data.title).append("")
                    .append("}").append(",");
        }
        textView.setText(sb);
    }

    @Override
    public void onLoadRawDataStart() {
        // TODO Auto-generated method stub
        Log.e("AdRawDataShow", "onLoadRawDataStart");
        dialog.show();
    }
}
