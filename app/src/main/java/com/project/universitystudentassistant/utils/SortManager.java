package com.project.universitystudentassistant.utils;

import com.project.universitystudentassistant.data.entities.Sort;
import com.project.universitystudentassistant.data.entities.UniversityEntity;
import com.project.universitystudentassistant.data.entities.UniversityEntityPrep;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortManager {

    private Sort sort;

    //Sorting methods
    public List<UniversityEntityPrep> sortByStates(List<UniversityEntityPrep> entityPreps) {
        return entityPreps.stream().sorted(Comparator.comparing(UniversityEntity::getState))
                .collect(Collectors.toList());
    }

    public List<UniversityEntityPrep> sortByName(List<UniversityEntityPrep> entityPreps) {
        return entityPreps.stream().sorted(Comparator.comparing(UniversityEntity::getName))
                .collect(Collectors.toList());
    }

    public List<UniversityEntityPrep> sortByCost(List<UniversityEntityPrep> entityPreps) {
        return entityPreps.stream().sorted(Comparator.comparing(UniversityEntity::getCostOfAttendance))
                .collect(Collectors.toList());
    }

    public List<UniversityEntityPrep> sortByAccRate(List<UniversityEntityPrep> entityPreps) {
        return entityPreps.stream().sorted(Comparator.comparing(UniversityEntity::getAcceptanceRate))
                .collect(Collectors.toList());
    }

    public List<UniversityEntityPrep> sortByGradRate(List<UniversityEntityPrep> entityPreps) {
        return entityPreps.stream().sorted(Comparator.comparing(UniversityEntity::getGraduationRate))
                .collect(Collectors.toList());
    }

    public void sortBy(String sortBy, List<UniversityEntityPrep> entityPreps) {
        switch (sortBy) {
            case AppConstants.NAMES:
                sortByName(entityPreps);
                break;
            case AppConstants.STATES:
                sortByStates(entityPreps);
                break;
            case AppConstants.ATTENDANCE_COST:
                sortByCost(entityPreps);
                break;
            case AppConstants.ACCEPTANCE_RATE:
                sortByAccRate(entityPreps);
                break;
            case AppConstants.GRADUATION_RATE:
                sortByGradRate(entityPreps);
                break;
        }
    }

    //TODO add asc/desc order option

    //Filter methods
    public List<UniversityEntityPrep> filterByPriceRange(int startPrice, int endPrice, List<UniversityEntityPrep> entityPreps) {
        return entityPreps.stream().filter(e -> e.getCostOfAttendance() >= startPrice)
                .filter(e -> e.getCostOfAttendance() <= endPrice)
                .collect(Collectors.toList());
    }

    public List<UniversityEntityPrep> filterByAccRange(int startRate, int endRate, List<UniversityEntityPrep> entityPreps) {
        return entityPreps.stream().filter(e -> e.getCostOfAttendance() >= startRate)
                .filter(e -> e.getCostOfAttendance() <= endRate)
                .collect(Collectors.toList());
    }

    public List<UniversityEntityPrep> filterByGradRange(int startRate, int endRate, List<UniversityEntityPrep> entityPreps) {
        return entityPreps.stream().filter(e -> e.getCostOfAttendance() >= startRate)
                .filter(e -> e.getCostOfAttendance() <= endRate)
                .collect(Collectors.toList());
    }

    public List<UniversityEntityPrep> filterUniversities(Sort sort, List<UniversityEntityPrep> universities) {
        List<UniversityEntityPrep> unis =  universities.stream()
//                .filter(u -> u.getCostOfAttendance() >= sort.getStartCost())
//                .filter(u -> u.getCostOfAttendance() <= sort.getEndCost())
                .filter(u -> u.getAcceptanceRate() >= sort.getStartAccRate())
                .filter(u -> u.getAcceptanceRate() <= sort.getEndAccRate())
                .filter(u -> u.getGraduationRate() >= sort.getStartGradRate())
                .filter(u -> u.getGraduationRate() <= sort.getEndGradRate())
                .collect(Collectors.toList());
        sortBy(sort.getSortBy(), unis);
        return unis;
    }

}
