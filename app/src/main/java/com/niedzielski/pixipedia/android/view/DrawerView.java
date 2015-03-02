package com.niedzielski.pixipedia.android.view;

import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.niedzielski.pixipedia.android.R;
import com.niedzielski.pixipedia.android.util.TextViewUtil;
import com.niedzielski.pixipedia.android.util.ViewUtil;
import com.squareup.phrase.Phrase;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DrawerView extends FrameLayout {
    private static final String PHRASE_AUTHOR_DELIMITER = "author";
    private static final String PHRASE_EMAIL_DELIMITER = "email";
    private static final String PHRASE_WWW_DELIMITER = "www";

    @InjectView(R.id.text)
    protected TextView mTextView;

    public DrawerView(Context context) {
        this(context, null);
    }

    public DrawerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setBackgroundColor(getResources().getColor(R.color.vector_primary_light));
        ViewUtil.setPaddingPx(this, ViewUtil.getDimensionPx(this, R.dimen.drawer_padding));
        LayoutInflater.from(context).inflate(R.layout.view_drawer, this, true);
        ButterKnife.inject(this);
        setClickable(true);
        TextViewUtil.setAutoLinkMask(mTextView);
        mTextView.setText(createText());
    }

    private Spanned createSpanned(CharSequence text, int sizePx) {
        SpannableString spanned = new SpannableString(text);
        spanned.setSpan(new AbsoluteSizeSpan(sizePx, false), 0, text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spanned;
    }

    private CharSequence createText() {
        return phraseText(
        createSpanned(getString(R.string.author_name), ViewUtil.getDimensionPx(this, R.dimen.drawer_header_text_size)),
        createSpanned(getStringHtml(R.string.author_email_html), ViewUtil.getDimensionPx(this, R.dimen.drawer_sub_text_size)),
        createSpanned(getStringHtml(R.string.author_link_html), ViewUtil.getDimensionPx(this, R.dimen.drawer_sub_text_size)));
    }

    private CharSequence phraseText(CharSequence author, CharSequence email, CharSequence www) {
        return Phrase.from(this, R.string.drawer_text_phrase)
                .put(PHRASE_AUTHOR_DELIMITER, author)
                .put(PHRASE_EMAIL_DELIMITER, email)
                .put(PHRASE_WWW_DELIMITER, www)
                .format();
    }

    private Spanned getStringHtml(int stringHtmlResourceId) {
        return Html.fromHtml(getString(stringHtmlResourceId));
    }

    private String getString(int stringResourceId) {
        return getContext().getString(stringResourceId);
    }
}