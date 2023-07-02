package com.example.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class attendance extends AppCompatActivity {

    private int[] attendedClasses = new int[4];
    private int[] totalClasses = new int[4];
    private TextView[] attendancePercentageTextView = new TextView[4];
    private EditText[] attendedClassesEditText = new EditText[4];
    private EditText[] totalClassesEditText = new EditText[4];
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "AttendancePrefs";
    private static final String[] KEY_ATTENDED_CLASSES = {
            "attendedClassesSubject1",
            "attendedClassesSubject2",
            "attendedClassesSubject3",
            "attendedClassesSubject4"
    };
    private static final String[] KEY_TOTAL_CLASSES = {
            "totalClassesSubject1",
            "totalClassesSubject2",
            "totalClassesSubject3",
            "totalClassesSubject4"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        attendancePercentageTextView[0] = findViewById(R.id.attendancePercentageTextView1);
        attendancePercentageTextView[1] = findViewById(R.id.attendancePercentageTextView2);
        attendancePercentageTextView[2] = findViewById(R.id.attendancePercentageTextView3);
        attendancePercentageTextView[3] = findViewById(R.id.attendancePercentageTextView4);
        attendedClassesEditText[0] = findViewById(R.id.attendedClassesEditText1);
        attendedClassesEditText[1] = findViewById(R.id.attendedClassesEditText2);
        attendedClassesEditText[2] = findViewById(R.id.attendedClassesEditText3);
        attendedClassesEditText[3] = findViewById(R.id.attendedClassesEditText4);
        totalClassesEditText[0] = findViewById(R.id.totalClassesEditText1);
        totalClassesEditText[1] = findViewById(R.id.totalClassesEditText2);
        totalClassesEditText[2] = findViewById(R.id.totalClassesEditText3);
        totalClassesEditText[3] = findViewById(R.id.totalClassesEditText4);

        Button[] updateButton = new Button[4];
        Button[] addButton = new Button[4];
        Button[] subtractButton = new Button[4];

        updateButton[0] = findViewById(R.id.updateButton1);
        updateButton[1] = findViewById(R.id.updateButton2);
        updateButton[2] = findViewById(R.id.updateButton3);
        updateButton[3] = findViewById(R.id.updateButton4);
        addButton[0] = findViewById(R.id.addButton1);
        addButton[1] = findViewById(R.id.addButton2);
        addButton[2] = findViewById(R.id.addButton3);
        addButton[3] = findViewById(R.id.addButton4);
        subtractButton[0] = findViewById(R.id.subtractButton1);
        subtractButton[1] = findViewById(R.id.subtractButton2);
        subtractButton[2] = findViewById(R.id.subtractButton3);
        subtractButton[3] = findViewById(R.id.subtractButton4);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        for (int i = 0; i < 4; i++) {
            final int subjectIndex = i;
            updateButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateAttendance(subjectIndex);
                }
            });

            addButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addAttendance(subjectIndex);
                }
            });

            subtractButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subtractAttendance(subjectIndex);
                }
            });

            // Retrieve the stored values
            attendedClasses[i] = sharedPreferences.getInt(KEY_ATTENDED_CLASSES[i], 0);
            totalClasses[i] = sharedPreferences.getInt(KEY_TOTAL_CLASSES[i], 0);

            // Update the UI with the stored values
            attendedClassesEditText[i].setText(String.valueOf(attendedClasses[i]));
            totalClassesEditText[i].setText(String.valueOf(totalClasses[i]));
        }

        updateAttendancePercentage();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save the values when the activity is paused
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < 4; i++) {
            editor.putInt(KEY_ATTENDED_CLASSES[i], attendedClasses[i]);
            editor.putInt(KEY_TOTAL_CLASSES[i], totalClasses[i]);
        }
        editor.apply();
    }

    private void updateAttendance(int subjectIndex) {
        String attendedClassesStr = attendedClassesEditText[subjectIndex].getText().toString();
        String totalClassesStr = totalClassesEditText[subjectIndex].getText().toString();

        if (!attendedClassesStr.isEmpty() && !totalClassesStr.isEmpty()) {
            attendedClasses[subjectIndex] = Integer.parseInt(attendedClassesStr);
            totalClasses[subjectIndex] = Integer.parseInt(totalClassesStr);
        }

        updateAttendancePercentage();
    }

    private void addAttendance(int subjectIndex) {
        attendedClasses[subjectIndex]++;
        totalClasses[subjectIndex]++;
        attendedClassesEditText[subjectIndex].setText(String.valueOf(attendedClasses[subjectIndex]));
        totalClassesEditText[subjectIndex].setText(String.valueOf(totalClasses[subjectIndex]));
        updateAttendancePercentage();
    }

    private void subtractAttendance(int subjectIndex) {
        totalClasses[subjectIndex]++;
        totalClassesEditText[subjectIndex].setText(String.valueOf(totalClasses[subjectIndex]));
        updateAttendancePercentage();
    }


    private void updateAttendancePercentage() {
        for (int i = 0; i < 4; i++) {
            double percentage = (double) attendedClasses[i] / totalClasses[i] * 100;
            attendancePercentageTextView[i].setText("Attendance: " + attendedClasses[i] + "/" + totalClasses[i]);

            if (percentage >= 85) {
                attendancePercentageTextView[i].append(" - You are good to go!");
            } else {
                attendancePercentageTextView[i].append(" - Attendance is below 85%");
            }
        }
    }
}
