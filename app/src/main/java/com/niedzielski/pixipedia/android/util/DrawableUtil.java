package com.niedzielski.pixipedia.android.util;

import static com.niedzielski.pixipedia.android.util.ViewUtil.getColorArgb;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.niedzielski.pixipedia.android.R;
import com.niedzielski.pixipedia.android.drawable.MaterialProgressDrawable;

public class DrawableUtil {
    public static Drawable setAlphaColorFilter(Context context, int drawableResourceId, int colorResourceId) {
        return setAlphaColorFilter(context, context.getResources().getDrawable(drawableResourceId), colorResourceId);
    }

    public static Drawable setAlphaColorFilter(Context context, Drawable drawable, int colorResourceId) {
        return setAlphaColorFilter(drawable, context.getResources().getColor(colorResourceId));
    }

    public static Drawable setAlphaColorFilter(Drawable drawable, int color) {
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return drawable;
    }


    public static Drawable createProgressDrawable(View view) {
        MaterialProgressDrawable drawable = new MaterialProgressDrawable(view.getContext(), view);

        drawable.setAlpha(255);
        drawable.setColorSchemeColors(getColorArgb(view, R.color.vector_accent));
        drawable.start();

        return drawable;
    }

    private DrawableUtil() {}
}