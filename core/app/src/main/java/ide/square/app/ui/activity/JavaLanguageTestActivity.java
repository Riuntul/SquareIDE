package ide.square.app.ui.activity;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import ide.square.app.databinding.ActivityJavaLanguageTestBinding;
import ide.square.app.language.service.JavaLanguageService;
import ide.square.lsp.JavaLanguageServer;
import org.riuntul.material.activity.CollapsingToolbarActivity;
import org.riuntul.material.activity.ToolbarActivity;

public class JavaLanguageTestActivity extends ToolbarActivity {
    public ActivityJavaLanguageTestBinding mBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = ActivityJavaLanguageTestBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}