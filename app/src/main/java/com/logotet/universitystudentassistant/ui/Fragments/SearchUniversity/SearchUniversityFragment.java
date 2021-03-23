package com.logotet.universitystudentassistant.ui.Fragments.SearchUniversity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.logotet.universitystudentassistant.R;
import com.logotet.universitystudentassistant.databinding.FragmentSearchUniversitiesBinding;

public class SearchUniversityFragment extends Fragment {

    private FragmentSearchUniversitiesBinding binding;
    private SearchUniversityViewModel searchUniversityViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search_universities, container, false);
        binding = DataBindingUtil.bind(root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchUniversityViewModel =
                new ViewModelProvider(this).get(SearchUniversityViewModel.class);
        searchUniversityViewModel.getUniversitiesList().observe(getViewLifecycleOwner(),
                universityEntities -> binding.textDashboard.setText(universityEntities.get(3).getName()));
    }
}