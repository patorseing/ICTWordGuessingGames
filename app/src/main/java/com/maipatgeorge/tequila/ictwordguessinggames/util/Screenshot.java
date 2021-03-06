package com.maipatgeorge.tequila.ictwordguessinggames.util;

import android.graphics.Bitmap;
import android.view.View;

//*******************************************************************
// Screenshot the class for take screenshot. the screenshot is taken
// from view.
//*******************************************************************

public class Screenshot {

    public static Bitmap takescreenshot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);

        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());

        v.setDrawingCacheEnabled(false);
        return b;
    }

    public static Bitmap takescreenshotOfRootview(View v){
        return takescreenshot (v.getRootView());
    }

}
