package ide.square.app.settings.homepage;

import android.os.Bundle;

import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ide.square.app.IDESettings;
import ide.square.app.R;
import ide.square.app.databinding.ActivitySettingsHomepageBinding;

import org.riuntul.material.activity.CollapsingToolbarActivity;

public class SettingsHomepageActivity extends CollapsingToolbarActivity {
    public ActivitySettingsHomepageBinding mBinding;
    
    private interface FragmentCreator<T extends Fragment> {
        T create();

        default void init(Fragment fragment) {}
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivitySettingsHomepageBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
        
        final ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_close);
        }
        
        Toast.makeText(this, IDESettings.getDarkMode(), Toast.LENGTH_SHORT).show();
        
        showFragment(() -> new SettingsHomepageFragment(), R.id.main_content);
    }
    
    private <T extends Fragment> T showFragment(FragmentCreator<T> fragmentCreator, int id) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        
        T showFragment = (T) fragmentManager.findFragmentById(id);

        if (showFragment == null) {
            showFragment = fragmentCreator.create();
            fragmentCreator.init(showFragment);
            fragmentTransaction.add(id, showFragment);
        } else {
            fragmentCreator.init(showFragment);
            fragmentTransaction.show(showFragment);
        }
        
        fragmentTransaction.commit();
        
        return showFragment;
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}