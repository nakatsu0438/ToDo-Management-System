package com.dmm.task.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarService {
	
    // 1.ListのListを用意
    public List<List<LocalDate>> generateCalendar(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth) {
    	
        List<List<LocalDate>> calendar = new ArrayList<>();
        // 4. 曜日を表すDayOfWeekを取得し、上で取得したLocalDateに曜日の値（DayOfWeek#getValue)をマイナスして前月分のLocalDateを求める
        LocalDate currentDay = firstDayOfMonth.minusDays(firstDayOfMonth.getDayOfWeek().getValue() - 1);  // 日曜日を1列目に固定

        while (currentDay.isBefore(lastDayOfMonth) || currentDay.isEqual(lastDayOfMonth)) {
            // 2. 1週間分のLocalDateを格納するListを用意
            List<LocalDate> week = new ArrayList<>();
            // 5. 1日ずつ増やしてLocalDateを求めていき、2．で作成したListへ格納していき、1週間分詰めたら1．のリストへ格納する
            for (int i = 0; i < 7; i++) {
                week.add(currentDay);
                // 6. 2週目以降は単純に1日ずつ日を増やしながらLocalDateを求めてListへ格納していき、土曜日になったら1．のリストへ格納して新しいListを生成する（月末を求めるにはLocalDate#lengthOfMonth()を使う）
                currentDay = currentDay.plusDays(1);

                // 7. 最終週の翌月分をDayOfWeekの値を使って計算し、6．で生成したリストへ格納し、最後に1．で生成したリストへ格納する。また月の最終日が格納される行以降の行は表示しない
                if (currentDay.getDayOfMonth() == 1 && i > 0) {
                    break;
                }
            }
            calendar.add(week);
        }

        return calendar;
    }
}