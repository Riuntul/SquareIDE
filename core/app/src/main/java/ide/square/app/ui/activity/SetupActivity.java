package ide.square.app.ui.activity;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
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
import org.riuntul.material.activity.ToolbarActivity;
import ide.square.app.R;

public class SetupActivity extends AppCompatActivity {
    public ActivitySetupBinding mBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivitySetupBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
        
        Intent intent = new Intent(SetupActivity.this, EditorActivity.class);
        intent.putExtra("projectPath", "/sdcard/AndroidIDEProjects/SquareIDE");
        
        startActivity(intent);
        
        //showFragment(new WelcomeFragment(), R.id.main_content);
        
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