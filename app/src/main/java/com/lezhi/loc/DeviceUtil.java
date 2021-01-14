package com.lezhi.loc;

import android.content.Context;

public class DeviceUtil {
    public static int dp2px(Context context,float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
