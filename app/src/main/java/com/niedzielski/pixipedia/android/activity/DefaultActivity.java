package com.niedzielski.pixipedia.android.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.niedzielski.pixipedia.android.R;
import com.niedzielski.pixipedia.android.drawable.TintResources;
import com.niedzielski.pixipedia.android.util.DeprecationUtil;
import com.niedzielski.pixipedia.android.util.DrawableUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/** Sane defaults and boilerplate for most {@link ActionBarActivity} implementations. */
public abstract class DefaultActivity extends ActionBarActivity {
    private static final int DRAWER_GRAVITY = Gravity.START;

    @InjectView(R.id.toolbar)
    protected Toolbar mToolbar;

    @InjectView(R.id.progress_bar)
    protected SmoothProgressBar mProgressBar;

    @InjectView(R.id.drawer)
    protected DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    private Resources mResources;

    @Override
    public Resources getResources() {
        if (mResources == null) {
            mResources = new TintResources(super.getResources());
        }
        return mResources;
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
        DeprecationUtil.finishActivityAnimation(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inflate();

        initDrawer();
        initToolbar();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    /** @return The resource layout to inflate. */
    protected abstract int getLayout();

    /** Inflates the resources provided by {@link #getLayout()} and configures Butter Knife. */
    protected void inflate() {
        setContentView(getLayout());
        ButterKnife.inject(this);
    }

    /** @return True if the navigation drawer is open. */
    protected boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(DRAWER_GRAVITY);
    }

    private void initDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name,
                R.string.app_name);
        mDrawerToggle.setHomeAsUpIndicator(buildHomeDrawable());
        mDrawerToggle.setDrawerIndicatorEnabled(isHomeActivity());
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setLogo(R.drawable.toolbar_logo);
    }

    private Drawable buildHomeDrawable() {
        return DrawableUtil.setAlphaColorFilter(this, R.drawable.abc_ic_ab_back_mtrl_am_alpha,
                R.color.vector_primary_dark);
    }

    private boolean isHomeActivity() {
        return getClass().equals(HomeActivity.class);
    }
}