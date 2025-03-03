package org.riuntul.material.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.resources.TextAppearanceConfig;

import org.riuntul.material.databinding.ActivityToolbarBinding;

public class ToolbarActivity extends AppCompatActivity {
    private AppBarLayout mAppBarLayout;
    
    private ActivityToolbarBinding mBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivityToolbarBinding.inflate(getLayoutInflater());
        
        TextAppearanceConfig.setShouldLoadFontSynchronously(true);
        
        super.setContentView(mBinding.getRoot());
        
        mAppBarLayout = mBinding.appBar;

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
        super.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
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

    public AppBarLayout getAppBarLayout() {
        return mAppBarLayout;
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}