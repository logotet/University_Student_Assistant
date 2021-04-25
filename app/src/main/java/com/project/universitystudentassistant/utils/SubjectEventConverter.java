package com.project.universitystudentassistant.utils;

import com.alamkanak.weekview.WeekViewEntity;
import com.alamkanak.weekview.WeekViewEvent;
import com.islandparadise14.mintable.model.ScheduleEntity;
import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.models.SubjectSchedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SubjectEventConverter {
    public static ArrayList<ScheduleEntity> toWeekEvent(List<SubjectSchedule> subjects) {
        ArrayList<ScheduleEntity> subjectEvents = new ArrayList<>();
        for (SubjectSchedule subject : subjects) {
            int uid = subject.getUid();
            String name = subject.getName();
            String location = subject.getLocation();

            String teacher = subject.getTeacher();
            int color = subject.getColor();
            ScheduleEntity event = new ScheduleEntity(
                    uid,
                    name,
                    location,
                    subject.getDayOfWeek().getValue(),
                    subject.getStartHour().format(DateTimeFormatter.ofPattern("HH:mm")),
                    subject.getEndHour().format(DateTimeFormatter.ofPattern("HH:mm")),
                    "#73fcae68", //backgroundColor (optional)
                    "#000000"
            );
            subjectEvents.add(event);
        }
        return subjectEvents;
    }

//    public static List<SubjectSchedule> toSubjects(List<WeekViewEntity.Event> events){
//        List<SubjectSchedule> data = new ArrayList<>();
//        for (WeekViewEntity event : events) {
//
//            SubjectSchedule subjectSchedule = new SubjectSchedule();
//            int id = Integer.parseInt(event.getId());
//            String name = event.getName();
//            String location = event.getLocation();
//            Calendar startTime = event.getStartTime();
//            DayOfWeek dayOfWeek = DayOfWeek.of(startTime.get(Calendar.DAY_OF_WEEK));
//            int hour = startTime.get(Calendar.HOUR_OF_DAY);
//            int min = startTime.get(Calendar.MINUTE);
//            LocalTime startHour = LocalTime.of(hour, min);
//            Calendar endTime = event.getStartTime();
//            int hour2 = endTime.get(Calendar.HOUR_OF_DAY);
//            int min2 = endTime.get(Calendar.MINUTE);
//            LocalTime endHour = LocalTime.of(hour2, min2);
//            String teacher = ((WeekEvent) event).getTeacher();
//            int color = event.getColor();
//            subjectSchedule.setName(name);
//            subjectSchedule.setLocation(location);
//            subjectSchedule.setDayOfWeek(dayOfWeek);
//            subjectSchedule.setStartHour(startHour);
//            subjectSchedule.setEndHour(endHour);
//            subjectSchedule.setTeacher(teacher);
//            subjectSchedule.setColor(color);
//            data.add(subjectSchedule);
//        }
//        return data;
//
//    }

    public List<SubjectSchedule> getSubjectsWithDates(SubjectSchedule subjectSchedule){
        Calendar calendar = Calendar.getInstance();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        int i = calendar.get(Calendar.WEEK_OF_YEAR);
        LocalDate date = LocalDate.now()
                .with(WeekFields.ISO.weekBasedYear(), 2021) // year
                .with(WeekFields.ISO.weekOfWeekBasedYear(), i) // week of year
                .with(WeekFields.ISO.dayOfWeek(), subjectSchedule.getDayOfWeek().getValue());

        List<SubjectSchedule> subjectSchedulesDates = new ArrayList<>();
        for (int j = i; j <52 ; j++) {

        }
        return subjectSchedulesDates;
    }
}

