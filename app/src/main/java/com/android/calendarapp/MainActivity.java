package com.android.calendarapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    private View colorBar; // Reference na barevný pruh
    private Button colorPickerButton; // Tlačítko pro otevření color pickeru
    private int selectedColor; // Aktuální zvolená barva

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
        getSupportActionBar().setTitle("Main page");
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

            // Získání dne v měsíci z textu (dayText)
            int day = Integer.parseInt(dayText);

            // Vytvoření data z vybraného dne
            LocalDate selectedDay = selectedDate.withDayOfMonth(day);

            // Formátování data na ddMMyyyy
            String formattedDate = selectedDay.format(DateTimeFormatter.ofPattern("ddMMyyyy"));

            // Předání data do startODC
            startODC(formattedDate);
        }
    }
    public void startODC(String dateText){
        Intent intent = new Intent(this, OnDayClickActivity.class);
        intent.putExtra("dayClicked", dateText);
        startActivity(intent);
    }

    /**
    //region
    public void colorPicker(){
        colorBar = findViewById(R.id.colorBar);
        colorPickerButton = findViewById(R.id.colorPickerButtonutton);

        selectedColor = Color.RED; // Výchozí barva

        colorPickerButton.setOnClickListener(v -> {
            // Vytvoření nového dialogu AmbilWarna
            AmbilWarnaDialog colorPickerDialog = new AmbilWarnaDialog(this, selectedColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    // Uložení nové barvy a nastavení pruhu
                    selectedColor = color;
                    colorBar.setBackgroundColor(selectedColor);
                }

                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
                    // Zrušení dialogu
                }
            });

            // Zobrazení dialogu
            colorPickerDialog.show();
        });
    }
    //endregion
     */
}