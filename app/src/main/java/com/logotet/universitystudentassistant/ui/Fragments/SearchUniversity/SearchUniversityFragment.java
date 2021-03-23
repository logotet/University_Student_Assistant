package com.logotet.universitystudentassistant.ui.Fragments.SearchUniversity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.logotet.universitystudentassistant.R;

public class SearchUniversityFragment extends Fragment {

    private SearchUniversityViewModel searchUniversityViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        searchUniversityViewModel =
                new ViewModelProvider(this).get(SearchUniversityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search_universities, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        searchUniversityViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}