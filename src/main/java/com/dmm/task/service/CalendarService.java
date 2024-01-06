package com.dmm.task.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    public List<List<LocalDate>> generateCalendarData(int year, int month, String username) {
        List<List<LocalDate>> calendar = new ArrayList<>();

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate currentDate = firstDayOfMonth;

        while (currentDate.getMonthValue() == month) {
            List<LocalDate> weekData = new ArrayList<>();

            for (int i = 0; i < 7; i++) {
                weekData.add(currentDate);

                if (i < 6) {
                    currentDate = currentDate.plusDays(1);
                }
            }

            calendar.add(weekData);
        }

        return calendar;
    }

    private List<LocalDate> getDatesForPreviousMonth(int year, int month, int dayOfWeek) {
        List<LocalDate> dates = new ArrayList<>();

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate currentDate = firstDayOfMonth.minusDays(dayOfWeek);

        for (int i = 0; i < dayOfWeek; i++) {
            dates.add(currentDate.plusDays(i));
        }

        return dates;
    }
}