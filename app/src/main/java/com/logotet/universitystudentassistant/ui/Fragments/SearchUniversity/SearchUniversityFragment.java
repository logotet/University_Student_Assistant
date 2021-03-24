package com.logotet.universitystudentassistant.ui.Fragments.SearchUniversity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.logotet.universitystudentassistant.R;
import com.logotet.universitystudentassistant.adapters.UniversityAdapter;
import com.logotet.universitystudentassistant.data.entities.UniversityEntity;
import com.logotet.universitystudentassistant.databinding.FragmentSearchUniversitiesBinding;

import java.util.List;
import java.util.stream.Collectors;

public class SearchUniversityFragment extends Fragment {

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

        adapter = new UniversityAdapter();
        binding.recViewUniversities.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recViewUniversities.setAdapter(adapter);

        searchUniversityViewModel.getUniversitiesList().observe(getViewLifecycleOwner(),
                new Observer<List<UniversityEntity>>() {
                    @Override
                    public void onChanged(List<UniversityEntity> universityEntities) {
                        universities = universityEntities;
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
}