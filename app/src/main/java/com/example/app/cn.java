package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn);
    }
    public void notescn(View view) {
        Button b = (Button) view;
        String op = b.getText().toString();
        if (op.equals("Notes")) {
            Intent intent = new Intent(this, cnotes.class);
            startActivity(intent);
        }
    }
}