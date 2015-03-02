package com.niedzielski.pixipedia.android.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public class ViewUtil {
    public static int getDimensionPx(View view, int dimensionResourceId) {
        return view.getResources().getDimensionPixelSize(dimensionResourceId);
    }

    public static void setPaddingPx(View view, int paddingPx) {
        view.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);
    }

    public static int getColorArgb(View view, int colorResourceId) {
        return view.getResources().getColor(colorResourceId);
    }

    public static void inflate(ViewGroup viewGroup, int layoutResourceId) {
        LayoutInflater.from(viewGroup.getContext()).inflate(layoutResourceId, viewGroup, true);
        ButterKnife.inject(viewGroup);
    }

    private ViewUtil() {}
}