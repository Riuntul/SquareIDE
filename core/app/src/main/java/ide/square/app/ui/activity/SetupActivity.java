package ide.square.app.ui.activity;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ide.square.app.IDEApplication;
import ide.square.app.databinding.ActivitySetupBinding;
import ide.square.app.ui.fragment.PermissionsRequestFragment;
import ide.square.app.ui.fragment.WelcomeFragment;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.riuntul.material.activity.ToolbarActivity;
import ide.square.app.R;

public class SetupActivity extends AppCompatActivity {
    private ActivitySetupBinding mBinding;
    
    private File mConfigDir = new File(IDEApplication.configPath);
    
    private List<Fragment> setupFragmentStep = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (mConfigDir.exists() && mConfigDir.isDirectory()) {
            Intent intent = new Intent(SetupActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (!mConfigDir.isDirectory()) {
            mConfigDir.delete();
        }
        
        super.onCreate(savedInstanceState);
        
        mBinding = ActivitySetupBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
        
        /*Intent intent = new Intent(SetupActivity.this, EditorActivity.class);
        intent.putExtra("projectPath", "/sdcard/AndroidIDEProjects/SquareIDE");
        
        startActivity(intent);*/
        
        init();
    }
    
    private void init() {
        setupFragmentStep.add(new WelcomeFragment());
        
        int stepNumber = 1;
        if (stepNumber == 1) {
            mBinding.back.setVisibility(View.GONE);
        }
        
        showFragment(new WelcomeFragment(), R.id.main_content);
        
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