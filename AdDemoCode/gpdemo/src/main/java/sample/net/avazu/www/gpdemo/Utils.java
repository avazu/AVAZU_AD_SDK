package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Utils {

	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.substring(0, 32).toString();
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	public static int px2dp(Context context,float pxValue){
	    final float scale = context.getResources().getDisplayMetrics().density; 
	    return (int)(pxValue/scale+0.5f); 
	}

	public static int getStatusBarHeight(Activity acty){
		Rect frame = new Rect();
		acty.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		Log.i("sdk","statusBarHeight window: "+statusBarHeight);
		
		if (0 == statusBarHeight){  
	        Class<?> localClass;  
	        try {  
	            localClass = Class.forName("com.android.internal.R$dimen");  
	            Object localObject = localClass.newInstance();  
	            int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());  
	            statusBarHeight = acty.getResources().getDimensionPixelSize(i5);  
	            Log.i("sdk","statusBarHeight reflect: "+statusBarHeight);
	        } catch (ClassNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IllegalAccessException e) {  
	            e.printStackTrace();  
	        } catch (InstantiationException e) {  
	            e.printStackTrace();  
	        } catch (NumberFormatException e) {  
	            e.printStackTrace();  
	        } catch (IllegalArgumentException e) {  
	            e.printStackTrace();  
	        } catch (SecurityException e) {  
	            e.printStackTrace();  
	        } catch (NoSuchFieldException e) {  
	            e.printStackTrace();  
	        }  
	    }
		
		return statusBarHeight;
	}

	public static int getPx(Context context, float dp) {
		Resources res = context.getResources();
		return (int) (res.getDisplayMetrics().density * dp + 0.5f);
	}
	
	public class Constants{
		public static final String BUNDLE_KEY_ADVIEWCONTROLLER = "adview_controller";
		public static final String BUNDLE_KEY_ADVIEWSHOWTYPE = "show_type";
		public static final String BUNDLE_KEY_ADSTYLE = "ad_style";
		
		public static final int STYLE_RECT = 1;
		public static final int STYLE_BANNER = 2;
	}

	public static ActivityInfo getSystemBrowser(Context context){
		try{
			PackageManager pm = context.getPackageManager();
			Intent query = new Intent(Intent.ACTION_VIEW);
			query.setData(Uri.parse("http://www.google.com"));
			List<ResolveInfo> results = pm.queryIntentActivities(query,0);
			for(int i=0;i<results.size();i++){
				ActivityInfo activityInfo = results.get(i).activityInfo;
				String packageName = activityInfo.packageName;
				ApplicationInfo ai = pm.getApplicationInfo(packageName,0);
				if ((ai.flags & ApplicationInfo.FLAG_SYSTEM) > 0 || (ai.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) > 0){
					return activityInfo;
				}
			}
		}catch (Exception e){

		}
		return null;
	}

	public static int getScreenWidth(Context context){
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	public static int getScreenHeight(Context context){
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	public static void scaleWebView(WebView webView, float scale) {
		webView.loadUrl("javascript:document.body.style.zoom = " + String.valueOf(scale) + ";");
	}
	
}
