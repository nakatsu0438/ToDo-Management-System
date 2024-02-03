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
	    List<List<LocalDate>> calendar = new ArrayList<>();

	    // 週のリストを生成し、曜日によってデータを格納
	    List<LocalDate> week = new ArrayList<>();

	    // 月の1日目の曜日を取得
	    DayOfWeek firstDayOfWeek = firstDayOfMonth.getDayOfWeek();

	    // 前月の最終日を取得
	    LocalDate lastDayOfPreviousMonth = firstDayOfMonth.minusDays(0);

	    // 初週の日付を前月の最終日から逆順で格納
	    for (int i = 1; i < firstDayOfWeek.getValue(); i++) {
	        week.add(lastDayOfPreviousMonth.minusDays(firstDayOfWeek.getValue() - i));
	    }
	    // 週の残りの日付を格納
	    for (int i = firstDayOfWeek.getValue(); i <= 7; i++) {
	        week.add(firstDayOfMonth);
	        firstDayOfMonth = firstDayOfMonth.plusDays(1);
	    }
	    calendar.add(week);

	    int dayCount = 1;
	    // 月の残りの日付を格納
	    for (int i = 0; i < 5; i++) {
	        week = new ArrayList<>();
	        for (int j = 0; j < 7; j++) {
	        	// 当月の日付を格納する
	            if (dayCount <= lastDayOfMonth.getDayOfMonth()) {
	                week.add(firstDayOfMonth.plusDays(dayCount - 1));
	                dayCount++;
	            } else {
	                // 翌月の日付をセット
	                firstDayOfMonth = firstDayOfMonth.plusMonths(1);
	                week.add(firstDayOfMonth);
	                dayCount = 2;  // 翌月から再スタート
	            }
	        }
	        // 生成されたカレンダーに週を追加
	        calendar.add(week);
	        // リスト内の全ての週から日付を抽出し、それらを1つのストリームに結合
	        boolean containsLastDayOfMonth = calendar.stream()
	                // ストリーム内の日付の中から、当月の最終日と同じ日付が存在するかどうかを確認
	                .flatMap(List::stream)
	                .anyMatch(date -> date.getDayOfMonth() == lastDayOfMonth.getDayOfMonth() && // 同じ日付かつ↓
	                                  date.getMonthValue() == lastDayOfMonth.getMonthValue()); // 同じ月かどうか
	        if (containsLastDayOfMonth) {
	            break;
	        }
	    }

	    return calendar;
	}

    // 西暦と月を表示するメソッド
    public String displayYearAndMonth(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月");
        return date.format(formatter);
    }
}

/*


*/