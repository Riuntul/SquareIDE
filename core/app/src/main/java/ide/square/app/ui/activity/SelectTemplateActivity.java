package ide.square.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import org.riuntul.material.activity.CollapsingToolbarActivity;
import com.google.android.material.textview.MaterialTextView;
import ide.square.app.databinding.ActivitySelectTemplateBinding;

public class SelectTemplateActivity extends CollapsingToolbarActivity {
    public ActivitySelectTemplateBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivitySelectTemplateBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 0:
                super.onActivityResult(requestCode, resultCode, data);
                
                break;
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        binding = null;
    }
}