package ide.square.app.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import ide.square.app.databinding.FragmentCreateProjectBinding;
import ide.square.app.ui.listener.DrawableClickListener;
import ide.square.app.util.FilesUtil;
import ide.square.app.util.UriUtil;
import ide.square.template.BaseTemplate;
import ide.square.template.TemplateManager;
import ide.square.template.module.NoActivityTemplate;

import java.io.File;
import java.io.IOException;

public class CreateProjectFragment extends Fragment {
    private static String projectName;
    private static String packageName;
    private static String projectPath;

    public FragmentCreateProjectBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateProjectBinding.inflate(getLayoutInflater(), container, false);
        
        return binding.getRoot();
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        init();
    }
    
    private void init() {
        // Init TextView
        MaterialTextView title = binding.title;
        title.setTextSize(25);

        // Init EditText
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

        // Init Button
        MaterialButton cancelButton = binding.cancelButton;
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
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
        this.startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 0:
                Uri treeUri = data.getData();
                File file = new File(Environment.getExternalStorageDirectory(), UriUtil.removeTreePrimaryPrefix(treeUri.getPath()));

                EditText projectPathEdit = binding.projectPathEdit;
                projectPathEdit.setText(file.getAbsolutePath());

                Log.i("ProjectBuilder", "Selected Project folder path: " + file.getAbsolutePath());
                break;
        }
    }
    
    private void createProject() {
        FilesUtil.copyAssets("templates/base", projectPath, getContext().getApplicationContext());
        TemplateManager templateManager = new TemplateManager(projectPath);
        NoActivityTemplate android = new NoActivityTemplate(projectName, packageName, getContext().getApplicationContext());
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
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}