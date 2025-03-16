package ide.square.app.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ide.square.app.IDEApplication;
import ide.square.app.R;
import ide.square.app.databinding.ActivityMainBinding;

import org.riuntul.material.activity.ToolbarActivity;

import java.io.File;

public class MainActivity extends ToolbarActivity {
    public ActivityMainBinding mBinding;
    
    private Intent mIntent;
    
    private File mConfigDir = new File(IDEApplication.configPath);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (!mConfigDir.exists()) {
            setup();
        } else if (!mConfigDir.isDirectory()) {
            mConfigDir.delete();
            setup();
        }
        
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        
        setContentView(mBinding.getRoot());
        
        init();
        
        // Check READ_EXTERNAL_STORAGE Permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                mIntent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                mIntent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(mIntent);
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }    
    
    protected void setup() {
        mIntent = new Intent(MainActivity.this, SetupActivity.class);
        startActivity(mIntent);
    }
    
    protected void init() {
        FloatingActionButton createProjectFab = mBinding.createProjectFab;
        createProjectFab.setOnClickListener(new View.OnClickListener() {
                
            @Override
            public void onClick(View view) {
                mIntent = new Intent(MainActivity.this, SelectTemplateActivity.class);
                startActivity(mIntent);
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                mIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(mIntent);
            
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        
        mBinding = null;
        mIntent = null;
    }
}