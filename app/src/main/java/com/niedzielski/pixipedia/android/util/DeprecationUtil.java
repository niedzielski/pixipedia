package com.niedzielski.pixipedia.android.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;

public class DeprecationUtil {
    public static void finishActivityAnimation(Activity activity) {
        if (!FeatureUtil.hasActivityScaleUpAnimations()) {
            ActivityUtil.animateOldActivityOut(activity);
        }
    }

    public static float getViewX(View view) {
        if (ApiUtil.hasHoneyComb()) {
            return getViewXHoneycomb(view);
        } else {
            return getViewXDeprecated(view);
        }
    }

    public static float getViewY(View view) {
        if (ApiUtil.hasHoneyComb()) {
            return getViewYHoneycomb(view);
        } else {
            return getViewYDeprecated(view);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static float getViewXHoneycomb(View view) {
        return view.getX();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static float getViewYHoneycomb(View view) {
        return view.getY();
    }

    private static int getViewXDeprecated(View view) {
        return getViewPosition(view)[0];
    }

    private static int getViewYDeprecated(View view) {
        return getViewPosition(view)[1];
    }

    /** @return [x,y] */
    private static int[] getViewPosition(View view) {
        int[] position = new int[2];
        view.getLocationOnScreen(position);
        return position;
    }

    private DeprecationUtil() {}
}