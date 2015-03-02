package com.niedzielski.pixipedia.android.drawable;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import com.niedzielski.pixipedia.android.R;

/** Provides android:tint accent support on older platforms. */
public class TintResources extends Resources {
    public TintResources(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
    }

    @Override
    public Drawable getDrawable(int id) throws NotFoundException {
        Drawable drawable = super.getDrawable(id);
        if (isAccentThemedResource(id)) {
            tintDrawable(drawable);
        }
        return drawable;
    }

    private void tintDrawable(Drawable drawable) {
        drawable.setColorFilter(getColor(R.color.vector_accent), PorterDuff.Mode.SRC_IN);
    }

    private boolean isAccentThemedResource(int resourceId) {
        switch (resourceId) {
            case R.drawable.text_select_handle_left_material:
            case R.drawable.text_select_handle_middle_material:
            case R.drawable.text_select_handle_right_material:
                return true;
        }
        return false;
    }
}