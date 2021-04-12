package com.project.universitystudentassistant.ui.Fragments.SearchUniversity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.adapters.UniversityAdapter;
import com.project.universitystudentassistant.data.entities.EntityConverter;
import com.project.universitystudentassistant.data.entities.Sort;
import com.project.universitystudentassistant.data.entities.UniversityEntity;
import com.project.universitystudentassistant.data.entities.UniversityEntityPrep;
import com.project.universitystudentassistant.databinding.FragmentSearchUniversitiesBinding;
import com.project.universitystudentassistant.ui.Fragments.Filter.FilterFragment;
import com.project.universitystudentassistant.utils.AppConstants;
import com.project.universitystudentassistant.utils.SortManager;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SearchUniversityFragment extends Fragment implements UniversityAdapter.UniversityHolder.OnItemPressedListener {

    private FragmentSearchUniversitiesBinding binding;
    private SearchUniversityViewModel searchUniversityViewModel;
    private UniversityAdapter adapter;
    private List<UniversityEntity> universities;
    private final static int TARGET_FRAGMENT_REQUEST_CODE = 1;

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
                new Observer<List<UniversityEntity>>() {
                    @Override
                    public void onChanged(List<UniversityEntity> universityEntities) {
                        SortManager sortManager = new SortManager();
                        universities = sortManager.sortByCost(universityEntities);

                        adapter.updateData(universities);
                    }
                });



    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
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
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
        }
        if (item.getItemId() == R.id.filter) {
            DialogFragment filterFragment = new FilterFragment();
            filterFragment.setTargetFragment(SearchUniversityFragment.this, TARGET_FRAGMENT_REQUEST_CODE);
            filterFragment.show(getParentFragmentManager(), "sort");
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
        if(!entity.isSelected()) {
            entity.setImage(AppConstants.SAVED);
        }else {
            entity.setImage("");
        }
        searchUniversityViewModel.updateUniversity(entity);
        Toast.makeText(getContext(), entity.getName() + " was added to your favourites.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWebPageClicked(UniversityEntity entity) {
        try {
            String url = entity.getWebPage();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
    }

    @Override
    public void onAddressClicked(UniversityEntity entity) {
        String location = "geo:0,0?q=" + entity.getAddress() + ", " + entity.getCity() + ", " + entity.getState();
        Uri gmmIntentUri = Uri.parse(location);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == TARGET_FRAGMENT_REQUEST_CODE) {
            binding.recViewUniversities.scrollToPosition(0);
            String sortMessage = data.getStringExtra("sort");
            Gson gson = new Gson();
            Sort sort = gson.fromJson(sortMessage, Sort.class);
            String[] states = sort.getStatesRange().toArray(new String[sort.getStatesRange().size()]);

            searchUniversityViewModel.getUniversitiesListA(states)
                    .observe(getViewLifecycleOwner(),
                            new Observer<List<UniversityEntity>>() {
                                @Override
                                public void onChanged(List<UniversityEntity> universityEntities) {
                                    SortManager sortManager = new SortManager();
                                    List<UniversityEntity> universityEntityPreps1
                                            = sortManager.filterUniversities(sort, universityEntities);
                                    sortManager.sortBy(sort.getSortBy(), sortManager.filterUniversities(sort, universityEntities));
                                    universities = universityEntityPreps1;
                                    //TODO change the logic with a sort builder(sortManager.sortBy(sort.getSortBy(), universityEntityPreps));
                                    adapter.updateData(universities);
                                }
                            });
        }
    }

    public static Intent newIntent(String sortJson) {
        Intent intent = new Intent();
        intent.putExtra("sort", sortJson);
        return intent;
    }
}