package ide.square.app.ui.fragment;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import ide.square.app.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}