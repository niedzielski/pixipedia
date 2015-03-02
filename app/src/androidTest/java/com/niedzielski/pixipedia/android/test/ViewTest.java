package com.niedzielski.pixipedia.android.test;

import android.view.View;

public abstract class ViewTest<T extends View> extends TestActivityTest {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setView(createViewUnderTest());
    }

    protected T getView() {
        //noinspection unchecked
        return (T) getActivity().getView();
    }

    /** Creates view under test on UI thread. */
    protected abstract T createViewUnderTest();
}