package ide.square.app.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ide.square.app.databinding.ActivityEditorBinding;

public class EditorActivity extends AppCompatActivity {
    public ActivityEditorBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityEditorBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());
    }
}