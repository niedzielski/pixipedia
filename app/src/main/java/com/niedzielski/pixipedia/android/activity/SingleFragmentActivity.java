package com.niedzielski.pixipedia.android.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;

import com.niedzielski.pixipedia.android.R;

/** Boilerplate for a {@link FragmentActivity} containing a single {@link Fragment}. */
public abstract class SingleFragmentActivity<T extends Fragment> extends DefaultActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isFragmentCreated()) {
            addFragment(createFragment());
        }
    }

    /** @return The {@link Fragment} to create. */
    protected abstract T createFragment();

    /** @return Fragment added. */
    protected T getFragment() {
        //noinspection unchecked
        return (T) getSupportFragmentManager().findFragmentById(getContainerId());
    }

    /** @return The resource identifier for the {@link Fragment} container. */
    protected int getContainerId() {
        return R.id.fragment_container;
    }

    /**
     * @return The resource layout to inflate which must contain a {@link ViewGroup} whose ID is
     *         {@link #getContainerId()}. */
    protected int getLayout() {
        return R.layout.activity_single_fragment;
    }

    private void addFragment(T fragment) {
        getSupportFragmentManager().beginTransaction().add(getContainerId(), fragment).commit();
    }

    private boolean isFragmentCreated() {
        return getFragment() != null;
    }
}