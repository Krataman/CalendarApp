package com.android.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class OnDayClickActivity extends AppCompatActivity {
    private String passedInMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odcactivity);
        passedInMsg = getIntent().getStringExtra("msg");

        createToolBar();
    }
    public void createToolBar(){
        Toolbar toolBar = findViewById(R.id.ODCToolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Go Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void startMainActivity(View view) {
        // Vytvoření Intentu pro spuštění nové aktivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}