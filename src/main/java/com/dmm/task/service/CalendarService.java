package com.dmm.task.service;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
public class CalendarService {
    // カレンダーを生成するメソッド
    public List<List<LocalDate>> generateCalendar(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

    	// 週と日を格納する二次元のListを用意する
        List<List<LocalDate>> month = new ArrayList<>();

        // 1週間分のLocalDateを格納するListを用意する
        List<LocalDate> week = new ArrayList<>();

        // 日にちを格納する変数を用意する
        LocalDate day;

        // その月の1日を取得する
        day = LocalDate.now();  // 現在日時を取得
        day = LocalDate.of(day.getYear(), day.getMonthValue(), 1);  // 現在日時からその月の1日を取得

        // 前月分の LocalDateを求める
        DayOfWeek w = day.getDayOfWeek();  // 当該日の曜日を取得
        day = day.minusDays(w.getValue());  // 1日からマイナスして 1/28を取得

        // 1週目（1日ずつ増やして 週のリストに格納していく）
        for(int i = 1; i <= 7; i++) {
          week.add(day);  // 週のリストへ格納
          day = day.plusDays(1);  // 1日進める
        }    
        month.add(week);  // 1週目のリストを、月のリストへ格納する

        week = new ArrayList<>();  // 次週のリストを新しくつくる

        // 2週目（「7」から始めているのは2週目だから）
        for(int i = 7; i <= day.lengthOfMonth(); i++) {
          week.add(day);  // 週のリストへ格納

          w = day.getDayOfWeek();
          if(w == DayOfWeek.SATURDAY) {  // 土曜日だったら
            month.add(week);  // 当該週のリストを、月のリストへ格納する
            week = new ArrayList<>();  // 次週のリストを新しくつくる
          }

          day = day.plusDays(1);  // 1日進める
        }

        // 最終週の翌月分
        w = day.getDayOfWeek();
        int remainingDays = 7 - w.getValue(); // 最終週の残りの日数
        for(int i = 1; i <= remainingDays; i++) {
            week.add(day);
            day = day.plusDays(1);
        }
        month.add(week); // 最終週のリストを、月のリストへ格納する

        // 生成されたカレンダーを返す
        return month;
    }
    // 西暦と月を表示するメソッド
    public String displayYearAndMonth(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月");
        return date.format(formatter);
    }
}