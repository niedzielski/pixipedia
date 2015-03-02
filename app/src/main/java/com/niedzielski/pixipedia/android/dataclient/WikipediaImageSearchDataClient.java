package com.niedzielski.pixipedia.android.dataclient;

import com.niedzielski.pixipedia.android.model.QueryResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

// @see https://wikipedia.org/w/api.php?action=help&modules=query
public interface WikipediaImageSearchDataClient {
    @GET("/?action=query&prop=pageimages&format=json&piprop=thumbnail&generator=prefixsearch&gpssearch=Cat")
    void queryPages(@Query("pithumbsize") int thumbSize,
                    @Query("gpssearch") String gpsSearch,
                    @Query("gpslimit") int gpsLimit,
                    @Query("srsearch") String srSearch,
                    @Query("pilimit") int piLimit,
                    Callback<QueryResponse> result);
}