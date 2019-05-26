package com.example.assignment2;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    private ImageButton BtnBell;
    private ImageButton BtnCard1, BtnCard2, BtnCard3, BtnCard4, BtnCard5;
    private ImageButton BtnCard6, BtnCard7, BtnCard8, BtnCard9, BtnCard10;
    //Defining Values that need to be remembered
    private String ValQN = "", ValTrueANS = "", ValUserANS = "";
    private int ImgViewCenterNum = 0, BellFunc = 1, ValPauseCount = 0;
    //Defining audio files
    private MediaPlayer AudBell1, AudBell2, AudBell5, AudHit1, AudHit2, AudHit3, AudHit4;

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
        BtnBell = findViewById(R.id.BtnBell);
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
        //Identifying sound files
        AudBell1 = MediaPlayer.create(MainActivity.this, R.raw.aud_bell1);
        AudBell2 = MediaPlayer.create(MainActivity.this, R.raw.aud_bell2);
        AudBell5 = MediaPlayer.create(MainActivity.this, R.raw.aud_bell5);
        AudHit1 = MediaPlayer.create(MainActivity.this, R.raw.aud_boxerhit1);
        AudHit2 = MediaPlayer.create(MainActivity.this, R.raw.aud_boxerhit2);
        AudHit3 = MediaPlayer.create(MainActivity.this, R.raw.aud_boxerhit3);
        AudHit4 = MediaPlayer.create(MainActivity.this, R.raw.aud_boxerhit4);

        //Starts the app with the number buttons disabled
        BtnCard1.setEnabled(false);
        BtnCard2.setEnabled(false);
        BtnCard3.setEnabled(false);
        BtnCard4.setEnabled(false);
        BtnCard5.setEnabled(false);
        BtnCard6.setEnabled(false);
        BtnCard7.setEnabled(false);
        BtnCard8.setEnabled(false);
        BtnCard9.setEnabled(false);
        BtnCard10.setEnabled(false);

        //Give the number buttons function
        BtnCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ANSInput("1");}});
        BtnCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ANSInput("2");}});
        BtnCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ANSInput("3");}});
        BtnCard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ANSInput("4");}});
        BtnCard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ANSInput("5");}});
        BtnCard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ANSInput("6");}});
        BtnCard7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ANSInput("7");}});
        BtnCard8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ANSInput("8");}});
        BtnCard9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ANSInput("9");}});
        BtnCard10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ANSInput("0");}});

        //Give the Bell button multiple functions
        BtnBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Function to start the round
                if (BellFunc == 1) {
                    AudBell5.start();
                    StartRound();
                    BellFunc = 2;
                }
                //Function to pause a match
                else if (BellFunc == 2) {
                    AudBell1.start();

                    ValPauseCount += 1;
                    BellFunc = 3;

                }
                //Function to resume a match
                else if (BellFunc == 3) {
                    AudBell2.start();
                }
            }
        });



    }

    public void StartRound() {
        //Reset round stats
        ValPauseCount = 0;

        //Enables number buttons
        BtnCard1.setEnabled(true);
        BtnCard2.setEnabled(true);
        BtnCard3.setEnabled(true);
        BtnCard4.setEnabled(true);
        BtnCard5.setEnabled(true);
        BtnCard6.setEnabled(true);
        BtnCard7.setEnabled(true);
        BtnCard8.setEnabled(true);
        BtnCard9.setEnabled(true);
        BtnCard10.setEnabled(true);
        //
        EncryptionBuild();
        QNCreator();
        //Change the ANS field to blanks equal to answer length
        System.out.println(ValQN);
        System.out.println(ValTrueANS);
    }

    public void PauseRound() {

        //Disables number buttons
        BtnCard1.setEnabled(false);
        BtnCard2.setEnabled(false);
        BtnCard3.setEnabled(false);
        BtnCard4.setEnabled(false);
        BtnCard5.setEnabled(false);
        BtnCard6.setEnabled(false);
        BtnCard7.setEnabled(false);
        BtnCard8.setEnabled(false);
        BtnCard9.setEnabled(false);
        BtnCard10.setEnabled(false);
    }

    public void EncryptionBuild() {

    }

    public void QNCreator() {
        //Reset remembered Values
        ValUserANS = "";
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
        ValTrueANS = Integer.toString(trueAnswer);
        //Set the ANS TextView to blanks
        String blanks = "_";
        while (blanks.length() < ValTrueANS.length())
        {
            blanks = blanks + "_";
        }
        TxtViewANS.setText(blanks);
    }

    public void ANSInput(String input) {
        //Record the user input
        ValUserANS = ValUserANS + input;
        //Check the users answers thus far, character by character in a for loop
        int error = 0;
        for (int i =0; i <ValUserANS.length(); i++) {
            //If user answer is correct, ANS textview is updated with user input
            System.out.println(ValUserANS);
            System.out.println(ValTrueANS);
            if (ValUserANS.charAt(i) == ValTrueANS.charAt(i)) {
                String blanks = "", holder;
                while (ValUserANS.length()+blanks.length() < ValTrueANS.length())
                {
                    blanks = blanks + "_";
                }
                holder = ValUserANS + blanks;
                TxtViewANS.setText(holder);
            }
            //if an error is detected it is flagged
            else {error = 1;}
        }
        //Changes center image of a boxer being hit depending on answer
        if (error == 0 ) {
            if (ImgViewCenterNum == 11){
                ImgViewCenter.setImageResource(R.drawable.boxer_bluehit2);
                ImgViewCenterNum = 12;
                AudHit1.start();
            }
            else {
                ImgViewCenter.setImageResource(R.drawable.boxer_bluehit1);
                ImgViewCenterNum = 11;
                AudHit2.start();
            }
            if (ValUserANS.equals(ValTrueANS)){
                QNCreator();
            }

        }
        else {
            if (ImgViewCenterNum == 21){
                ImgViewCenter.setImageResource(R.drawable.boxer_redhit2);
                ImgViewCenterNum = 22;
                AudHit3.start();
            }
            else {ImgViewCenter.setImageResource(R.drawable.boxer_redhit1);
                ImgViewCenterNum = 21;
                AudHit4.start();
            }
            QNCreator();
        }

    }

    public void questionCoder(String holder, int seed) {

    }


}
