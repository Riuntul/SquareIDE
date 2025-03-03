package ide.square.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import ide.square.app.IDEApplication;
import ide.square.app.databinding.ActivitySetupBinding;
import java.io.File;
import org.riuntul.material.activity.ToolbarActivity;

public class SetupActivity extends ToolbarActivity {
    public ActivitySetupBinding mBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivitySetupBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
        
        Intent intent = new Intent(SetupActivity.this, EditorActivity.class);
        intent.putExtra("projectPath", "/sdcard/AndroidIDEProjects/SquareIDE");
        
        startActivity(intent);
        
        if (!new File(IDEApplication.sdkPath + "/jdk").exists()) {
            
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}