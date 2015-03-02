package com.niedzielski.pixipedia.android.view;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

import android.view.View;

import org.junit.Test;

public class DefaultViewHolderTest {
    @Test
    public void testGetView() {
        View subjectView = mock(View.class);
        DefaultViewHolder subject = new DefaultViewHolder(subjectView);
        assertThat(subject.getView(), equalTo(subjectView));
    }
}