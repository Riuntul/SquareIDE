package org.riuntul.material.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.riuntul.material.databinding.FragmentCollapsingToolbarBinding;

public abstract class CollapsingToolbarFragment extends Fragment {
    private static final float TOOLBAR_LINE_SPACING_MULTIPLIER = 1.1f;

    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private FrameLayout mContentFrameLayout;
    
    private FragmentCollapsingToolbarBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentCollapsingToolbarBinding.inflate(inflater, container, false);
        
        mCoordinatorLayout = mBinding.container;
        mCollapsingToolbarLayout = mBinding.collapsingToolbar;
        mAppBarLayout = mBinding.appBar;
        
        if (mCollapsingToolbarLayout != null) {
            mCollapsingToolbarLayout.setLineSpacingMultiplier(TOOLBAR_LINE_SPACING_MULTIPLIER);
        }
        disableCollapsingToolbarLayoutScrollingBehavior();
        
        mToolbar = mBinding.actionBar;
        mContentFrameLayout = mBinding.layoutContainer;
        
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requireActivity().setActionBar(mToolbar);
    }

    public CoordinatorLayout getCoordinatorLayout() {
        return mCoordinatorLayout;
    }

    public AppBarLayout getAppBarLayout() {
        return mAppBarLayout;
    }

    public CollapsingToolbarLayout getCollapsingToolbarLayout() {
        return mCollapsingToolbarLayout;
    }

    public FrameLayout getContentFrameLayout() {
        return mContentFrameLayout;
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
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}