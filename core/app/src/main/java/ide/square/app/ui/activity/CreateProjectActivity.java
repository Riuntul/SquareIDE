package ide.square.app.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import ide.square.app.R;
import ide.square.app.databinding.ActivityCreateProjectBinding;
import ide.square.app.template.Template;
import org.riuntul.material.activity.CollapsingToolbarActivity;

public class CreateProjectActivity extends CollapsingToolbarActivity {
    public ActivityCreateProjectBinding binding;
    
    private Class<Template> templateClass;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setTitle(getIntent().getIntExtra("templateName", 0));
        
        binding = ActivityCreateProjectBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());
    }
    
    @Override
    public void finish() {
        super.finish();
        
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        binding = null;
    }
}
