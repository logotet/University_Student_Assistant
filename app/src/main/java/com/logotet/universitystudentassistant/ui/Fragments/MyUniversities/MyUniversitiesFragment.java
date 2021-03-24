package com.logotet.universitystudentassistant.ui.Fragments.MyUniversities;

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
import com.logotet.universitystudentassistant.data.entities.UniversityEntity;
import com.logotet.universitystudentassistant.databinding.FragmentMyUniversitiesBinding;
import com.logotet.universitystudentassistant.utils.AppConstants;

import java.util.List;

public class MyUniversitiesFragment extends Fragment implements UniversityAdapter.UniversityHolder.OnItemPressedListener {

    private MyUniversitiesViewModel myUniversitiesViewModel;
    private FragmentMyUniversitiesBinding binding;
    private UniversityAdapter adapter;
    private List<UniversityEntity> universities;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_universities, container, false);
        binding = DataBindingUtil.bind(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myUniversitiesViewModel =
                new ViewModelProvider(this).get(MyUniversitiesViewModel.class);

        adapter = new UniversityAdapter(this, AppConstants.FRAGMENT_MY_UNIVERSITIES);
        binding.recViewUniversities.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recViewUniversities.setAdapter(adapter);

        myUniversitiesViewModel.getUniversities().observe(getViewLifecycleOwner(), new Observer<List<UniversityEntity>>() {
            @Override
            public void onChanged(List<UniversityEntity> universityEntities) {
                universities = universityEntities;
                adapter.updateData(universities);
            }
        });
    }

    @Override
    public void onFavButtonClicked(UniversityEntity entity) {
        myUniversitiesViewModel.deleteUniversity(entity);
    }
}