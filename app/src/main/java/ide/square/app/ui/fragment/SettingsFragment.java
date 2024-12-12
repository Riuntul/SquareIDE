package ide.square.app.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ide.square.app.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    public FragmentSettingsBinding binding;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(getLayoutInflater(), container, false);
        
        return binding.getRoot();
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroy();
        
        binding = null;
    }
}