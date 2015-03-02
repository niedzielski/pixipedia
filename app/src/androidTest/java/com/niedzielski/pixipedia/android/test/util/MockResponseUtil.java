package com.niedzielski.pixipedia.android.test.util;

import android.content.Context;

import com.squareup.okhttp.mockwebserver.MockResponse;

import java.io.IOException;

public class MockResponseUtil {
    public static MockResponse mockResourceResponse(Context context, int resourceId) throws IOException {
        return new MockResponse().setBody(ResourceTestUtil.openRawResourceAsBytes(context, resourceId));
    }

    public static MockResponse mockResourceResponse(Class<?> clazz, String filename) throws IOException {
        return new MockResponse().setBody(ResourceTestUtil.openFileAsBytes(clazz, filename));
    }

    private MockResponseUtil() {}
}