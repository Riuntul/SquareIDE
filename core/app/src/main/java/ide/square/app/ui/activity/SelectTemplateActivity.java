package ide.square.app.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import android.transition.Slide;
import android.view.Gravity;
import android.view.Window;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ide.square.app.R;
import ide.square.app.databinding.ActivitySelectTemplateBinding;
import ide.square.app.template.NoActivityTemplate;
import ide.square.app.template.Template;
import ide.square.app.template.TemplateManager;

import ide.square.app.template.TemplatesAdapter;
import javax.xml.transform.Templates;
import org.riuntul.material.activity.CollapsingToolbarActivity;

public class SelectTemplateActivity extends CollapsingToolbarActivity {
    public ActivitySelectTemplateBinding binding;
    
    private Intent intent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivitySelectTemplateBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());
            
        final ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
        
        TemplateManager templateManager = new TemplateManager(); 
        templateManager.register(new NoActivityTemplate(getApplicationContext()));
        
        RecyclerView templateContainer = binding.templateContainer;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        templateContainer.setLayoutManager(gridLayoutManager);
        templateContainer.setAdapter(new TemplatesAdapter(templateManager.getTemplates(), new TemplatesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Template template) {
                intent = new Intent(SelectTemplateActivity.this, CreateProjectActivity.class);
                intent.putExtra("templateClass", template.getClass().getName());
                intent.putExtra("templateName", template.getName());           
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, R.anim.sud_stay);  
            }
        }));
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        binding = null;
        
        intent = null;
    }
}