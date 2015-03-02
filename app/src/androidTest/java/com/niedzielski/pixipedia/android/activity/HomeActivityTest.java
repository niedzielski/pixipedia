package com.niedzielski.pixipedia.android.activity;

import com.niedzielski.pixipedia.android.test.ActivityTest;

public class HomeActivityTest extends ActivityTest<HomeActivity> {
    public HomeActivityTest() {
        super(HomeActivity.class);
    }

    public void testLandscape() {
        screenshot("landscape");
    }

    public void testPortrait() {
        screenshot("portrait");
    }
}