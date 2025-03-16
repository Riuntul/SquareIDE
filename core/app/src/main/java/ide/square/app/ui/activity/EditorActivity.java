package ide.square.app.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import ide.square.app.IDEApplication;
import ide.square.app.databinding.ActivityEditorBinding;
import ide.square.app.editor.ProjectManager;
import ide.square.app.ui.widget.sheet.EditorBottomSheet;
import ide.square.tooling.GradleToolingServer;
import java.io.File;

public class EditorActivity extends AppCompatActivity {
    public ActivityEditorBinding mBinding;
    
    private boolean isRecreating = false;
    
    private ProjectManager project;
    
    private EditorBottomSheet mBottomSheet;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivityEditorBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
        
        final MaterialToolbar toolbar = mBinding.actionBar;
        setSupportActionBar(toolbar);
        
        final ActionBar actionbar = getSupportActionBar();
        
        String projectPath = getIntent().getStringExtra("projectPath");
        
        mBinding.codeView.openFile(new File(projectPath + "/core/app/src/main/java/ide/square/app/IDEApplication.java"));
        
        mBottomSheet = mBinding.bottomSheet;
        GradleToolingServer tooling = new GradleToolingServer("/sdcard/AndroidIDEProjects/LSPosed");
        tooling.init(IDEApplication.gradleUserHome);
        mBottomSheet.setOutputStream(tooling.getLogOutputStream());
    }
    
    /*protected void openProject(String path) {
        try {
            GradleToolingServer tooling = new GradleToolingServer(path);
            tooling.configureProject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
    public void onChangeBuildState(String task) {
        
    }
    
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