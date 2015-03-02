package com.niedzielski.pixipedia.android.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/** The minimum boilerplate required by the view holder pattern for use with custom views. */
public class DefaultViewHolder<T extends View> extends RecyclerView.ViewHolder {
    @NonNull
    private final T mView;

    public DefaultViewHolder(@NonNull T view) {
        super(view);
        mView = view;
    }

    @NonNull
    public T getView() {
        return mView;
    }
}