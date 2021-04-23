package com.project.universitystudentassistant.utils;

import com.project.universitystudentassistant.models.Sort;
import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.models.SubjectTime;
import com.project.universitystudentassistant.models.UniversityEntity;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SortManager {

    private Sort sort;

    //Sorting methods
    public List<UniversityEntity> sortByStates(List<UniversityEntity> entityPreps) {
        return entityPreps.stream().sorted(Comparator.comparing(UniversityEntity::getState))
                .collect(Collectors.toList());
    }

    public List<UniversityEntity> sortByName(List<UniversityEntity> entityPreps) {
        return entityPreps.stream().sorted(Comparator.comparing(UniversityEntity::getName))
                .collect(Collectors.toList());
    }

    public List<UniversityEntity> sortByCost(List<UniversityEntity> entityPreps) {
        return entityPreps.stream().sorted(Comparator.comparing(UniversityEntity::getCostOfAttendance))
                .collect(Collectors.toList());
    }

    public List<UniversityEntity> sortByAccRate(List<UniversityEntity> entityPreps) {
        return entityPreps.stream().sorted(Comparator.comparing(UniversityEntity::getAcceptanceRate))
                .collect(Collectors.toList());
    }

    public List<UniversityEntity> sortByGradRate(List<UniversityEntity> entityPreps) {
        return entityPreps.stream().sorted(Comparator.comparing(UniversityEntity::getGraduationRate))
                .collect(Collectors.toList());
    }

    public List<UniversityEntity> sortBy(String sortBy, List<UniversityEntity> entityPreps) {
        List<UniversityEntity> sortedUniversities = new ArrayList<>();
        switch (sortBy) {
            case AppConstants.NAME:
                sortedUniversities = sortByName(entityPreps);
                break;
            case AppConstants.STATE:
                sortedUniversities = sortByStates(entityPreps);
                break;
            case AppConstants.ATTENDANCE_COST:
                sortedUniversities = sortByCost(entityPreps);
                break;
            case AppConstants.ACCEPTANCE_RATE:
                sortedUniversities = sortByAccRate(entityPreps);
                break;
            case AppConstants.GRADUATION_RATE:
                sortedUniversities = sortByGradRate(entityPreps);
                break;
        }
        return sortedUniversities;
    }

    //TODO add asc/desc order option

    public void reverse(List<UniversityEntity> entityPreps) {
             Collections.reverse(entityPreps);
    }

    //Filter methods
    public List<UniversityEntity> filterByPriceRange(int startPrice, int endPrice, List<UniversityEntity> entityPreps) {
        return entityPreps.stream().filter(e -> e.getCostOfAttendance() >= startPrice)
                .filter(e -> e.getCostOfAttendance() <= endPrice)
                .collect(Collectors.toList());
    }

    public List<UniversityEntity> filterByAccRange(int startRate, int endRate, List<UniversityEntity> entityPreps) {
        return entityPreps.stream().filter(e -> e.getCostOfAttendance() >= startRate)
                .filter(e -> e.getCostOfAttendance() <= endRate)
                .collect(Collectors.toList());
    }

    public List<UniversityEntity> filterByGradRange(int startRate, int endRate, List<UniversityEntity> entityPreps) {
        return entityPreps.stream().filter(e -> e.getCostOfAttendance() >= startRate)
                .filter(e -> e.getCostOfAttendance() <= endRate)
                .collect(Collectors.toList());
    }

    public List<UniversityEntity> filterUniversities(Sort sort, List<UniversityEntity> universities) {
        List<UniversityEntity> unis =  universities.stream()
                .filter(u -> u.getCostOfAttendance() >= sort.getStartCost())
                .filter(u -> u.getCostOfAttendance() <= sort.getEndCost())
                .filter(u -> u.getAcceptanceRate() >= sort.getStartAccRate())
                .filter(u -> u.getAcceptanceRate() <= sort.getEndAccRate())
                .filter(u -> u.getGraduationRate() >= sort.getStartGradRate())
                .filter(u -> u.getGraduationRate() <= sort.getEndGradRate())
                .collect(Collectors.toList());
        sortBy(sort.getSortBy(), unis);
        return unis;
    }

//    Subject Sorting

    public List<Subject> getSubjectsByWeekDay(List<Subject> data, DayOfWeek day){
        return data.stream().filter(s ->
                s.getWeekMap().entrySet().stream().anyMatch(entry -> entry.getKey() == day)).collect(Collectors.toList());
    }

    public SubjectTime getSubjectsByWeekDays(Map<DayOfWeek, SubjectTime> days, DayOfWeek day){
        return days.get(day);

    }
//
//    public List<Subject> sortSubjectByHours(List<Subject> data){
//        List<Subject> collect = data.stream()
//                .sorted(s -> s.getWeekMap()
//                        .entrySet()
//                        .stream()
//                        .sorted(Map.Entry.comparingByValue(
//                                (e1, e2) -> Integer.compare(e1.getStartHour().getHour(), e2.getStartHour().getHour())
//                        )).collect(Collectors.toMap(Map.Entry::getKey, SubjectTime::getDate)))
//                .collect(Collectors.toList());
//        return collect;
//    }

}
