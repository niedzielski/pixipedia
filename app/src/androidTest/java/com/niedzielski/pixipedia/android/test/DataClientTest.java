package com.niedzielski.pixipedia.android.test;

import com.niedzielski.pixipedia.android.test.util.NetworkTestUtil;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;

import java.net.URL;

public abstract class DataClientTest {
    private MockWebServer mServer;

    @Before
    public void setUp() throws Exception {
        mServer = NetworkTestUtil.setUp();
    }

    @After
    public void tearDown() throws Exception {
        NetworkTestUtil.tearDown(mServer);
    }

    protected MockWebServer getServer() {
        return mServer;
    }

    protected void enqueue(MockResponse response) {
        getServer().enqueue(response);
    }

    protected URL getUrl(String path) {
        return getServer().getUrl(path);
    }
}