package ide.square.app.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import ide.square.app.R;
import ide.square.app.IDESettings;

public class ThemesFragment extends PreferenceFragmentCompat {
    private ListPreference darkModePreference;
    
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        getPreferenceManager().setStorageDeviceProtected();
        getPreferenceManager().setSharedPreferencesName(IDESettings.NAME);
        getPreferenceManager().setSharedPreferencesMode(Context.MODE_PRIVATE);
        
        setPreferencesFromResource(R.xml.themes_preferences, rootKey);
        
        darkModePreference = findPreference("dark_mode");
        
        darkModePreference.setOnPreferenceChangeListener((preference, newValue) -> {
            if (Integer.parseInt(IDESettings.getDarkMode()) != Integer.parseInt(String.valueOf(newValue))) {
                AppCompatDelegate.setDefaultNightMode(Integer.parseInt(String.valueOf(newValue)));    
            }   
                
            return true;    
        });
    }
}