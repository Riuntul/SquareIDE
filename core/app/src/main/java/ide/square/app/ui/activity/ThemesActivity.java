package ide.square.app.ui.activity;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ide.square.app.R;
import ide.square.app.databinding.ActivityThemesBinding;
import ide.square.app.ui.fragment.ThemesFragment;
import org.riuntul.material.activity.CollapsingToolbarActivity;
import org.riuntul.material.activity.ToolbarActivity;

public class ThemesActivity extends CollapsingToolbarActivity {
    public ActivityThemesBinding mBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivityThemesBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
        
        final ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
        
        showFragment(new ThemesFragment(), R.id.main_content);
        
        FrameLayout content = mBinding.mainContent;
        content.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
    }
    
    private void showFragment(Fragment fragment, int id) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final Fragment showFragment = fragmentManager.findFragmentById(id);

        if (showFragment == null) {
            fragmentTransaction.add(id, fragment);
        } else {
            fragmentTransaction.show(showFragment);
        }
        fragmentTransaction.commit();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}