package com.example.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    //Defining UI elements
    private ImageView ImgViewBG, ImgViewCenter;
    private TextView TxtViewQN, TxtViewANS;
    private ImageButton BtnCard1, BtnCard2, BtnCard3, BtnCard4, BtnCard5;
    private ImageButton BtnCard6, BtnCard7, BtnCard8, BtnCard9, BtnCard10;
    //Defining Values that need to be remembered
    private String ValQN = "";
    private int ValANS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Identifying image views
        ImgViewBG = findViewById(R.id.ImgViewBG);
        ImgViewCenter = findViewById(R.id.ImgViewCenter);
        //Identifying text view
        TxtViewQN = findViewById(R.id.TxtViewQN);
        TxtViewANS = findViewById(R.id.TxtViewANS);
        //Identifying buttons
        BtnCard1 = findViewById(R.id.Card_1);
        BtnCard2 = findViewById(R.id.Card_2);
        BtnCard3 = findViewById(R.id.Card_3);
        BtnCard4 = findViewById(R.id.Card_4);
        BtnCard5 = findViewById(R.id.Card_5);
        BtnCard6 = findViewById(R.id.Card_6);
        BtnCard7 = findViewById(R.id.Card_7);
        BtnCard8 = findViewById(R.id.Card_8);
        BtnCard9 = findViewById(R.id.Card_9);
        BtnCard10 = findViewById(R.id.Card_10);

        //TODO: start game > generate/show encryption > create/encrypt QN > accept/check ANS

        StartRound();


    }

    public void StartRound() {
        QNCreator();
    }

    public void QNCreator() {
        //Create a list of the operators available
        List<String> operator = new ArrayList<>();
        operator.add(0, "+");
        operator.add(1, "-");
        operator.add(2, "x");
        operator.add(3, "/");
        //Randomly select a 2 digit number, an operator from above table, and a 1 digit number
        int num1 = ThreadLocalRandom.current().nextInt(10, 99 + 1);
        int num2 = ThreadLocalRandom.current().nextInt(1, 9 + 1);
        int operatorCode = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        int trueAnswer = 0;
        //Assemble the above into a string to display
        String holder = Integer.toString(num1) + operator.get(operatorCode) + Integer.toString(num2);
        //Check the TRUE answer to the above generated question
        System.out.println(holder);
        if (operatorCode == 0) {
            trueAnswer = num1 + num2;
        } else if (operatorCode == 1) {
            trueAnswer = num1 - num2;
        } else if (operatorCode == 2) {
            trueAnswer = num1 * num2;
        } else if (operatorCode == 3) {
            trueAnswer = Math.round(num1 / num2);
        } else {
            System.out.println("impossible TxtViewQN detected");
        }
        //Scramble the question with the Encryption function
        TxtViewQN.setText(holder);
        //Recording the question and answer
        ValQN = holder;
        ValANS = trueAnswer;
    }

    public void questionCoder(String holder, int seed) {

    }


}
