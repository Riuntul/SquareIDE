package ide.square.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.color.utilities.ColorUtils;

public class IDEApplication extends Application {
    public static String configPath = "/data/data/ide.square.app/files/config";
    public static String sdkPath = "/data/data/ide.square.app/files/framework";
    
    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }
    
    private void init(Context context) {
        IDESettings.initialize(context);
        
        DynamicColors.applyToActivitiesIfAvailable(this);
        AppCompatDelegate.setDefaultNightMode(Integer.parseInt(IDESettings.getDarkMode()));
    }
}