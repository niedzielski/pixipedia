package com.niedzielski.pixipedia.android.model;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.Map;

public class Query {
    @SerializedName("pages")
    protected Map<Integer,Page> mPages = Collections.emptyMap();

    public Map<Integer,Page> pages() {
        return Collections.unmodifiableMap(mPages);
    }
}