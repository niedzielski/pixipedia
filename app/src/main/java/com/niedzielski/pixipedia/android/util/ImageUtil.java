package com.niedzielski.pixipedia.android.util;

import static com.niedzielski.pixipedia.android.util.DrawableUtil.createProgressDrawable;

import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageUtil {
    public static void load(String url, ImageView view) {
        load(url, view, null);
    }

    public static void load(String url, ImageView view, Callback callback) {
        Picasso.with(view.getContext())
                .load(url)
                .placeholder(createProgressDrawable(view))
                .into(view, callback);

    }

    private ImageUtil() {}
}