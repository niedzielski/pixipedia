package com.niedzielski.pixipedia.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.niedzielski.pixipedia.android.R;

/** A {@link RecyclerView} that exposes a column availability listener. */
public class AutoFitRecyclerView extends RecyclerView {
    public interface Listener {
        void onColumnsAvailable(int columns);
    }

    private static final int MIN_COLUMNS = 1;
    private static final Listener DEFAULT_LISTENER = new DefaultListener();

    private int mColumns = MIN_COLUMNS;
    private int mColumnWidthPx;

    @NonNull
    private Listener mCallback = DEFAULT_LISTENER;

    public AutoFitRecyclerView(Context context) {
        this(context, null);
    }

    public AutoFitRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoFitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initFromAttributes(attrs);
    }

    public void setColumnWidthPx(int columnWidthPx) {
        if (columnWidthPx != mColumnWidthPx) {
            mColumnWidthPx = columnWidthPx;
            requestLayout();
        }
    }

    /** @return The number of columns to display, greater than zero. */
    public int getColumns() {
        return mColumns;
    }

    public void setListener(Listener callback) {
        mCallback = callback == null ? DEFAULT_LISTENER : callback;
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        int columns = calculateColumns(mColumnWidthPx, getMeasuredWidth());
        if (mColumns != columns) {
            mColumns = columns;
            mCallback.onColumnsAvailable(mColumns);
        }
    }

    private int calculateColumns(int columnWidthPx, int availableWidthPx) {
        return columnWidthPx > 0 ? Math.max(MIN_COLUMNS, availableWidthPx / columnWidthPx) : MIN_COLUMNS;
    }

    private void initFromAttributes(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.AutoFitRecyclerView);
            mColumnWidthPx = array.getDimensionPixelSize(R.styleable.AutoFitRecyclerView_columnWidth, 0);
            array.recycle();
        }
    }

    public static class DefaultListener implements Listener {
        @Override
        public void onColumnsAvailable(int columns) {}
    }
}