package com.niedzielski.pixipedia.android.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.niedzielski.pixipedia.android.R;
import com.niedzielski.pixipedia.android.activity.ImageSearchFragment.PageImageSearchFragmentCallback;
import com.niedzielski.pixipedia.android.dataclient.WikipediaImageSearchDataClient;
import com.niedzielski.pixipedia.android.model.Page;
import com.niedzielski.pixipedia.android.model.QueryResponse;
import com.niedzielski.pixipedia.android.util.ActivityUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class ImageSearchActivity extends SingleFragmentActivity<ImageSearchFragment>
        implements PageImageSearchFragmentCallback {
    private static final String TAG = DefaultActivity.class.getSimpleName();
    private static final String QUERY_INSTANCE_KEY = "query";

    private SearchView mSearchView;

    private WikipediaImageSearchDataClient mDataClient;

    // The number of outstanding data client requests. When nonzero, show the progress bar.
    private int mPendingRequests;

    @NonNull
    private String mQuery = "";

    @Nullable
    private Toast mToast;

    @NonNull
    private final Callback<QueryResponse> mQueryCallback = new Callback<QueryResponse>() {
        @Override
        public void success(QueryResponse queryResponse, Response response) {
            recordQueryResponse();
            onPageResults(queryResponse.hasQuery()
                    ? queryResponse.query().pages().values()
                    : Collections.<Page>emptyList());
        }

        @Override
        public void failure(RetrofitError error) {
            Log.e(TAG, error.toString());

            recordQueryResponse();

            replaceToast(R.string.search_generic_failure);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        initSearchView((SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search)));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected ImageSearchFragment createFragment() {
        return ImageSearchFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);

        mQuery = getString(R.string.page_image_search_init_query);

        initDataClient();
    }

    @Override
    public void onPageClick(View view, Page page) {
        if (page.hasThumbnail()) {
            launchImageActivity(view, page.thumbnail().source());
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onRestoreQuery(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onSaveQuery(outState);
    }

    private void initDataClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.wikipedia_service_uri))
                .build();

        mDataClient = restAdapter.create(WikipediaImageSearchDataClient.class);
    }

    private void initSearchView(SearchView searchView) {
        mSearchView = searchView;
        mSearchView.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);

        mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean queryTextFocused) {
                if(!queryTextFocused) {
                    mToolbar.collapseActionView();
                    mToolbar.clearFocus();
                }
            }
        });

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                mQuery = newText;
                if (TextUtils.isGraphic(mQuery)) {
                    queryPages(newText);
                }
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchView.clearFocus();
                return true;
            }
        };
        mSearchView.setOnQueryTextListener(queryTextListener);
        mSearchView.setQuery(mQuery, false);
    }

    private void launchImageActivity(View view, String imageUrl) {
        ActivityUtil.startActivity(this, ImageActivity.newIntent(this, imageUrl), view,
                getString(R.string.transition_image));
    }

    protected void onPageResults(Collection<Page> pages) {
        if (getFragment() != null) {
            getFragment().setPageResults(pages);
        }
    }

    private void queryPages(String query) {
        recordQueryRequest();
        int resultLimit = getResources().getInteger(R.integer.query_result_limit);
        mDataClient.queryPages(getResources().getDimensionPixelSize(R.dimen.thumbnail_size), query,
                resultLimit, query, resultLimit, mQueryCallback);
    }

    private void onRestoreQuery(Bundle savedInstanceState) {
        mQuery = StringUtils.defaultString(savedInstanceState.getString(QUERY_INSTANCE_KEY));
    }

    private void onSaveQuery(Bundle outState) {
        outState.putString(QUERY_INSTANCE_KEY, mQuery);
    }

    private void updateProgressBar() {
        mProgressBar.setVisibility(mPendingRequests > 0 ? View.VISIBLE : View.INVISIBLE);
    }

    private void recordQueryResponse() {
        --mPendingRequests;
        updateProgressBar();
    }

    private void recordQueryRequest() {
        ++mPendingRequests;
        updateProgressBar();
    }

    private void replaceToast(int stringResourceId) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(ImageSearchActivity.this, stringResourceId, Toast.LENGTH_SHORT);
        mToast.show();
    }
}