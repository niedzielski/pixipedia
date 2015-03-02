package com.niedzielski.pixipedia.android.view;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import android.support.v7.widget.LinearLayoutManager;

import com.niedzielski.pixipedia.android.test.TestCountDownLatch;
import com.niedzielski.pixipedia.android.test.ViewTest;

public class AutoFitRecyclerViewTest extends ViewTest<AutoFitRecyclerView> {
    @Override
    protected AutoFitRecyclerView createViewUnderTest() {
        AutoFitRecyclerView view = new AutoFitRecyclerView(getActivity());
        view.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public void testDefaultColumns_landscape() {
        setLandscapeOrientation();
        defaultColumnsTest();
    }

    public void testDefaultColumns_portrait() {
        setPortraitOrientation();
        defaultColumnsTest();
    }

    public void testDefaultNoColumnChange_landscape() {
        setLandscapeOrientation();
        defaultNoColumnChangeTest();
    }

    public void testDefaultNoColumnChange_portrait() {
        setPortraitOrientation();
        defaultNoColumnChangeTest();
    }

    public void testWidthNoColumnChange_landscape() throws Exception {
        setLandscapeOrientation();
        widthNoColumnChangeTest();
    }

    public void testWidthNoColumnChange_portrait() throws Exception {
        setPortraitOrientation();
        widthNoColumnChangeTest();
    }

    public void testColumnChange_landscape() throws Exception {
        setLandscapeOrientation();
        columnChangeTest();
    }

    public void testColumnChange_portrait() throws Exception {
        setPortraitOrientation();
        columnChangeTest();
    }

    public void testZeroWidthColumn_landscape() throws Exception {
        setLandscapeOrientation();
        zeroWidthColumnTest();
    }

    public void testZeroWidthColumn_portrait() throws Exception {
        setPortraitOrientation();
        zeroWidthColumnTest();
    }

    private void defaultColumnsTest() {
        assertThat(getView().getColumns(), is(1));
    }

    private void defaultNoColumnChangeTest() {
        setFailListener();
        waitForIdleSync();
    }

    private void widthNoColumnChangeTest() {
        waitForIdleSync();

        int width = getView().getWidth();
        assertThat(width, greaterThan(0));

        setFailListener();

        setColumnWidthPx(width);

        waitForIdleSync();
        assertThat(getView().getColumns(), is(1));
    }

    private void columnChangeTest() throws Exception {
        waitForIdleSync();

        int width = getView().getWidth();
        assertThat(width, greaterThan(0));

        TestCountDownLatch latch = setListener(width);

        setColumnWidthPx(1);

        latch.await();
    }

    private void zeroWidthColumnTest() {
        setFailListener();

        setColumnWidthPx(0);

        waitForIdleSync();

        assertThat(getView().getColumns(), is(1));
    }

    private void setFailListener() {
        getView().setListener(new AutoFitRecyclerView.Listener() {
            @Override
            public void onColumnsAvailable(int columns) {
                fail("Unexpected listener invocation.");
            }
        });
    }

    private TestCountDownLatch setListener(final int expectedColumns) {
        final TestCountDownLatch latch = new TestCountDownLatch(1);
        getView().setListener(new AutoFitRecyclerView.Listener() {
            @Override
            public void onColumnsAvailable(int columns) {
                assertThat(columns, is(expectedColumns));
                latch.countDown();
            }
        });
        return latch;
    }

    private void setColumnWidthPx(final int widthPx) {
        runOnMainSync(new Runnable() {
            @Override
            public void run() {
                getView().setColumnWidthPx(widthPx);
            }
        });
    }
}