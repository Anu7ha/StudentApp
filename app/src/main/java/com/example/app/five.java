package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class five extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
    }
    public void subfive(View view) {
        Button b = (Button) view;
        String op = b.getText().toString();
        if (op.equals("dbms")) {
            Intent intent = new Intent(this, dbms.class);
            startActivity(intent);
        }
        if (op.equals("atc")) {
            Intent intent = new Intent(this, atc.class);
            startActivity(intent);
        }
        if (op.equals("cn")) {
            Intent intent = new Intent(this, cn.class);
            startActivity(intent);
        }
        if (op.equals("adp")) {
            Intent intent = new Intent(this, adp.class);
            startActivity(intent);
        }
        if(op.equals("Attendance Calculator")){
            Intent intent = new Intent(this, attendance.class);
            startActivity(intent);
        }

    }

}