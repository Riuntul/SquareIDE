package ide.square.app.settings;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import ide.square.app.IDESettings;
import ide.square.app.R;

public class SettingsStyleFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        getPreferenceManager().setStorageDeviceProtected();
        
        getPreferenceManager().setSharedPreferencesName(IDESettings.NAME);
        getPreferenceManager().setSharedPreferencesMode(Context.MODE_PRIVATE);

        setPreferencesFromResource(R.xml.settings_style_preferences, rootKey);
        
        Preference darkModePreference = findPreference("dark_mode");
        
        darkModePreference.setDefaultValue(Integer.parseInt(IDESettings.getDarkMode()));
        darkModePreference.setOnPreferenceChangeListener((pref, returnValue) -> {
            if (IDESettings.getDarkMode() != returnValue) {
                AppCompatDelegate.setDefaultNightMode(Integer.parseInt((String) returnValue));
                getActivity().recreate();    
            }
            return true;
        });
    }
}