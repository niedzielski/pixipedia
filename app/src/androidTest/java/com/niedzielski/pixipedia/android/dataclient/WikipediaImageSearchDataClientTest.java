package com.niedzielski.pixipedia.android.dataclient;

import static com.niedzielski.pixipedia.android.test.util.MockResponseUtil.mockResourceResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import com.niedzielski.pixipedia.android.model.QueryResponse;
import com.niedzielski.pixipedia.android.test.DataClientTest;
import com.niedzielski.pixipedia.android.test.TestCountDownLatch;

import org.junit.Test;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WikipediaImageSearchDataClientTest extends DataClientTest {
    private WikipediaImageSearchDataClient mSubject;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getUrl("/").toString())
                .build();

        mSubject = restAdapter.create(WikipediaImageSearchDataClient.class);
    }

    @Test
    public void testPage() throws Exception {
        enqueue(mockResourceResponse(getClass(), "/res/raw/response.json"));

        final TestCountDownLatch latch = new TestCountDownLatch(1);
        mSubject.queryPages(1, "gpsSearch", 2, "srSearch", 3, new Callback<QueryResponse>() {
            @Override
            public void success(QueryResponse queryResponse, Response response) {
                assertThat(queryResponse.query().pages().size(), is(51));
                latch.countDown();
            }

            @Override
            public void failure(RetrofitError error) {
                fail("Unexpected request failure, " + error.toString());
            }
        });
        latch.await();
    }
}