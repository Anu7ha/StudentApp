package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class six extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);
    }
    public void subsix(View view) {
        Button b = (Button) view;
        String op = b.getText().toString();
        if (op.equals("ssc")) {
            Intent intent = new Intent(this, ssc.class);
            startActivity(intent);
        }
        if (op.equals("wta")) {
            Intent intent = new Intent(this, wta.class);
            startActivity(intent);
        }
        if (op.equals("mad")) {
            Intent intent = new Intent(this, mad.class);
            startActivity(intent);
        }
        if (op.equals("cg")) {
            Intent intent = new Intent(this, cg.class);
            startActivity(intent);
        }
        if(op.equals("ATTENDANCE CALCULATOR")){
            Intent intent = new Intent(this, attendsix.class);
            startActivity(intent);
        }
    }

}