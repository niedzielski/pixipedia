package com.niedzielski.pixipedia.android.test;

import android.content.pm.ActivityInfo;
import android.test.ActivityInstrumentationTestCase2;

import com.niedzielski.pixipedia.android.activity.DefaultActivity;
import com.squareup.spoon.Spoon;

// TODO: upgrade to JUnit 4 once supported by Spoon.
public abstract class ActivityTest<T extends DefaultActivity> extends ActivityInstrumentationTestCase2 {
    public ActivityTest(Class<T> clazz) {
        //noinspection unchecked
        super(clazz);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
        waitForIdleSync();
    }

    @Override
    public T getActivity() {
        //noinspection unchecked
        return (T) super.getActivity();
    }

    protected void runOnMainSync(Runnable runnable) {
        getInstrumentation().runOnMainSync(runnable);
    }

    protected void waitForIdleSync() {
        getInstrumentation().waitForIdleSync();
    }

    protected void setLandscapeOrientation() {
        runOnMainSync(new Runnable() {
            @Override
            public void run() {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });
    }

    protected void setPortraitOrientation() {
        runOnMainSync(new Runnable() {
            @Override
            public void run() {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
    }

    protected void screenshot(final String filename) {
        Spoon.screenshot(getActivity(), filename);
    }
}