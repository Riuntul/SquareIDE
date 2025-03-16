package ide.square.app.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import ide.square.app.databinding.FragmentWelcomeBinding;
import org.riuntul.material.activity.CollapsingToolbarFragment;

public class WelcomeFragment extends CollapsingToolbarFragment {
    public FragmentWelcomeBinding mBinding;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(View parent, Bundle savedInstanceState) {}
}