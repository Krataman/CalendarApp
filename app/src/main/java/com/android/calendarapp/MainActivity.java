package com.android.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.calendarapp.calendarHandling.CalendarAdapter;
import com.android.calendarapp.calendarHandling.OnItemListener;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemListener{

    private TextView monthAndYear;
    private String msg;
    private RecyclerView recyclerView;
    private LocalDate selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeWidgets();
        selectedDate = LocalDate.now();
        setMonthAndYearView();

    }

    private void setMonthAndYearView() {
        monthAndYear.setText(dateFormat(selectedDate, 1));
        ArrayList<String> daysInMonth = getDaysInCurrentMonth(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(calendarAdapter);

    }

    private ArrayList<String> getDaysInCurrentMonth(LocalDate date) {
        ArrayList<String> daysInMonthList = new ArrayList<>();
        YearMonth ym = YearMonth.from(date);

        int daysInMonth = ym.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);

        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++){
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthList.add("");
            }else{
                daysInMonthList.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthList;
    }

    private String dateFormat(LocalDate date, int type){
        if(type == 1){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
            return date.format(formatter);
        }else if(type == 2){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            return date.format(formatter);
        }
        return null;
    }

    private void initializeWidgets() {
        recyclerView = findViewById(R.id.recyclerView);
        monthAndYear = findViewById(R.id.currentMonthAndYear);
    }

    public void previousMonth(View v){
        selectedDate = selectedDate.minusMonths(1);
        setMonthAndYearView();

    }
    public void nextMonth(View v){
        selectedDate = selectedDate.plusMonths(1);
        setMonthAndYearView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(!dayText.isEmpty()){
            Intent intent = new Intent(this, OnDayClickActivity.class);

            String str = dateFormat(selectedDate, 2);
            intent.putExtra("dayClicked", str);
            startActivity(intent);
        }
    }
}