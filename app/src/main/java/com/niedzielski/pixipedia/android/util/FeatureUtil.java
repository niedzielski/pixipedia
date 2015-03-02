package com.niedzielski.pixipedia.android.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class FeatureUtil {
    public static boolean hasActivityTransitionAnimations() {
        return ApiUtil.hasLollipop();
    }

    public static boolean hasActivityScaleUpAnimations() {
        return ApiUtil.hasJellyBean();
    }

    public static boolean hasEmail(Context context) {
        return ActivityUtil.isActivityResolvable(context,
                new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:")));
    }

    public static boolean hasBrowser(Context context) {
        return ActivityUtil.isActivityResolvable(context,
                new Intent(Intent.ACTION_VIEW, Uri.parse("http://")));
    }

    private FeatureUtil() {}
}