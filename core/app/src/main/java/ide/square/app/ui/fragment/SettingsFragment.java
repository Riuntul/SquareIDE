package ide.square.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import ide.square.app.R;
import ide.square.app.ui.activity.SettingsActivity;
import ide.square.app.ui.activity.ThemesActivity;

public class SettingsFragment extends PreferenceFragmentCompat {
    private Intent intent;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey);
        
        Preference themesPreference = findPreference("themes");
        themesPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                
            @Override
            public boolean onPreferenceClick(Preference preference) {
                intent = new Intent(getActivity(), ThemesActivity.class);
                startActivity(intent);
                    
                return true;        
            }
        });
    }
}