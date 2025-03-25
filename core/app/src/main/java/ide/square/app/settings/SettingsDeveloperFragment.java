package ide.square.app.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import ide.square.app.R;

public class SettingsDeveloperFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_developer_preferences, rootKey);
    }
}