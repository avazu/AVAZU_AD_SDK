package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	
	public class Constants{
		public static final String BUNDLE_KEY_ADVIEWCONTROLLER = "adview_controller";
		public static final String BUNDLE_KEY_ADVIEWSHOWTYPE = "show_type";
		public static final String BUNDLE_KEY_ADSTYLE = "ad_style";
		
		public static final int STYLE_RECT = 1;
		public static final int STYLE_BANNER = 2;
	}
	
}
