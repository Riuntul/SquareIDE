package ide.square.app.settings.homepage;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import ide.square.app.R;
import ide.square.app.settings.core.SubSettingLauncher;

public class SettingsHomepageFragment extends PreferenceFragmentCompat implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    
    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        new SubSettingLauncher(getActivity()).setDestination(pref.getFragment()).setTitleRes(-1).setTitleText(pref.getTitle()).setIsSecondLayerPage(true).launch();
        getActivity().overridePendingTransition(org.riuntul.material.R.anim.sud_slide_next_in, org.riuntul.material.R.anim.sud_slide_next_out);
        return true;
    }
    
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_homepage_preferences, rootKey);
    }
}