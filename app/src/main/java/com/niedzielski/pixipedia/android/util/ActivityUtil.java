package com.niedzielski.pixipedia.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.niedzielski.pixipedia.android.R;

public class ActivityUtil {
    public static void startActivity(Activity activity, Intent intent, View sharedElement,
            String sharedElementName) {
        startActivity(activity, intent, sharedElement, sharedElementName,
                (int) DeprecationUtil.getViewX(sharedElement),
                (int) DeprecationUtil.getViewY(sharedElement), sharedElement.getWidth(),
                sharedElement.getHeight());
    }

    public static void startActivity(Activity activity, Intent intent, View sharedElement,
            String sharedElementName, int startXPx, int startYPx, int startWidthPx,
            int startHeightPx) {
        if (FeatureUtil.hasActivityTransitionAnimations()) {
            sharedElement.setTransitionName(sharedElementName);
            ActivityCompat.startActivity(activity, intent,
                    buildActivityIntentOptionsForTransition(activity, sharedElement,
                            sharedElementName).toBundle());
        } else if (FeatureUtil.hasActivityScaleUpAnimations()) {
            ActivityCompat.startActivity(activity, intent, buildActivityIntentOptionsForScaleUp(sharedElement,
                    startXPx, startYPx, startWidthPx, startHeightPx).toBundle());
        } else {
            activity.startActivity(intent);
            animateNewActivityIn(activity);
        }
    }

    /** Slides this activity out left and the new activity in from the right. Often used when
     * increasing the activity stack. */
    public static void animateNewActivityIn(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_from_right, android.R.anim.fade_out);
    }

    /** Slides this activity out right and the new activity in from the left. Often used when
     * reducing the activity stack. */
    public static void animateOldActivityOut(Activity activity) {
        activity.overridePendingTransition(android.R.anim.fade_in, R.anim.slide_out_to_right);
    }

    public static void dismissSoftKeyboard(Activity activity) {
        View focus = activity.getCurrentFocus();
        if (focus != null) {
            getInputMethodManager(activity).hideSoftInputFromWindow(focus.getWindowToken(), 0);
        }
        activity.getWindow().getDecorView().findViewById(android.R.id.content).clearFocus();
    }

    public static boolean isActivityResolvable(Context context, Intent intent) {
        return intent.resolveActivity(context.getPackageManager()) != null;
    }

    public static InputMethodManager getInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    }

    private static ActivityOptionsCompat buildActivityIntentOptionsForTransition(Activity activity,
            View sharedElement, String sharedElementName) {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedElement, sharedElementName);
    }

    private static ActivityOptionsCompat buildActivityIntentOptionsForScaleUp(View source,
            int startXPx, int startYPx, int startWidthPx, int startHeightPx) {
        return ActivityOptionsCompat.makeScaleUpAnimation(source, startXPx, startYPx, startWidthPx,
                startHeightPx);
    }

    private ActivityUtil() {}
}