package com.niedzielski.pixipedia.android.model;

import com.google.gson.annotations.SerializedName;

public class Page {
    @SerializedName("pageid")
    protected int mPageId;

    @SerializedName("ns")
    protected int mNs;

    @SerializedName("title")
    protected String mTitle;

    @SerializedName("index")
    protected int mIndex;

    @SerializedName("thumbnail")
    protected Thumbnail mThumbnail;

    public int pageId() {
        return mPageId;
    }

    public int ns() {
        return mNs;
    }

    public String title() {
        return mTitle;
    }

    public int index() {
        return mIndex;
    }

    public Thumbnail thumbnail() {
        return mThumbnail;
    }

    public boolean hasThumbnail() {
        return mThumbnail != null;
    }
}