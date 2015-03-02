package com.niedzielski.pixipedia.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.niedzielski.pixipedia.android.R;
import com.niedzielski.pixipedia.android.util.ImageUtil;

import butterknife.InjectView;

public class ImageFragment extends DefaultFragment {
    /*default*/ static final String FRAGMENT_ARG_IMAGE_URL_KEY = "imageUrl";

    @InjectView(R.id.image)
    protected ImageView mImageView;

    private String mImageUrl;

    public static ImageFragment newInstance(String imageUrl) {
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(buildFragmentArgs(imageUrl));
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ImageUtil.load(mImageUrl, mImageView);

        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_page_image;
    }

    @Override
    protected void initFromFragmentArgs() {
        mImageUrl = getArguments().getString(FRAGMENT_ARG_IMAGE_URL_KEY);
    }

    private static Bundle buildFragmentArgs(String imageUrl) {
        Bundle ret = new Bundle();
        ret.putString(FRAGMENT_ARG_IMAGE_URL_KEY, imageUrl);
        return ret;
    }
}