package com.example.app;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void press(View view){
        Button b = (Button) view;
        String op = b.getText().toString();
        if(op.equals("five")) {
            Intent intent = new Intent(MainActivity.this, five.class);
            startActivity(intent);
        }
        if(op.equals("six")) {
            Intent intent = new Intent(MainActivity.this, six.class);
            startActivity(intent);
        }
        if(op.equals("CGPA")){
            Intent intent = new Intent(MainActivity.this, cgpa.class);
            startActivity(intent);
        }

    }
}
