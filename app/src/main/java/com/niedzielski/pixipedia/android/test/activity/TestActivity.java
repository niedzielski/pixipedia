package com.niedzielski.pixipedia.android.test.activity;

import android.view.View;

import com.niedzielski.pixipedia.android.R;
import com.niedzielski.pixipedia.android.activity.DefaultActivity;

/** An activity for facilitating automated testing. This activity assumes it handles configuration
 * changes. */
public class TestActivity extends DefaultActivity {
    private View mView;

    public void setView(View view) {
        mView = view;
        setContentView(mView);
    }

    public View getView() {
        return mView;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_single_fragment;
    }
}