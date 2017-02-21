package com.prolificinteractive.materialcalendarview.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.SubjectCalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Shows off the most basic usage
 */
public class BasicActivity extends AppCompatActivity implements OnDateSelectedListener, OnMonthChangedListener {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @Bind(R.id.calendarView)
    MaterialCalendarView widget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ButterKnife.bind(this);

        widget.setOnDateChangedListener(this);
        widget.setOnMonthChangedListener(this);

        widget.state().edit()
                .setMaximumDate(Calendar.getInstance())
                .commit();
        //Setup initial text

        Date currentDate = new Date();

        widget.setCurrentDate(currentDate);
        widget.setAllowClickDaysOutsideCurrentMonth(false);
        widget.setAllowClickDaysOutsideCurrentMonth(true);
        widget.setShowOtherDates(MaterialCalendarView.SHOW_OTHER_MONTHS);
        widget.setSelectedDate(currentDate);
        widget.setArrowColor(Color.parseColor("#1384c4"));
        widget.setSelectionColor(Color.parseColor("#05c475"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<SubjectCalendar> calendarDays = new ArrayList<>();

                SubjectCalendar subjectCalendar = new SubjectCalendar();
                CalendarDay day1 = CalendarDay.from(Calendar.getInstance());
                subjectCalendar.calendarDay = day1;
                subjectCalendar.done = true;
                subjectCalendar.right = true;
                subjectCalendar.subjectName = "数学";
                calendarDays.add(subjectCalendar);

                SubjectCalendar subjectCalendar1 = new SubjectCalendar();
                CalendarDay day2 = CalendarDay.from(2017, 1, 16);
                subjectCalendar1.calendarDay = day2;
                subjectCalendar1.subjectName = "阅读";
                calendarDays.add(subjectCalendar1);

                widget.showSubject(calendarDays);
            }
        }, 3000);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        //noinspection ConstantConditions

        getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
    }

    private String getSelectedDatesString() {
        CalendarDay date = widget.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }
}
