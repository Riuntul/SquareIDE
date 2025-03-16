package ide.square.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.color.utilities.ColorUtils;
import java.io.IOException;

public class IDEApplication extends Application {
    public static String configPath = "/data/data/ide.square.app/files/config";
    public static String sdkPath = "/data/data/ide.square.app/files/framework";
    
    public static String gradleUserHome = "/data/data/ide.square.app/files/.gradle";
    
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