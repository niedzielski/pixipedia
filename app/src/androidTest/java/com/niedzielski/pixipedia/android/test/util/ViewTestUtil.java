package com.niedzielski.pixipedia.android.test.util;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.espresso.action.ViewActions;

public class ViewTestUtil {
    public static void click(int viewId) {
        onView(withId(viewId)).perform(ViewActions.click());
    }
}