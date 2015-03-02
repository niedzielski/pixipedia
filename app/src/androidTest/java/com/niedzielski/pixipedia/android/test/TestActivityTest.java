package com.niedzielski.pixipedia.android.test;

import android.view.View;

import com.niedzielski.pixipedia.android.test.activity.TestActivity;

public abstract class TestActivityTest extends ActivityTest<TestActivity> {
    protected TestActivityTest() {
        super(TestActivity.class);
    }

    protected View getView() {
        return getActivity().getView();
    }

    protected void setView(final View view) {
        runOnMainSync(new Runnable() {
            @Override
            public void run() {
                getActivity().setView(view);
            }
        });
    }
}