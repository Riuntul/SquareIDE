package org.riuntul.material.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.resources.TextAppearanceConfig;

import org.riuntul.material.R;

public class ToolbarBaseActivity extends FragmentActivity {
    private AppBarLayout mAppBarLayout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextAppearanceConfig.setShouldLoadFontSynchronously(true);
        
        super.setContentView(R.layout.layout_toolbar_base);
        
        mAppBarLayout = findViewById(R.id.app_bar);

        final Toolbar toolbar = findViewById(R.id.action_bar);
        setActionBar(toolbar);

        final ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        final ViewGroup parent = findViewById(R.id.layout_container);
        
        if (parent != null) {
            parent.removeAllViews();
        }
        
        LayoutInflater.from(this).inflate(layoutResID, parent);
    }

    @Override
    public void setContentView(View view) {
        final ViewGroup parent = findViewById(R.id.layout_container);
        
        if (parent != null) {
            parent.addView(view);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        final ViewGroup parent = findViewById(R.id.layout_container);
        
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
}