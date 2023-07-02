package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class cgpa extends AppCompatActivity {

    public EditText[] etCredits = new EditText[8];
    public EditText[] etSGPA = new EditText[8];
    public Button btnCalculate;
    public TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);

        // Initialize EditText fields for each semester's credits and SGPA
        etCredits[0] = findViewById(R.id.c1);
        etSGPA[0] = findViewById(R.id.s1);
        // Repeat for other semesters
        etCredits[1] = findViewById(R.id.c2);
        etSGPA[1] = findViewById(R.id.s2);
        etCredits[2] = findViewById(R.id.c3);
        etSGPA[2] = findViewById(R.id.s3);
        etCredits[3] = findViewById(R.id.c4);
        etSGPA[3] = findViewById(R.id.s4);
        etCredits[4] = findViewById(R.id.c5);
        etSGPA[4] = findViewById(R.id.s5);
        etCredits[5] = findViewById(R.id.c6);
        etSGPA[5] = findViewById(R.id.s6);
        etCredits[6] = findViewById(R.id.c7);
        etSGPA[6] = findViewById(R.id.s7);
        etCredits[7] = findViewById(R.id.c8);
        etSGPA[7] = findViewById(R.id.s8);

        btnCalculate = findViewById(R.id.cal);
        tvResult = findViewById(R.id.t1);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCGPA();
            }
        });
    }

    public void calculateCGPA() {
        int[] credits = new int[8];
        float[] sgpa = new float[8];

        // Retrieve credits and SGPA for each semester from EditText fields
        for (int i = 0; i < 8; i++) {
            credits[i] = Integer.parseInt(etCredits[i].getText().toString());
            sgpa[i] = Float.parseFloat(etSGPA[i].getText().toString());
        }

        // Calculate CGPA
        float totalCredits = 0;
        float weightedSGPA = 0;

        for (int i = 0; i < 8; i++) {
            totalCredits += credits[i];
            weightedSGPA += credits[i] * sgpa[i];
        }

        float cgpa = weightedSGPA / totalCredits;

        // Display CGPA
        tvResult.setText("Your CGPA: " + cgpa);
    }
}
