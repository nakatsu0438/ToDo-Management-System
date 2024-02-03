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
    public List<List<LocalDate>> generateCalendar(
    		LocalDate firstDayOfMonth,
    		LocalDate lastDayOfMonth,
    		@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date ) {

    	// 週と日を格納する二次元のListを用意する
        List<List<LocalDate>> month = new ArrayList<>();

        // 1週間分のLocalDateを格納するListを用意する
        List<LocalDate> week = new ArrayList<>();

        // 日にちを格納する変数を用意する
        LocalDate day;
        
        if(date == null) {  // 引数で渡ってきた dateが nullであれば、今月と判断する
          // その月の1日のLocalDateを取得する
          day = LocalDate.now();
          day = LocalDate.of(day.getYear(), day.getMonthValue(), 1);
        }else {
          // nullでなければ、前月 or 翌月（の1日）が渡ってきているので、そのまま使う
          day = date;
        }

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
        System.out.println("dayの値 : " + day);

        week = new ArrayList<>();  // 次週のリストを新しくつくる

        // 2週目（2週目から月末まで）
        int leftOfMonth = day.lengthOfMonth() - day.getDayOfMonth();    // ①1週目の当月分
        leftOfMonth = day.lengthOfMonth() - leftOfMonth;    // ②当月の全日数から①を引く
        leftOfMonth = 7 - leftOfMonth;    // ③int i = 7を考慮した追加日数

        for(int i = 7; i < day.lengthOfMonth() + leftOfMonth; i++) {    // day.lengthOfMonth() に③を加える
            w = day.getDayOfWeek();
            week.add(day);
            if(w == DayOfWeek.SATURDAY){
                month.add(week);   // 月に週を追加
                week = new ArrayList<>();  // 新しい週のListを作成
            }
            
            day = day.plusDays(1);
            System.out.println(day);    // [DEBUG] ここで当月の末日が出力されること
        }

        // 最終週の翌月分
        w = day.getDayOfWeek();
        int remainingDays = 7 - w.getValue(); // 最終週の残りの日数
        if(remainingDays == 0) {  // 0の場合はもう1週分★
        	remainingDays = 7;
        }
        for(int n = 1; n <= remainingDays; n++) {
            week.add(day);
            day = day.plusDays(1);
        }
        month.add(week);

        // 生成されたカレンダーを返す
        return month;
    }
    // 西暦と月を表示するメソッド
    public String displayYearAndMonth(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月");
        return date.format(formatter);
    }
}