package ide.square.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ide.square.app.R;
import ide.square.app.databinding.ActivitySelectTemplateBinding;
import ide.square.app.template.NoActivityTemplate;
import ide.square.app.template.Template;
import ide.square.app.template.TemplateManager;
import ide.square.app.template.TemplatesAdapter;

import org.riuntul.material.activity.CollapsingToolbarActivity;

public class SelectTemplateActivity extends CollapsingToolbarActivity {
    public ActivitySelectTemplateBinding mBinding;
    
    private Intent mIntent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivitySelectTemplateBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
            
        final ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_close);
        }
        
        TemplateManager templateManager = new TemplateManager(); 
        templateManager.register(new NoActivityTemplate(getApplicationContext()));
        
        RecyclerView templateContainer = mBinding.templateContainer;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        templateContainer.setLayoutManager(gridLayoutManager);
        templateContainer.setAdapter(new TemplatesAdapter(templateManager.getTemplates(), new TemplatesAdapter.OnItemClickListener() {
                    
            @Override
            public void onItemClick(Template template) {
                mIntent = new Intent(SelectTemplateActivity.this, CreateProjectActivity.class);
                mIntent.putExtra("templateClass", template);
                if (template.getTitleIntRes() != 0) {
                    mIntent.putExtra("templateName", template.getTitleIntRes());       
                } else {           
                    mIntent.putExtra("templateName", template.getTitle());
                }              
                startActivity(mIntent);
            }
        }));
    }
    
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(org.riuntul.material.R.anim.sud_slide_next_in, org.riuntul.material.R.anim.sud_slide_next_out);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        mBinding = null;
        mIntent = null;
    }
}