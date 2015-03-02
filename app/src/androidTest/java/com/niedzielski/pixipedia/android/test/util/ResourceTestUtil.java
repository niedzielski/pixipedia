package com.niedzielski.pixipedia.android.test.util;

import android.content.Context;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class ResourceTestUtil {
    public static InputStream openRawResourceAsStream(Context context, int rawResourceId) {
        return context.getResources().openRawResource(rawResourceId);
    }

    public static byte[] openRawResourceAsBytes(Context context, int rawResourceId) throws IOException {
        return IOUtils.toByteArray(openRawResourceAsStream(context, rawResourceId));
    }

    public static byte[] openFileAsBytes(Class<?> clazz, String filename) throws IOException {
        InputStream stream = clazz.getResourceAsStream(filename);
        if (stream == null) {
            throw new IOException("No such file, \"" + filename + "\".");
        }
        return IOUtils.toByteArray(stream);
    }

    private ResourceTestUtil() {}
}