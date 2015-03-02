package com.niedzielski.pixipedia.android.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.niedzielski.pixipedia.android.R;
import com.niedzielski.pixipedia.android.util.ImageUtil;
import com.niedzielski.pixipedia.android.util.ViewUtil;
import com.squareup.picasso.Callback;

import butterknife.InjectView;

/** A {@link CardView} holding an image. */
public class ImageCardView extends FrameLayout {
    @InjectView(R.id.image)
    protected ImageView mImage;

    public ImageCardView(Context context) {
        this(context, null);
    }

    public ImageCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // TODO: consider extending from CardView and setting attributes.
        ViewUtil.inflate(this, R.layout.view_image_card_view);
    }

    public void load(String url) {
        load(url, null);
    }

    protected void load(String url, Callback callback) {
        ImageUtil.load(url, mImage, callback);
    }
}