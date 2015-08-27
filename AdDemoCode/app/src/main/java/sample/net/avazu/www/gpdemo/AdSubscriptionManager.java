package sample.net.avazu.www.gpdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import nativesdk.ad.adcore.common.utils.L;

/**
 * Created by csc on 15/8/25.
 */
public class AdSubscriptionManager {
    private WindowManager mWM;
    protected AdSubscriptionView mSubscriptionAdView;
    protected Context mContext;

    ProgressDialog ProgresDialog;

    private String adUrl;

    private AdSubscriptionListener mSubscriptionListener;

    private static final String TAG = "AdSubscriptionManager";

    public static final float MARGIN_BOTTOM_DP = 53;

    public AdSubscriptionManager(Context context) {
        mContext = context;

    }

    public void init(String url) {
        mWM = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        adUrl = url;
        initViews();
    }

    public void loadAd() {
        mSubscriptionAdView.loadSubscriptionAd(adUrl);
    }

    public void close() {
        L.d(TAG + " close");
        try {
            mWM.removeView(mSubscriptionAdView);
            mSubscriptionAdView.getWebView().setWebViewClient(null);
            mSubscriptionAdView.removeAllViews();
            mSubscriptionAdView = null;
        } catch (Exception e) {
            L.d(TAG + " close error: " + e.getMessage());
        }
        notifyDismiss();
    }

    private void initViews() {
        L.d(TAG, "initViews");
        mSubscriptionAdView = new AdSubscriptionView(mContext);
        mSubscriptionAdView.getCloseButton().
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        close();
                    }
                });

        ProgresDialog = new ProgressDialog(mContext);
        ProgresDialog.setMessage("loading...");
        ProgresDialog.setCanceledOnTouchOutside(false);
        ProgresDialog.show();

        mSubscriptionAdView.getWebView().setWebViewClient(new WebViewClient() {
            int resourceNumber = 0;

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                L.d(TAG + " onPageFinished:" + url);
                L.d(TAG + " onPageFinished resourceNumber " + resourceNumber);
                final Resources resource = view.getContext().getResources();
                final float dp = resource.getDisplayMetrics().widthPixels
                        / resource.getDisplayMetrics().density;
                Utils.scaleWebView(view, 1.0f * dp / 360);
                attach();
                notifyLoadSuccess();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String
                    failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                L.d("onReceivedError:" + failingUrl);
                notifyLoadFailed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, final String url) {
                L.d("shouldOverrideUrlLoading URL:" + url);
                return false;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                resourceNumber++;
            }
        });
    }

    private boolean attach() {
        L.d(TAG, "attach");
        //setup window
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.x = 0;
        mSubscriptionAdView.measure(0, 0);
        params.y = Utils.getScreenHeight(mContext) - mSubscriptionAdView.getMeasuredHeight() - Utils.getPx(mContext, 25)
                - Utils.getPx(mContext, MARGIN_BOTTOM_DP);
        L.i("******** attach y:" + params.y);
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.format = PixelFormat.RGBA_8888;
        L.d(TAG, "ADDVIEW");
        mWM.addView(mSubscriptionAdView, params);
        return true;
    }

    void notifyLoadSuccess() {
        L.d(TAG + " notifyLoadSuccess");
        if (mSubscriptionListener != null) {
            mSubscriptionListener.onAdLoadSuccess();
        }
        ProgresDialog.dismiss();
    }

    void notifyLoadFailed() {
        L.d(TAG + " notifyLoadFailed");
        if (mSubscriptionListener != null) {
            mSubscriptionListener.onAdLoadFailed();
        }
        mSubscriptionListener = null;
        ProgresDialog.dismiss();
    }

    void notifyDismiss() {
        L.d(TAG + " notifyDismiss ");
        if (mSubscriptionListener != null) {
            mSubscriptionListener.onAdDismiss();
        }
        mSubscriptionListener = null;
        ProgresDialog.dismiss();
    }
}
