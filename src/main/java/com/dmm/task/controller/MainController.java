package com.dmm.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/main")
    public String showCalendar(Model model) {
    	// 3. その月の1日のLocalDateを取得する
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        // 月の最終日を取得
        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        // カレンダーを生成
        List<List<LocalDate>> calendar = generateCalendar(firstDayOfMonth, lastDayOfMonth);

        model.addAttribute("main", calendar);

        return "main";
    }

    // 1.ListのListを用意
    private static List<List<LocalDate>> generateCalendar(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth) {
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
