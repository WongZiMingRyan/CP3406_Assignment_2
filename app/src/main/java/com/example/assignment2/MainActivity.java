package com.example.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Defining UI elements
    private ImageView bgImage, centerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Identifying image views, and buttons
        bgImage = findViewById(R.id.BgImg);
        centerImage = findViewById(R.id.CenterImg);
    }
}
