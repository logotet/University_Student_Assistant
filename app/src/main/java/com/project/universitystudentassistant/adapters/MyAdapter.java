package com.project.universitystudentassistant.adapters;

import android.graphics.Color;
import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEntity;
import com.alamkanak.weekview.jsr310.WeekViewSimpleAdapterJsr310;
import com.project.universitystudentassistant.models.SubjectSchedule;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyAdapter extends WeekViewSimpleAdapterJsr310<SubjectSchedule> {
    public MyAdapter() {
        super();
    }

    private Calendar getStartTime(SubjectSchedule subject) {
        LocalDateTime localDateTime = LocalDateTime.of(subject.getDate(), subject.getStartHour());
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        GregorianCalendar calendar = GregorianCalendar.from(zonedDateTime);
        return calendar;
    }

    private Calendar getEndTime(SubjectSchedule subject) {
        LocalDateTime localDateTime = LocalDateTime.of(subject.getDate(), subject.getEndHour());
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        GregorianCalendar calendar = GregorianCalendar.from(zonedDateTime);
        return calendar;
    }

    @NotNull
    @Override
    public WeekViewEntity onCreateEntity(SubjectSchedule item) {
        WeekViewEntity.Style style = new WeekViewEntity.Style.Builder()
                .setBackgroundColor(item.getColor())
                .setTextColor(Color.WHITE)
                .build();
        WeekViewEntity weekViewEntity = new WeekViewEntity.Event.Builder(item)
                .setId(item.getUid())
                .setTitle(item.getName())
                .setStartTime(getStartTime(item))
                .setEndTime(getEndTime(item))
                .setStyle(style)
                .build();
        return weekViewEntity;
    }

    @Override
    public void onEventClick(SubjectSchedule data) {
        super.onEventClick(data);
        Toast.makeText(getContext(), data.getName(), Toast.LENGTH_SHORT).show();
    }
}
