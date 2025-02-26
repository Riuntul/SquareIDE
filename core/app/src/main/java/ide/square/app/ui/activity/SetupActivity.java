package ide.square.app.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import ide.square.app.databinding.ActivitySetupBinding;
import org.riuntul.material.activity.ToolbarActivity;

public class SetupActivity extends ToolbarActivity {
    public ActivitySetupBinding mBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivitySetupBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
        
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}