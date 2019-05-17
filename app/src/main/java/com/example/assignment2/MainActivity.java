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
    private ImageView bgImage, centerImage;
    private TextView question, answer;
    private ImageButton card1, card2, card3, card4, card5;
    private ImageButton card6, card7, card8, card9, card10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Identifying image views
        bgImage = findViewById(R.id.BgImg);
        centerImage = findViewById(R.id.CenterImg);
        //Identifying text view
        question = findViewById(R.id.questionView);
        answer = findViewById(R.id.answerView);
        //Identifying buttons
        card1 = findViewById(R.id.Card_1);
        card2 = findViewById(R.id.Card_2);
        card3 = findViewById(R.id.Card_3);
        card4 = findViewById(R.id.Card_4);
        card5 = findViewById(R.id.Card_5);
        card6 = findViewById(R.id.Card_6);
        card7 = findViewById(R.id.Card_7);
        card8 = findViewById(R.id.Card_8);
        card9 = findViewById(R.id.Card_9);
        card10 = findViewById(R.id.Card_10);

        questionCreator();


    }



    public int questionCreator(){
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
        String holder = Integer.toString(num1)+operator.get(operatorCode)+Integer.toString(num2);
        //Check the correct answer to the above question
        System.out.println(holder);
        if (operatorCode == 0){
            trueAnswer = num1 + num2;}
        else if (operatorCode == 1){
            trueAnswer = num1 - num2;}
        else if (operatorCode == 2){
            trueAnswer = num1 * num2;}
        else if (operatorCode == 3){
            trueAnswer = Math.round(num1 / num2);}
            else
            System.out.println("impossible question detected");
        System.out.println(trueAnswer);
        question.setText(holder);
        return trueAnswer;
    }


}
