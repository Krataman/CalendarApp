package com.android.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ODCActivity extends AppCompatActivity {
    private String passedInMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odcactivity);
        passedInMsg = getIntent().getStringExtra("msg");

        updateText();
    }
    public void updateText(){
        ((TextView) findViewById(R.id.testView)).setText(passedInMsg);
    }

    public void startMainActivity(View view) {
        // Vytvoření Intentu pro spuštění nové aktivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}