package com.niedzielski.pixipedia.android.model;

import com.google.gson.annotations.SerializedName;

public class Thumbnail {
    @SerializedName("source")
    protected String mSource;

    @SerializedName("width")
    protected int mWidth;

    @SerializedName("height")
    protected int mHeight;

    public String source() {
        return mSource;
    }

    public int width() {
        return mWidth;
    }

    public int height() {
        return mHeight;
    }
}