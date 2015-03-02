package com.niedzielski.pixipedia.android.view;

import com.niedzielski.pixipedia.android.test.ViewTest;

public class DrawerViewTest extends ViewTest<DrawerView> {
    @Override
    protected DrawerView createViewUnderTest() {
        return new DrawerView(getActivity());
    }

    public void testLandscape() {
        setLandscapeOrientation();
        screenshot("landscape");
    }

    public void testPortrait() {
        setPortraitOrientation();
        screenshot("portrait");
    }
}