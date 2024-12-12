package ide.square.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import ide.square.app.R;
import ide.square.app.databinding.FragmentProjectBinding;
import ide.square.app.ui.activity.CreateProjectActivity;

public class ProjectFragment extends Fragment {
    public FragmentProjectBinding binding;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProjectBinding.inflate(getLayoutInflater(), container, false);
        
        return binding.getRoot();
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        MaterialTextView messageTextView = binding.messageTextview;
        messageTextView.setText(R.string.not_project);
        
        FloatingActionButton createProjectFab = binding.createProjectFab;
        createProjectFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateProjectActivity.class);
                startActivity(intent);
            }
        });
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroy();
        
        binding = null;
    }
}