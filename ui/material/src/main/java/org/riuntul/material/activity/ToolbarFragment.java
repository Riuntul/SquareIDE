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

import org.riuntul.material.R;

public abstract class ToolbarFragment extends Fragment {
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private FrameLayout mContentFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_toolbar, container, false);
        
        mCoordinatorLayout = view.findViewById(R.id.container);
        mAppBarLayout = view.findViewById(R.id.app_bar);
        
        mToolbar = view.findViewById(R.id.action_bar);
        mContentFrameLayout = view.findViewById(R.id.layout_container);
        
        return view;
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
}