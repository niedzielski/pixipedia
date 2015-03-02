package com.niedzielski.pixipedia.android.test.util;

import com.squareup.okhttp.mockwebserver.MockWebServer;

import java.io.IOException;

public class NetworkTestUtil {
    public static MockWebServer setUp() throws IOException {
        MockWebServer server = new MockWebServer();
        server.play();
        return server;
    }

    public static void tearDown(MockWebServer server) throws IOException {
        server.shutdown();
    }

    private NetworkTestUtil() {}
}