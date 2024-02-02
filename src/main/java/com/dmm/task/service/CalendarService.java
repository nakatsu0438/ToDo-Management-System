package com.dmm.task.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarService {

    // カレンダーを生成するメソッド
    public List<List<LocalDate>> generateCalendar(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth) {

        // カレンダーを表現するListのListを用意
        List<List<LocalDate>> calendar = new ArrayList<>();

        // 月の日数を取得
        int daysInMonth = lastDayOfMonth.lengthOfMonth();

        // 月の初日の曜日を取得
        DayOfWeek firstDayOfWeek = firstDayOfMonth.getDayOfWeek();

        // 前月の最終日から月の初日までの日付を追加
        for (int i = 0; i == firstDayOfWeek.getValue(); i++) {
            List<LocalDate> week = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                // 前月の日付を追加
                week.add(firstDayOfMonth.minusDays(firstDayOfWeek.getValue() - 1 - i + j));
            }
            // 前月の最終日から月の初日までの日付が追加された週をカレンダーに追加
            calendar.add(week);
        }

        // 月の初日から最終日までの日付を追加
        int dayCount = 1;
        for (int i = 0; i < 6; i++) {
            List<LocalDate> week = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                if (dayCount <= daysInMonth) {
                    week.add(firstDayOfMonth.plusDays(dayCount - 1));
                    dayCount++;
                } else {
                    // 翌月の日付をセット
                    firstDayOfMonth = firstDayOfMonth.plusMonths(1);
                    week.add(firstDayOfMonth);
                    dayCount = 2;  // 翌月から再スタート
                }
            }
            // 月の初日から最終日までの日付が追加された週をカレンダーに追加
	        calendar.add(week);
	        boolean containsLastDayOfMonth = calendar.stream()
	                .flatMap(List::stream)
	                .anyMatch(date -> date.getDayOfMonth() == lastDayOfMonth.getDayOfMonth());
	        if (containsLastDayOfMonth) {
	            break;
	        }

        }

        // 生成されたカレンダーを返す
        return calendar;
    }

    // 西暦と月を表示するメソッド
    public String displayYearAndMonth(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月");
        return date.format(formatter);
    }
}