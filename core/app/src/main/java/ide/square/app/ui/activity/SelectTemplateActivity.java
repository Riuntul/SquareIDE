package ide.square.app.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import android.transition.Slide;
import android.view.Gravity;
import android.view.Window;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ide.square.app.databinding.ActivitySelectTemplateBinding;
import ide.square.app.template.NoActivityTemplate;
import ide.square.app.template.TemplateManager;

import ide.square.app.template.TemplatesAdapter;
import ide.square.app.template.TestTemplate;
import org.riuntul.material.activity.CollapsingToolbarActivity;

public class SelectTemplateActivity extends CollapsingToolbarActivity {
    public ActivitySelectTemplateBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivitySelectTemplateBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());
            
        TemplateManager templateManager = new TemplateManager(); 
        templateManager.register(new NoActivityTemplate(getApplicationContext()));
        templateManager.register(new TestTemplate(getApplicationContext()));
        
        RecyclerView templateContainer = binding.templateContainer;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        templateContainer.setLayoutManager(linearLayoutManager);
        templateContainer.setAdapter(new TemplatesAdapter(templateManager.getTemplates()));
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        binding = null;
    }
}