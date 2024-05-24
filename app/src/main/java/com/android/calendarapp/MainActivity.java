package com.android.calendarapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

    private View colorBar; 
    private Button colorPickerButton;
    private int selectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeWidgets();
        selectedDate = LocalDate.now();
        setMonthAndYearView();
        createToolBar();

    }

    //region createToolbar
    public void createToolBar(){
        Toolbar toolBar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle(getString(R.string.main_page));
    }

    //region setMOnthAndYear
    /**
     * Updates the view to display the current month and year and populates the RecyclerView
     * with the days in the selected month.
     *
     * <p>This method sets the text of the month and year view using the selected date,
     * retrieves the days in the current month, and sets up the RecyclerView with a
     * CalendarAdapter to display these days in a grid layout.</p>
     *
     * <p>It performs the following steps:
     * <ul>
     *     <li>Sets the text of the month and year view to the formatted selected date.</li>
     *     <li>Retrieves a list of days in the current month based on the selected date.</li>
     *     <li>Creates a new CalendarAdapter with the list of days and the current context.</li>
     *     <li>Configures a GridLayoutManager with 7 columns for the RecyclerView.</li>
     *     <li>Sets the layout manager and adapter of the RecyclerView to display the days of the month.</li>
     * </ul>
     * </p>
     */
    private void setMonthAndYearView() {
        monthAndYear.setText(dateFormat(selectedDate, 1));
        ArrayList<String> daysInMonth = getDaysInCurrentMonth(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(calendarAdapter);

    }
    //endregion
    //region getDaysInCurrentMont
    /**
     * Generates a list of days for the current month, formatted for a calendar view.
     *
     * <p>This method calculates the days in the specified month and prepares them in a format
     * suitable for display in a 7x6 grid, typically used in calendar views. It includes empty
     * strings for days outside the current month to align the first day of the month with the
     * correct day of the week.</p>
     *
     * <p>It performs the following steps:
     * <ul>
     *     <li>Initializes a list to store the days of the month.</li>
     *     <li>Gets the number of days in the specified month.</li>
     *     <li>Determines the day of the week of the first day of the month.</li>
     *     <li>Iterates through 42 positions (to cover a 7x6 grid), adding either empty strings
     *         for days outside the month or the day numbers for days within the month.</li>
     * </ul>
     * </p>
     *
     * @param date The LocalDate object representing the date for which the days of the month are to be generated.
     * @return An ArrayList of strings representing the days in the current month formatted for a calendar view.
     */
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
    //endregion
    //region formatDate
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
    //endregion
    private void initializeWidgets() {
        recyclerView = findViewById(R.id.recyclerView);
        monthAndYear = findViewById(R.id.currentMonthAndYear);
    }
    //region month switcher
    public void previousMonth(View v){
        selectedDate = selectedDate.minusMonths(1);
        setMonthAndYearView();

    }
    public void nextMonth(View v){
        selectedDate = selectedDate.plusMonths(1);
        setMonthAndYearView();
    }
    //endregion
    @Override
    public void onItemClick(int position, String dayText) {
        if(!dayText.isEmpty()){

            int day = Integer.parseInt(dayText);

            LocalDate selectedDay = selectedDate.withDayOfMonth(day);

            String formattedDate = selectedDay.format(DateTimeFormatter.ofPattern("ddMMyyyy"));

            startODC(formattedDate);
        }
    }
    public void startODC(String dateText){
        Intent intent = new Intent(this, OnDayClickActivity.class);
        intent.putExtra("dayClicked", dateText);
        startActivity(intent);
    }
}