package ide.square.app.settings;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import ide.square.app.R;
import ide.square.app.databinding.ActivitySettingsBinding;

import org.riuntul.material.activity.CollapsingToolbarActivity;

public class SettingsActivity extends CollapsingToolbarActivity {
    private ActivitySettingsBinding mBinding;
    
    private String mFragmentClass;
    private String mFragmentTitle;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        
        final ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
        
        init(getIntent());
        
        createUiFromIntent();
    }
    
    private void init(Intent intent) {
        if (TextUtils.isEmpty(intent.getStringExtra("fragmentClass"))) {
            throw new NullPointerException("Unable to get fragmentClass");
        }
        
        mFragmentClass = intent.getStringExtra("fragmentClass");
        mFragmentTitle = intent.getStringExtra("fragmentTitle");
    }
    
    private void createUiFromIntent() {
        if (mFragmentTitle != null) {
            setTitle(mFragmentTitle);
        }
        
        setTheme(ide.square.shared.R.style.Theme_SubSettings);
        
        setContentView(mBinding.getRoot());
        
        launchSettingFragment(mFragmentClass);
    }
    
    void launchSettingFragment(String fragmentName) {
        switchToFragment(fragmentName);
    }
    
    private void switchToFragment(String fragmentClass) {
        Fragment fragment = Fragment.instantiate(this, fragmentClass);
        
        if (fragment == null) {
            return;
        }
        
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        transaction.replace(R.id.main_content, fragment);
        transaction.commit();
    }
    
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(org.riuntul.material.R.anim.sud_slide_back_in, org.riuntul.material.R.anim.sud_slide_back_out);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}