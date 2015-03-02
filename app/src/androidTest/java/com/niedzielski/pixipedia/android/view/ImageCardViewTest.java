package com.niedzielski.pixipedia.android.view;

import static com.niedzielski.pixipedia.android.test.util.MockResponseUtil.mockResourceResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import com.niedzielski.pixipedia.android.R;
import com.niedzielski.pixipedia.android.drawable.MaterialProgressDrawable;
import com.niedzielski.pixipedia.android.test.TestCountDownLatch;
import com.niedzielski.pixipedia.android.test.ViewTest;
import com.niedzielski.pixipedia.android.test.util.NetworkTestUtil;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.picasso.Callback;

import java.net.URL;

public class ImageCardViewTest extends ViewTest<ImageCardView> {
    private MockWebServer mServer;

    @Override
    protected ImageCardView createViewUnderTest() {
        return new ImageCardView(getActivity());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mServer = NetworkTestUtil.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        NetworkTestUtil.tearDown(mServer);
        super.tearDown();
    }

    public void testLoad_landscape() throws Exception {
        setLandscapeOrientation();

        loadTest();

        screenshot("landscape");
    }

    public void testLoad_portrait() throws Exception {
        setPortraitOrientation();

        loadTest();

        screenshot("portrait");
    }

    private void loadTest() throws Exception {
        mServer.enqueue(mockResourceResponse(getActivity(), R.mipmap.ic_launcher));

        final TestCountDownLatch latch = new TestCountDownLatch(1);

        load(mServer.getUrl("/"), new Callback() {
            @Override
            public void onSuccess() {
                latch.countDown();
            }

            @Override
            public void onError() {
                fail("Unexpected error.");
            }
        });
        screenshot("loading");

        disableAnimations();
        latch.await();

        waitForIdleSync();
    }

    private void disableAnimations() {
        runOnMainSync(new Runnable() {
            @Override
            public void run() {
                assertThat(getView().mImage.getDrawable(), instanceOf(MaterialProgressDrawable.class));
                MaterialProgressDrawable animation = (MaterialProgressDrawable) getView().mImage.getDrawable();
                animation.stop();
            }
        });
    }

    private void load(URL url, Callback callback) {
        load(url.toString(), callback);
    }

    private void load(final String url, final Callback callback) {
        runOnMainSync(new Runnable() {
            @Override
            public void run() {
                getView().load(url, callback);
            }
        });
    }
}