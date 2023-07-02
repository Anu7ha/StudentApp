package com.example.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class dbmsn extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2, button3;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbmsn);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        pdfView = findViewById(R.id.pdfView);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String fileUrl = "";
        switch (v.getId()) {
            case R.id.button1:
                fileUrl = "mod2.pdf";
                break;
            case R.id.button2:
                fileUrl = "model.pdf";
                break;
            case R.id.button3:
                fileUrl = "module5.pdf";
                break;
        }

        openPDF(fileUrl);
    }

    private void openPDF(String filename) {
        pdfView.fromAsset(filename)
                .load();
    }
}