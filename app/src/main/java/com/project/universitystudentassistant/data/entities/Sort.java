package com.project.universitystudentassistant.data.entities;

import com.project.universitystudentassistant.utils.AppConstants;
import com.project.universitystudentassistant.utils.StateNameConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sort {
    private String sortBy = AppConstants.ACCEPTANCE_RATE;
    private int startCost;
    private int endCost;
    private int startAccRate = 1;
    private int endAccRate = 100;
    private int startGradRate = 1;
    private int endGradRate = 100;
    private List<String> statesRange = new ArrayList<>();
    private StateNameConverter stateNameConverter = StateNameConverter.getInstance();

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getStartCost() {
        return startCost;
    }

    public void setStartCost(int startCost) {
        this.startCost = startCost;
    }

    public int getEndCost() {
        return endCost;
    }

    public void setEndCost(int endCost) {
        this.endCost = endCost;
    }

    public int getStartAccRate() {
        return startAccRate;
    }

    public void setStartAccRate(int startAccRate) {
        this.startAccRate = startAccRate;
    }

    public int getEndAccRate() {
        return endAccRate;
    }

    public void setEndAccRate(int endAccRate) {
        this.endAccRate = endAccRate;
    }

    public int getStartGradRate() {
        return startGradRate;
    }

    public void setStartGradRate(int startGradRate) {
        this.startGradRate = startGradRate;
    }

    public int getEndGradRate() {
        return endGradRate;
    }

    public void setEndGradRate(int endGradRate) {
        this.endGradRate = endGradRate;
    }

    public void addState(String state){
        statesRange.add(stateNameConverter.getStateCode(state));
    }

    public List<String> getStatesRange() {
        return statesRange;
    }

    public void setStatesRange(List<String> statesRange) {
        this.statesRange = statesRange.stream()
                .map(s -> s = stateNameConverter.getStateCode(s))
                .collect(Collectors.toList());
    }



}
