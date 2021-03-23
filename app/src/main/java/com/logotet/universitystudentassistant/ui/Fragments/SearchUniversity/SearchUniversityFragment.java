package com.logotet.universitystudentassistant.ui.Fragments.SearchUniversity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.logotet.universitystudentassistant.R;
import com.logotet.universitystudentassistant.adapters.UniversityAdapter;
import com.logotet.universitystudentassistant.data.models.UniversityEntity;
import com.logotet.universitystudentassistant.databinding.FragmentSearchUniversitiesBinding;

import java.util.List;

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

        UniversityAdapter adapter = new UniversityAdapter();
        binding.recViewUniversities.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recViewUniversities.setAdapter(adapter);

        searchUniversityViewModel.getUniversitiesList().observe(getViewLifecycleOwner(),
                new Observer<List<UniversityEntity>>() {
                    @Override
                    public void onChanged(List<UniversityEntity> universityEntities) {
                        adapter.updateData(universityEntities);
                    }
                });
    }
}