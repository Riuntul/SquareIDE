package ide.square.app.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.documentfile.provider.DocumentFile;
import com.google.android.material.textview.MaterialTextView;

import ide.square.app.DefaultConfig;
import ide.square.app.databinding.ActivityCreateProjectBinding;

public class CreateProjectActivity extends AppCompatActivity {
    private static String projectName = DefaultConfig.projectName;
    private static String packageName = DefaultConfig.packageName;
    private static String projectPath;
    
    public ActivityCreateProjectBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityCreateProjectBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());
        
        MaterialTextView title = binding.titleText;
        title.setTextSize(25);
        
        EditText projectNameEdit = binding.projectNameEdit;
        projectNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                projectName = text.toString();
            }    
                
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                
            }
                
            @Override
            public void afterTextChanged(Editable edit) {
                
            }
        });
        projectNameEdit.setText(projectName);
        
        EditText packageNameEdit = binding.packageNameEdit;
        packageNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                packageName = text.toString();
            }    
                
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                
            }
                
            @Override
            public void afterTextChanged(Editable edit) {
                
            }
        });
        packageNameEdit.setText(packageName);
        
        EditText projectPathEdit = binding.projectPathEdit;
        projectPathEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                projectPath = text.toString();
            }    
                
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                
            }
                
            @Override
            public void afterTextChanged(Editable edit) {
                
            }
        });
        projectPathEdit.setText(DefaultConfig.projectPath + "/" + projectName);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        binding = null;
    }
}