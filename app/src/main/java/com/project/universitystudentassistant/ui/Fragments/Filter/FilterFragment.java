package com.project.universitystudentassistant.ui.Fragments.Filter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.material.chip.Chip;
import com.google.android.material.slider.RangeSlider;
import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.databinding.FragmentFilterBinding;

import java.util.List;

public class FilterFragment extends DialogFragment {
    private FragmentFilterBinding binding;
    private ArrayAdapter<String> statesNamesAdapter;

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static FilterFragment newInstance() {
        return new FilterFragment();
    }
//    public static FilterFragment newInstance(String param1, String param2) {
//        FilterFragment fragment = new FilterFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }


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
//        ViewHelper.setUpToolbar((AppCompatActivity) getActivity(), binding.toolbarFilter);
        binding.toolbarFilter.inflateMenu(R.menu.filter_menu);

        binding.rangeSliderCost.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull RangeSlider slider) {
                List<Float> values = binding.rangeSliderCost.getValues();
                Log.d("onStartTrackingTouch From", values.get(0).toString());
                Log.d("onStartTrackingTouch T0", values.get(1).toString());
            }

            @Override
            public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                List<Float> values = binding.rangeSliderCost.getValues();
                Log.d("onStartTrackingTouch From", values.get(0).toString());
                Log.d("onStartTrackingTouch T0", values.get(1).toString());
            }
        });

        statesNamesAdapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.states));
        binding.searchViewStates.setAdapter(statesNamesAdapter);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                Chip chip = (Chip) layoutInflater.inflate(R.layout.state_chip_item, null, false);
                chip.setText(binding.searchViewStates.getText().toString());
                chip.setOnCloseIconClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.chipGroupStates.removeView(chip);
                    }
                });
                binding.chipGroupStates.addView(chip);
                binding.searchViewStates.setText("");
            }
        });

        binding.toolbarFilter.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menu_close){
                    getDialog().dismiss();
                }
                return true;
            }
        });
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.filter_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
}