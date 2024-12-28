package ide.square.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.textview.MaterialTextView;
import ide.square.app.databinding.ActivitySelectTemplateBinding;

public class SelectTemplateActivity extends FragmentActivity {
    public ActivitySelectTemplateBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivitySelectTemplateBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());
        
        MaterialTextView title = binding.title;
        title.setTextSize(25);
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