package com.lezhi.loc;

import android.graphics.drawable.GradientDrawable;

public class ImgUtil {
    public static GradientDrawable getSolidCircle(int solidColor) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(solidColor);
        gd.setShape(GradientDrawable.OVAL);
        return gd;
    }
}
