package ide.square.app.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import ide.square.app.R;
import ide.square.app.databinding.ActivityCreateProjectBinding;
import ide.square.app.template.NoActivityTemplate;
import ide.square.app.template.Template;
import ide.square.app.template.TemplateBuilder;
import ide.square.app.template.TemplateManager;
import java.io.File;
import java.io.IOException;
import org.riuntul.material.activity.CollapsingToolbarActivity;
import org.riuntul.material.activity.ToolbarActivity;

public class CreateProjectActivity extends CollapsingToolbarActivity {
    public ActivityCreateProjectBinding mBinding;
    
    private Template mTemplate;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setTitle(getIntent().getIntExtra("templateName", 0));
        
        mBinding = ActivityCreateProjectBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
        
        final ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mTemplate = getIntent().getParcelableExtra("templateClass", Template.class);
        } else {
            mTemplate = getIntent().getParcelableExtra("templateClass");
        }
        
        MaterialButton nextButton = mBinding.next;
        nextButton.setOnClickListener((view) -> {
            TemplateManager templateManager = new TemplateManager();
            Template template = getIntent().getParcelableExtra("templateClass");
            template.onCreate(templateManager);    
            templateManager.outputProject(template, "/sdcard/AndroidIDEProjects/Test");
        });
    }
    
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(org.riuntul.material.R.anim.sud_slide_back_in, org.riuntul.material.R.anim.sud_slide_back_out);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}
