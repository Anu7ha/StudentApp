package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cg);
    }

    public void notescg(View view) {
        Button b = (Button) view;
        String op = b.getText().toString();
        if (op.equals("notes")) {
            Intent intent = new Intent(this, cgnotes.class);
            startActivity(intent);
        }
    }
}