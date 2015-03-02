package com.niedzielski.pixipedia.android.model;

import com.google.gson.annotations.SerializedName;

public class QueryResponse {
    @SerializedName("query")
    protected Query mQuery;

    public Query query() {
        return mQuery;
    }

    public boolean hasQuery() {
        return mQuery != null;
    }
}