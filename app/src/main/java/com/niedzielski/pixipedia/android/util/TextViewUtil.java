package com.niedzielski.pixipedia.android.util;

import android.text.util.Linkify;
import android.widget.TextView;

public class TextViewUtil {
    public static void setAutoLinkMask(TextView textView) {
        textView.setAutoLinkMask(getAutoLinkMask(textView));
    }

    public static int getAutoLinkMask(TextView textView) {
        return getAutoLinkMaskEmail(textView) | getAutoLinkMaskWeb(textView);
    }

    public static int getAutoLinkMaskEmail(TextView textView) {
        return FeatureUtil.hasEmail(textView.getContext()) ? Linkify.EMAIL_ADDRESSES : 0;
    }

    public static int getAutoLinkMaskWeb(TextView textView) {
        return FeatureUtil.hasBrowser(textView.getContext()) ? Linkify.WEB_URLS : 0;
    }

    private TextViewUtil() {}
}