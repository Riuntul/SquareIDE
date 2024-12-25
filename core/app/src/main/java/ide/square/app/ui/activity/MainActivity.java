package ide.square.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import ide.square.app.R;
import ide.square.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());
        
        init_navbar();
    }    
    
    protected void init_navbar() {
        Log.i("InitManager", "Started Navbar Init");
        
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.content);
        NavController navController = navHostFragment.getNavController();
        
        BottomNavigationView navbar = binding.navbar;
        
        NavigationUI.setupWithNavController(navbar, navController);
        
        navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.project_nav) {
                    navController.navigate(R.id.project_fragment);
                        
                    return true;    
                } else if (item.getItemId() == R.id.settings_nav) {
                	navController.navigate(R.id.settings_fragment);
                        
                    return true;    
                }
                return false;           
            }
        });
        
        Log.i("InitManager", "Finish Navbar Init");
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        
        binding = null;
    }
}