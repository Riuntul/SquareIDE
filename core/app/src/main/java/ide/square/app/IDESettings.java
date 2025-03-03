package ide.square.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import ide.square.app.utils.EmptySharedPreferencesImpl;

public class IDESettings {
    public static final String NAME = "settings";
    public static final String DARK_MODE = "dark_mode";
    
    private static SharedPreferences preferences;

    public static SharedPreferences getPreferences() {
        return preferences;
    }

    @NonNull
    private static Context getSettingsStorageContext(@NonNull Context context) {
        Context storageContext;
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            storageContext = context.createDeviceProtectedStorageContext();
        } else {
            storageContext = context;
        }

        storageContext = new ContextWrapper(storageContext) {
            
            @Override
            public SharedPreferences getSharedPreferences(String name, int mode) {
                try {
                    return super.getSharedPreferences(name, mode);
                } catch (IllegalStateException e) {
                    return new EmptySharedPreferencesImpl();
                }
            }
        };

        return storageContext;
    }

    public static void initialize(Context context) {
        if (preferences == null) {
            preferences = getSettingsStorageContext(context).getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
    }

    @AppCompatDelegate.NightMode
    public static String getDarkMode() {
        int defValue = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
        return getPreferences().getString(DARK_MODE, String.valueOf(defValue));
    }
}