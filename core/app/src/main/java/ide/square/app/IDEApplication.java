package ide.square.app;

import android.app.Application;
import android.content.pm.PackageManager;
import com.google.android.material.color.DynamicColors;

public class IDEApplication extends Application {
    public static String configPath = "/data/data/ide.square.app/files/config";
    
    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}