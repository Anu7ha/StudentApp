package com.example.app;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class cgpa extends AppCompatActivity {

    public EditText[] etCredits = new EditText[8];
    public EditText[] etSGPA = new EditText[8];
    public Button btnCalculate;
    public Button btnClear;
    public TextView tvResult;
    private SharedPreferences sharedPreferences;//used to store the values
    private static final String PREF_NAME = "CGPAPrefs";
    //pref fine which is not visible but programmatically accessed,
    //it is some called the internal storage that every app has within it

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        //mode_private it specifies that it accessed only by the calling application

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
        btnClear = findViewById(R.id.clear);
        tvResult = findViewById(R.id.t1);

        // Restore previously entered values from SharedPreferences
        //the values are overwritten
        for (int i = 0; i < 8; i++) {
            String creditsValue = sharedPreferences.getString("credits_" + i, "");
            String sgpaValue = sharedPreferences.getString("sgpa_" + i, "");

            etCredits[i].setText(creditsValue);
            etSGPA[i].setText(sgpaValue);
        }

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCGPA();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });
    }

    public void calculateCGPA() {
        int[] credits = new int[8];
        float[] sgpa = new float[8];

        SharedPreferences.Editor editor = sharedPreferences.edit();//the data is immediately stored using sp.E, in key value pair only
                                                                    //one gone then editor.apply is used to apply all the changes to the disk

        // Retrieve credits and SGPA for each semester from EditText fields
        for (int i = 0; i < 8; i++) {
            String creditsStr = etCredits[i].getText().toString();
            String sgpaStr = etSGPA[i].getText().toString();

            // Check if the input fields are empty
            if (creditsStr.isEmpty() || sgpaStr.isEmpty()) {
                tvResult.setText("Please enter values for all semesters.");
                return;
            }

            try {
                credits[i] = Integer.parseInt(creditsStr);
                sgpa[i] = Float.parseFloat(sgpaStr);

                // Store the entered values in SharedPreferences
                editor.putString("credits_" + i, creditsStr);
                editor.putString("sgpa_" + i, sgpaStr);
            } catch (NumberFormatException e) {
                tvResult.setText("Invalid input. Please enter valid numbers.");
                return;
            }
        }

        editor.apply(); // Save the entered values in SharedPreferences into the disk

        // Calculate CGPA
        float totalCredits = 0;
        float weightedSGPA = 0;

        for (int i = 0; i < 8; i++) {
            totalCredits += credits[i];
            weightedSGPA += credits[i] * sgpa[i];
        }

        float cgpa = weightedSGPA / totalCredits;

        // Round CGPA to two decimal places
        cgpa = Math.round(cgpa * 100) / 100f;

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String formattedCGPA = decimalFormat.format(cgpa);

        // Display CGPA
        tvResult.setText("Your CGPA: " + formattedCGPA);
    }

    private void clearData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Clear all stored values
        editor.apply();

        for (int i = 0; i < 8; i++) {
            etCredits[i].setText(""); // Clear the EditText fields
            etSGPA[i].setText("");
        }

        tvResult.setText(""); // Clear the CGPA result TextView
    }
}
