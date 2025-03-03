package org.riuntul.material.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.resources.TextAppearanceConfig;

import org.riuntul.material.databinding.ActivityCollapsingToolbarBinding;

public class CollapsingToolbarActivity extends AppCompatActivity {
    private static final float TOOLBAR_LINE_SPACING_MULTIPLIER = 1.1f;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    
    private ActivityCollapsingToolbarBinding mBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivityCollapsingToolbarBinding.inflate(getLayoutInflater());
        
        TextAppearanceConfig.setShouldLoadFontSynchronously(true);
        
        super.setContentView(mBinding.getRoot());
        
        mCollapsingToolbarLayout = mBinding.collapsingToolbar;
        mAppBarLayout = mBinding.appBar;
        if (mCollapsingToolbarLayout != null) {
            mCollapsingToolbarLayout.setLineSpacingMultiplier(TOOLBAR_LINE_SPACING_MULTIPLIER);
        }
        
        disableCollapsingToolbarLayoutScrollingBehavior();

        final MaterialToolbar toolbar = mBinding.actionBar;
        setSupportActionBar(toolbar);
    }

    @Override
    public void setContentView(int layoutResID) {
        final ViewGroup parent = mBinding.layoutContainer;
        
        if (parent != null) {
            parent.removeAllViews();
        }
        
        LayoutInflater.from(this).inflate(layoutResID, parent);
    }

    @Override
    public void setContentView(View view) {
        final ViewGroup parent = mBinding.layoutContainer;
        
        if (parent != null) {
            parent.addView(view);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        final ViewGroup parent = mBinding.layoutContainer;
        
        if (parent != null) {
            parent.addView(view, params);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (mCollapsingToolbarLayout != null) {
            mCollapsingToolbarLayout.setTitle(title);
        } else {
            super.setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if (mCollapsingToolbarLayout != null) {
            mCollapsingToolbarLayout.setTitle(getText(titleId));
        } else {
            super.setTitle(titleId);
        }
    }

    @Override
    public boolean onNavigateUp() {
        if (!super.onNavigateUp()) {
            finishAfterTransition();
        }
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home) -> {
                finish();
                return true;
            } default -> {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    public CollapsingToolbarLayout getCollapsingToolbarLayout() {
        return mCollapsingToolbarLayout;
    }

    public AppBarLayout getAppBarLayout() {
        return mAppBarLayout;
    }

    private void disableCollapsingToolbarLayoutScrollingBehavior() {
        if (mAppBarLayout == null) {
            return;
        }
        
        final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        
        final AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
                
            @Override
            public boolean canDrag(AppBarLayout appBarLayout) {
                return false;
            }
        });
        
        params.setBehavior(behavior);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}