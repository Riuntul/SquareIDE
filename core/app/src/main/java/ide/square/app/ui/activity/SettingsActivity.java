package ide.square.app.ui.activity;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.FrameLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.resources.TextAppearanceConfig;
import ide.square.app.databinding.ActivitySettingsBinding;
import ide.square.app.R;
import ide.square.app.ui.fragment.SettingsFragment;
import org.riuntul.material.activity.CollapsingToolbarActivity;
import org.riuntul.material.activity.CollapsingToolbarBaseActivity;
import org.riuntul.material.activity.ToolbarActivity;

public class SettingsActivity extends CollapsingToolbarActivity {
    public ActivitySettingsBinding mBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
        
        showFragment(new SettingsFragment(), R.id.main_content);
        
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