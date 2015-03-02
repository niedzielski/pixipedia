package com.niedzielski.pixipedia.android.test;

import android.support.annotation.NonNull;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/** A partial {@link CountDownLatch} implementation with assumptions for test. */
public class TestCountDownLatch {
    private static final int TIMEOUT = 10;
    private static final TimeUnit UNITS = TimeUnit.SECONDS;

    @NonNull
    private final CountDownLatch mLatch;

    public TestCountDownLatch(int count) {
        mLatch = new CountDownLatch(count);
    }

    public void countDown() {
        mLatch.countDown();
    }

    public long getCount() {
        return mLatch.getCount();
    }

    /** Like {@link CountDownLatch#await(long, TimeUnit)} but an exception is thrown if the
     * countdown failed to reach zero within internal timeout constraints.
     *
     * @throws InterruptedException If the countdown times out waiting to reach zero. */
    public void await() throws InterruptedException {
        if (!mLatch.await(TIMEOUT, UNITS)) {
            throw new InterruptedException("Timeout exceeded.");
        }
    }
}