package ide.square.app.settings;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import ide.square.app.R;

public class SettingsStyleFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_style_preferences, rootKey);
    }
}