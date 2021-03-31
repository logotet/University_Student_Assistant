package com.project.universitystudentassistant.ui.Fragments.SearchUniversity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.adapters.UniversityAdapter;
import com.project.universitystudentassistant.data.entities.EntityConverter;
import com.project.universitystudentassistant.data.entities.UniversityEntity;
import com.project.universitystudentassistant.data.entities.UniversityEntityPrep;
import com.project.universitystudentassistant.databinding.FragmentSearchUniversitiesBinding;
import com.project.universitystudentassistant.utils.AppConstants;

import java.util.List;
import java.util.stream.Collectors;

public class SearchUniversityFragment extends Fragment implements UniversityAdapter.UniversityHolder.OnItemPressedListener {

    private FragmentSearchUniversitiesBinding binding;
    private SearchUniversityViewModel searchUniversityViewModel;
    private UniversityAdapter adapter;
    private List<UniversityEntity> universities;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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

        adapter = new UniversityAdapter(this, AppConstants.FRAGMENT_SEARCH_UNIVERSITIES);
        binding.recViewUniversities.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recViewUniversities.setAdapter(adapter);

        searchUniversityViewModel.getUniversitiesList().observe(getViewLifecycleOwner(),
                new Observer<List<UniversityEntityPrep>>() {
                    @Override
                    public void onChanged(List<UniversityEntityPrep> universityEntityPreps) {
                        universities = EntityConverter.convertToMyUnis(universityEntityPreps);
                        adapter.updateData(universities);
                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_universities_menu, menu);
        SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.updateData(getQueriedList(universities, newText));
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
        }
        return true;
    }

    //TODO: extract the method in a separate class.
    private List<UniversityEntity> getQueriedList(List<UniversityEntity> universityEntities, String query) {
        return universityEntities.stream()
                .filter(entity -> entity.getName().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void onFavButtonClicked(UniversityEntity entity) {
        searchUniversityViewModel.insertUniversity(entity);
        Toast.makeText(getContext(), entity.getName() +" was added to your favourites.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWebPageClicked(UniversityEntity entity) {
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
}