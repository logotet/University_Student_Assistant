package com.project.universitystudentassistant.ui.university.Fragments.MyUniversities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.adapters.UniversityAdapter;
import com.project.universitystudentassistant.data.models.UniversityEntity;
import com.project.universitystudentassistant.databinding.FragmentMyUniversitiesBinding;
import com.project.universitystudentassistant.utils.AppConstants;

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


        myUniversitiesViewModel.getSavedUniversities().observe(getViewLifecycleOwner(), new Observer<List<UniversityEntity>>() {
            @Override
            public void onChanged(List<UniversityEntity> universityEntities) {
                universities = universityEntities;
                adapter.updateData(universities);
                toggleTextInfoVisibility();
            }
        });
    }

    @Override
    public void onFavButtonClicked(UniversityEntity entity) {
        entity.setImage("");
        entity.setSelected(false);
        myUniversitiesViewModel.updateUniversity(entity);

    }

    @Override
    public void onWebPageClicked(UniversityEntity entity) {
        //TODO: extract this logic to avoid duplication
        try {
            String url = entity.getWebPage();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }catch (Exception e){
            Log.e("error", e.getMessage());
        }
    }

    @Override
    public void onAddressClicked(UniversityEntity entity) {
        String location = "geo:0,0?q="+entity.getAddress()+", "+entity.getCity()+", " + entity.getState();
        Uri gmmIntentUri = Uri.parse(location);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void toggleTextInfoVisibility() {
        if (universities != null && universities.size() > 0) {
            binding.txtFavouriteUniversities.setVisibility(View.GONE);
            binding.recViewUniversities.setVisibility(View.VISIBLE);
        } else {
            binding.txtFavouriteUniversities.setVisibility(View.VISIBLE);
            binding.recViewUniversities.setVisibility(View.GONE);

        }
    }
}