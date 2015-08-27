package sample.net.avazu.www.gpdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import java.io.IOException;

import nativesdk.ad.adcore.common.utils.L;

/**
 * Created by csc on 15/8/25.
 */
public class AdSubscriptionView extends ViewGroup{
    private final String TAG = this.getClass().getSimpleName();
    private static final float SUBSCRIPTION_RATE = 1.0f * 320 / 480;
    private static final int IMAGE_PADDING_DP = 2;
    private static final int IMAGE_SIZE_DP = 20;
    private static final String IMAGE_PATH = "ad_assets/btn_close.png";


    private ImageView mBtnClose;
    private WebView mWebView;
    private float mRate = SUBSCRIPTION_RATE;

    public AdSubscriptionView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mWebView = new WebView(getContext());
        setupWebView();
        mBtnClose = new ImageView(getContext());
        mBtnClose.setScaleType(ImageView.ScaleType.FIT_XY);
        final int padding = Utils.getPx(getContext(), IMAGE_PADDING_DP);
        mBtnClose.setPadding(padding, padding, padding, padding);
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                try {
                    return BitmapFactory.decodeStream(getContext().getAssets().open(IMAGE_PATH));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                mBtnClose.setImageBitmap(bitmap);
            }
        }.execute();
        mWebView.setBackgroundColor(0);
        addView(mBtnClose);
        addView(mWebView);
    }

    private void setupWebView() {
        mWebView.setBackgroundColor(Color.TRANSPARENT);
        mWebView.setHorizontalScrollBarEnabled(true);
        mWebView.setVerticalScrollBarEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSavePassword(false);
        mWebView.getSettings().setSaveFormData(false);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            mBtnClose.layout(getMeasuredWidth() - mBtnClose.getMeasuredWidth(), 0, getMeasuredWidth(),
                    mBtnClose.getMeasuredHeight());
            final int top = mBtnClose.getMeasuredHeight();
            mWebView.layout(0, top, mWebView.getMeasuredWidth(), top + mWebView.getMeasuredHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = getResources().getDisplayMetrics().widthPixels;
        L.d("width:" + width);
        final int height = (int) (width / mRate) + Utils.getPx(getContext(), IMAGE_SIZE_DP);
        setMeasuredDimension(width, height);

        mWebView.measure(width | MeasureSpec.EXACTLY, height | MeasureSpec.EXACTLY);
        final int sizeMeasureSpec = Utils.getPx(getContext(), IMAGE_SIZE_DP) | MeasureSpec.EXACTLY;
        mBtnClose.measure(sizeMeasureSpec, sizeMeasureSpec);
    }

    public void loadSubscriptionAd(String url){
        mRate = SUBSCRIPTION_RATE;
        requestLayout();
        mWebView.loadUrl(url);
    }

    public ImageView getCloseButton() {
        return mBtnClose;
    }

    public WebView getWebView() {
        return mWebView;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        L.d(TAG + " onAttachedToWindow  " + getWidth() + " " + getHeight());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        L.d(TAG + " onDetachedFromWindow ");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        L.d(TAG + " onWindowFocusChanged "+getWidth()+" "+getHeight());
    }


}
