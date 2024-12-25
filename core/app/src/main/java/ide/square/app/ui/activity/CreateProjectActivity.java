package ide.square.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import ide.square.app.databinding.ActivityCreateProjectBinding;
import ide.square.app.ui.listener.DrawableClickListener;
import ide.square.app.util.FilesUtil;
import ide.square.app.util.UriUtil;
import ide.square.template.BaseTemplate;
import ide.square.template.TemplateManager;
import ide.square.template.module.NoActivityTemplate;

import java.io.File;
import java.io.IOException;

public class CreateProjectActivity extends AppCompatActivity {
    private static String projectName;
    private static String packageName;
    private static String projectPath;

    private int REQUEST_CODE_OPEN_DOCUMENT_TREE = 0;

    public ActivityCreateProjectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateProjectBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        MaterialTextView title = binding.titleText;
        title.setTextSize(25);

        EditText projectNameEdit = binding.projectNameEdit;
        projectName = projectNameEdit.getText().toString();
        
        projectNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                projectName = text.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable edit) {}
        });

        EditText packageNameEdit = binding.packageNameEdit;
        packageName = packageNameEdit.getText().toString();
        
        packageNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                packageName = text.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable edit) {}
        });

        EditText projectPathEdit = binding.projectPathEdit;
        projectPath = projectPathEdit.getText().toString();

        DrawableClickListener.setOnDrawableClickListener(projectPathEdit, new DrawableClickListener.OnDrawableClickListener() {
            @Override
            public void onDrawableStartClick(View view) {
                selectDirectory();
            }

            @Override
            public void onDrawableEndClick(View view) {}
        });

        projectPathEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                projectPath = text.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable edit) {}
        });

        MaterialButton cancelButton = binding.cancelButton;
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        
        MaterialButton createButton = binding.createButton;
        createButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProject();
            }
        });
    }

    private void selectDirectory() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        this.startActivityForResult(intent, REQUEST_CODE_OPEN_DOCUMENT_TREE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {
            Uri treeUri = data.getData();
            File file = new File(Environment.getExternalStorageDirectory(), UriUtil.removeTreePrimaryPrefix(treeUri.getPath()));

            EditText projectPathEdit = binding.projectPathEdit;
            projectPathEdit.setText(file.getAbsolutePath());

            Log.i("ProjectBuilder", "Selected Project folder path: " + file.getAbsolutePath());
        }
    }

    public String getDocumentPath(Context context, Uri treeUri) {
        DocumentFile documentFile = DocumentFile.fromTreeUri(context, treeUri);

        if (documentFile != null && documentFile.exists()) {
            return documentFile.getUri().toString();
        }
        return null;
    }
    
    private void createProject() {
        FilesUtil.copyAssets("templates/base", projectPath, getApplicationContext());
        TemplateManager templateManager = new TemplateManager(projectPath);
        NoActivityTemplate android = new NoActivityTemplate(projectName, packageName, getApplicationContext());
        templateManager.loadTemplate(android);
        try {
            templateManager.saveTemplate("No Activity");
            templateManager.applyTemplate("No Activity");
        } catch(IOException e) {
            e.printStackTrace();
        }
        BaseTemplate template = templateManager.getTemplate("No Activity");
        System.out.println(template);
        templateManager.removeTemplate("No Activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
    }
}