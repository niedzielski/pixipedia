package com.niedzielski.pixipedia.android.activity;

import android.content.Context;
import android.content.Intent;

/** An activity for showing a full screen image to the user. */
public class ImageActivity extends SingleFragmentActivity<ImageFragment> {
    public static Intent newIntent(Context context, String imageUrl) {
        return new Intent(context, ImageActivity.class)
                .putExtra(ImageFragment.FRAGMENT_ARG_IMAGE_URL_KEY, imageUrl);
    }

    @Override
    protected ImageFragment createFragment() {
        return ImageFragment.newInstance(getIntent().getStringExtra(ImageFragment.FRAGMENT_ARG_IMAGE_URL_KEY));
    }
}