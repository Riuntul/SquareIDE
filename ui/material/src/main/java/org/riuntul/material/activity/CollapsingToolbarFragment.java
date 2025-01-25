package org.riuntul.material.activity;

import android.os.Build;
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
import org.riuntul.material.R;

public abstract class CollapsingToolbarFragment extends Fragment {
    private static final float TOOLBAR_LINE_SPACING_MULTIPLIER = 1.1f;

    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private FrameLayout mContentFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_collapsing_toolbar, container, false);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            mCoordinatorLayout = view.findViewById(R.id.container);
        }
        mCollapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        mAppBarLayout = view.findViewById(R.id.app_bar);
        
        if (mCollapsingToolbarLayout != null) {
            mCollapsingToolbarLayout.setLineSpacingMultiplier(TOOLBAR_LINE_SPACING_MULTIPLIER);
        }
        disableCollapsingToolbarLayoutScrollingBehavior();
        
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
}