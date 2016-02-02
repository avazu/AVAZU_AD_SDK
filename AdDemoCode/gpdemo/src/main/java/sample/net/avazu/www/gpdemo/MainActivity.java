package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import nativesdk.ad.adsdk.AdSdk;
import nativesdk.ad.adsdk.app.DirectToMarketListener;
import nativesdk.ad.adsdk.common.utils.L;


public class MainActivity extends Activity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ad_main_layout);

        findViewById(R.id.go_banner_single).setOnClickListener(this);
        findViewById(R.id.go_banner_wall).setOnClickListener(this);
        findViewById(R.id.go_banner_custom).setOnClickListener(this);
        findViewById(R.id.go_rect_single).setOnClickListener(this);
        findViewById(R.id.go_rect_wall).setOnClickListener(this);
        findViewById(R.id.go_rect_custom).setOnClickListener(this);
        findViewById(R.id.raw_data).setOnClickListener(this);
        findViewById(R.id.direct_to_market).setOnClickListener(this);
        findViewById(R.id.subscription_ad).setOnClickListener(this);
        findViewById(R.id.activity_ad).setOnClickListener(this);
        findViewById(R.id.news_ad).setOnClickListener(this);

        AdSdk.initialize(this);
        AdSdk.createMarketShortcut(this, BitmapFactory.decodeResource(getResources(), R.drawable.market_icon), "Market");

    }


    private void goCustomAd(int style) {
        Intent custom = new Intent(this, AdCustomizedActivity.class);
        custom.putExtra(Utils.Constants.BUNDLE_KEY_ADSTYLE, style);
        startActivity(custom);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int id = v.getId();
        switch (id) {
            case R.id.go_banner_single:
                showTwoPickDialog();
                break;

            case R.id.go_banner_wall:
                goBannerWallNew();
                break;

            case R.id.go_rect_single:
                goRectSingleNew();
                break;

            case R.id.go_rect_wall:
                goRectWallNew();
                break;

            case R.id.go_rect_custom:
                goCustomAd(Utils.Constants.STYLE_RECT);
                break;

            case R.id.go_banner_custom:
                goCustomAd(Utils.Constants.STYLE_BANNER);
                break;
            case R.id.raw_data:
                Intent fire = new Intent(this, AdRawDataShow.class);
                startActivity(fire);
                break;
            case R.id.direct_to_market:
                AdSdk.directToMarket(this, "15887", new DirectToMarketListener() {
                    @Override
                    public void DirectToMarketSuccess() {
                        L.d("DirectToMarketSuccess");
                    }

                    @Override
                    public void DirectMarketToFail() {
                        L.d("DirectMarketToFail");
                    }
                });
                break;
            case R.id.subscription_ad:
                Intent SubscriptionIntent = new Intent(this, AdSubscriptionActivity.class);
                startActivity(SubscriptionIntent);
                break;
            case R.id.activity_ad:
//                AdSdk.enableFacebookAdInMarket(MainActivity.this, "xxxxxx");
//                AdSdk.enableAdmobInMarket(MainActivity.this, "xxxxxx");
                AdSdk.showAppMarket(this, "18001");
                break;
            case R.id.news_ad:
//              AdSdk.enableFacebookInterstitialInNewsFeed(MainActivity.this, "xxxxxx");
//              AdSdk.enableFacebookBannerInNewsFeed(MainActivity.this, "xxxxxx");
//              AdSdk.enableFacebookNativeAdInNewsFeed(MainActivity.this, "xxxxx");
                AdSdk.enableApxNativeAdInNewsFeed(MainActivity.this, "18000");
                AdSdk.showNewsFeed(MainActivity.this, false);
                break;
        }
    }

    private void goBannerWallNew() {
        Intent fire = new Intent(this, AdBannerWallDemo.class);
        startActivity(fire);
    }

    private void goBannerSingleNew1() {
        Intent fire = new Intent(this, AdBannerSingleDemo.class);
        startActivity(fire);
    }

    private void goBannerSingleNew0() {
        Intent fire = new Intent(this, AdBannerSingleDemo2.class);
        startActivity(fire);
    }

    private void goRectWallNew() {
        Intent fire = new Intent(this, AdRectWallDemo.class);
        startActivity(fire);
    }

    private void goRectSingleNew() {
        Intent fire = new Intent(this, AdRectSingleDemo.class);
        startActivity(fire);
    }

    private void showTwoPickDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(new CharSequence[]{"Integrated right inside of your app", "Integrated above your app"}, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (which == 0) {
                    goBannerSingleNew0();
                } else if (which == 1) {
                    goBannerSingleNew1();
                }
            }
        });
        builder.show();
    }
}
