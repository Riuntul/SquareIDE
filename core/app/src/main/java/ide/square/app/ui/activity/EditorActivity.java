package ide.square.app.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import ide.square.app.databinding.ActivityEditorBinding;
import ide.square.app.ui.widget.sheet.EditorBottomSheet;

import java.io.File;

public class EditorActivity extends AppCompatActivity {
    public ActivityEditorBinding mBinding;
    
    private boolean isRecreating = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivityEditorBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
        
        final MaterialToolbar toolbar = mBinding.actionBar;
        setSupportActionBar(toolbar);
        
        final ActionBar actionbar = getSupportActionBar();
        
        String projectPath = getIntent().getStringExtra("projectPath");
        File projectDir = new File(projectPath);
        
        //openProject(projectPath);
        
        actionbar.setSubtitle(projectDir.getName());
        
        EditorBottomSheet bottomSheet = mBinding.bottomSheet;
        bottomSheet.setStatus("Test", Gravity.CENTER);
    }
    
    /*protected void openProject(String path) {
        try {
            GradleToolingServer tooling = new GradleToolingServer(path);
            tooling.configureProject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
    
    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(this).setTitle("Close Project").setMessage("Are you sure you want to close this project? All started Gradle daemons will be stopped after this project is closed.").setNegativeButton("No", new DialogInterface.OnClickListener() {
                
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }        
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }
}