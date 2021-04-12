package com.project.universitystudentassistant.ui.Fragments.Filter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.android.material.chip.Chip;
import com.google.android.material.slider.RangeSlider;
import com.google.gson.Gson;
import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.data.entities.Sort;
import com.project.universitystudentassistant.databinding.FragmentFilterBinding;
import com.project.universitystudentassistant.ui.Fragments.SearchUniversity.SearchUniversityFragment;
import com.project.universitystudentassistant.utils.AppConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilterFragment extends DialogFragment {
    private FragmentFilterBinding binding;
    private ArrayAdapter<String> statesNamesAdapter;
    private FilterFragmentViewModel filterViewModel;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Sort sort = new Sort();
    private List<String> chosenStates = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbarFilter.inflateMenu(R.menu.filter_menu);
        filterViewModel = new ViewModelProvider(this).get(FilterFragmentViewModel.class);


        binding.rangeSliderCost.addOnChangeListener((slider, value, fromUser) -> {
            List<Float> values = slider.getValues();
            sort.setStartCost(Math.round(Collections.min(values)));
            sort.setEndCost(Math.round(Collections.max(values)));
        });

        binding.rangeSliderAcceptanceRate.addOnChangeListener((slider, value, fromUser) -> {
            List<Float> values = slider.getValues();
            sort.setStartAccRate(Math.round(Collections.min(values)));
            sort.setEndAccRate(Math.round(Collections.max(values)));
        });

        binding.rangeSliderGradRate.addOnChangeListener((slider, value, fromUser) -> {
            List<Float> values = slider.getValues();
            sort.setStartGradRate(Math.round(Collections.min(values)));
            sort.setEndGradRate(Math.round(Collections.max(values)));
        });

        statesNamesAdapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.states));
        binding.searchViewStates.setAdapter(statesNamesAdapter);

        binding.searchViewStates.setOnItemClickListener((adapterView, view1, i, l) -> setStateChip());
        binding.btnAdd.setOnClickListener(view12 -> setStateChip());


        binding.toolbarFilter.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_close) {
                    getDialog().dismiss();
                }
                if (item.getItemId() == R.id.menu_apply) {
                    sort.setStatesRange(chosenStates);
                    setSortCriteria();
                    getDialog().dismiss();
                }
                return true;
            }
        });
    }

    private void setStateChip() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        Chip chip = (Chip) layoutInflater.inflate(R.layout.state_chip_item, null, false);
        String stateName = binding.searchViewStates.getText().toString();
        chosenStates.add(stateName);
        chip.setText(stateName);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chipGroupStates.removeView(chip);
            }
        });
        binding.chipGroupStates.addView(chip);
        binding.searchViewStates.setText("");
    }


    private void setSortCriteria() {
        Gson gson = new Gson();
        String jsonData = gson.toJson(sort);
        sendResult(jsonData);
    }

    private void sendResult(String sortMessage) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = SearchUniversityFragment.newIntent(sortMessage);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
        dismiss();
    }

}