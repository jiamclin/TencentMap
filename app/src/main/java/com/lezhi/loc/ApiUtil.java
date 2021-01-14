package com.lezhi.loc;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

public class ApiUtil {
    /**设置背景图片*/
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("deprecation")
    public static void setBackgroundDrawable(View view, Drawable drawable){
        if(android.os.Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            view.setBackground(drawable);//api>=16
        }else{
            view.setBackgroundDrawable(drawable);
        }
    }
}
