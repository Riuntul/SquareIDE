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

import org.riuntul.material.databinding.FragmentToolbarBinding;

public abstract class ToolbarFragment extends Fragment {
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private FrameLayout mContentFrameLayout;
    
    private FragmentToolbarBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentToolbarBinding.inflate(inflater, container, false);
        
        mCoordinatorLayout = mBinding.container;
        mAppBarLayout = mBinding.appBar;
        
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

    public FrameLayout getContentFrameLayout() {
        return mContentFrameLayout;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}