package com.niedzielski.pixipedia.android.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niedzielski.pixipedia.android.R;
import com.niedzielski.pixipedia.android.model.Page;
import com.niedzielski.pixipedia.android.util.ActivityUtil;
import com.niedzielski.pixipedia.android.view.AutoFitRecyclerView;
import com.niedzielski.pixipedia.android.view.DefaultViewHolder;
import com.niedzielski.pixipedia.android.view.ImageCardView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnTouch;

public class ImageSearchFragment extends DefaultFragment {
    public interface PageImageSearchFragmentCallback extends FragmentCallback {
        void onPageClick(View view, Page page);
    }

    @InjectView(R.id.recycler)
    protected AutoFitRecyclerView mRecycler;

    private PageAdapter mRecyclerAdapter;

    private GridLayoutManager mLayoutManager;

    @NonNull
    private final List<Page> mPages = new ArrayList<>();

    public static ImageSearchFragment newInstance() {
        return new ImageSearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerAdapter = new PageAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mRecycler.setListener(new AutoFitRecyclerView.Listener() {
            @Override
            public void onColumnsAvailable(int columns) {
                mLayoutManager.setSpanCount(columns);
            }
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecycler.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getActivity(), mRecycler.getColumns());

        // use a linear layout manager
        mRecycler.setLayoutManager(mLayoutManager);

        mRecycler.setAdapter(mRecyclerAdapter);

        return view;
    }

    @OnTouch(R.id.recycler)
    public boolean onRecyclerInteraction() {
        ActivityUtil.dismissSoftKeyboard(getActivity());
        return false;
    }

    public void setPageResults(Collection<Page> pages) {
        if (getActivity() != null) {
            mPages.clear();
            for (Page page : pages) {
                if (page.hasThumbnail()) {
                    mPages.add(page);
                }
            }
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected PageImageSearchFragmentCallback getCallback() {
        return (PageImageSearchFragmentCallback) super.getCallback();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_image_search;
    }

    @Override
    protected void initFromFragmentArgs() {}

    private class PageAdapter extends RecyclerView.Adapter<DefaultViewHolder<ImageCardView>> {
        @Override
        public DefaultViewHolder<ImageCardView> onCreateViewHolder(ViewGroup viewGroup, int i) {
            DefaultViewHolder<ImageCardView> viewHolder = new DefaultViewHolder<>(new ImageCardView(viewGroup.getContext()));
            viewGroup.addView(viewHolder.getView());
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final DefaultViewHolder<ImageCardView> viewHolder, int i) {
            final Page item = mPages.get(i);
            viewHolder.getView().load(item.thumbnail().source());
            viewHolder.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getCallback().onPageClick(viewHolder.getView(), item);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mPages.size();
        }
    }
}