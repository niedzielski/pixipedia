package com.niedzielski.pixipedia.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/** Sane defaults and boilerplate for most {@link Fragment}s. */
public abstract class DefaultFragment<T extends FragmentCallback> extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initFromFragmentArgs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    /** A lifecycle pattern for unpacking {@link Fragment#getArguments()}. */
    protected abstract void initFromFragmentArgs();

    /** @return The layout to inflate. */
    protected abstract int getLayout();

    /** @return The host, which must implement {@link FragmentCallback}. */
    protected T getCallback() {
        if (getTargetFragment() instanceof FragmentCallback) {
            //noinspection unchecked
            return (T) getTargetFragment();
        } else if (getActivity() instanceof FragmentCallback) {
            //noinspection unchecked
            return (T) getActivity();
        } else {
            return null;
        }
    }
}