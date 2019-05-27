package com.example.assignment2;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    //Defining UI elements
    private ProgressBar ProgBarBlue, ProgBarRed;
    private ImageView ImgViewBG, ImgViewCenter, ImgViewTBox;
    private TextView TxtViewQN, TxtViewANS, TxtViewTBoxLn1, TxtViewTBoxLn2;
    private ImageButton BtnBell;
    private ImageButton BtnCard1, BtnCard2, BtnCard3, BtnCard4, BtnCard5;
    private ImageButton BtnCard6, BtnCard7, BtnCard8, BtnCard9, BtnCard10;
    //Defining Values that need to be remembered
    private String ValQN = "", ValTrueANS = "", ValUserANS = "";
    private String EncKey = "", EncLn1 = "", EncLn2 = "";
    private int ImgViewCenterNum = 0, BellFunc = 1, DMGIncrement = -25;
    private int ValRoundCount = 0, ValPauseCount = 0, ValQNCount = 0, ValQNCorrectCount;
    //Defining audio files
    private MediaPlayer AudBell1, AudBell2, AudBell5, AudHit1, AudHit2, AudHit3, AudHit4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Identifying progress bars
        ProgBarBlue = findViewById(R.id.ProgBarBlue);
        ProgBarRed = findViewById(R.id.ProgBarRed);
        //Identifying image views
        ImgViewBG = findViewById(R.id.ImgViewBG);
        ImgViewCenter = findViewById(R.id.ImgViewCenter);
        ImgViewTBox = findViewById(R.id.ImgViewTBox);
        //Identifying text view
        TxtViewQN = findViewById(R.id.TxtViewQN);
        TxtViewANS = findViewById(R.id.TxtViewANS);
        TxtViewTBoxLn1 = findViewById(R.id.TxtViewTBoxLn1);
        TxtViewTBoxLn2 = findViewById(R.id.TxtViewTBoxLn2);

        //Setting progress bars to full
        ProgBarBlue.setProgress(100);
        ProgBarRed.setProgress(100);
        //Identifying sound files
        AudBell1 = MediaPlayer.create(MainActivity.this, R.raw.aud_bell1);
        AudBell2 = MediaPlayer.create(MainActivity.this, R.raw.aud_bell2);
        AudBell5 = MediaPlayer.create(MainActivity.this, R.raw.aud_bell5);
        AudHit1 = MediaPlayer.create(MainActivity.this, R.raw.aud_boxerhit1);
        AudHit2 = MediaPlayer.create(MainActivity.this, R.raw.aud_boxerhit2);
        AudHit3 = MediaPlayer.create(MainActivity.this, R.raw.aud_boxerhit3);
        AudHit4 = MediaPlayer.create(MainActivity.this, R.raw.aud_boxerhit4);
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

        //Create and show starting code
        EncryptionBuild();
        TxtViewTBoxLn1.setText(EncLn1);
        TxtViewTBoxLn2.setText(EncLn2);

        //Give the Bell button multiple functions
        BtnBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Function to start the round
                if (BellFunc == 1) {
                    //Play audio to ring bell 5 times and start method to start round
                    AudBell5.start();
                    RoundStart();
                    //Record that a round has started and bell now pauses instead of restarts
                    BellFunc = 2;
                }
                //Function to pause a match
                else if (BellFunc == 2) {
                    //Play audio to ring bell 1 time and start method that pauses game
                    AudBell1.start();
                    RoundPause();
                    ValPauseCount += 1;
                    //Sets bell function to resume the round
                    BellFunc = 3;

                }
                //Function to resume a match
                else if (BellFunc == 3) {
                    //Play audio to ring bell 2 times and start method that resumes game
                    AudBell2.start();
                    RoundResume();
                    //Sets bell function to pause the round
                    BellFunc = 2;
                }
            }
        });
    }

    public void RoundStart() {
        //Reset round stats
        ValPauseCount = 0;
        ProgBarBlue.setProgress(100);
        ProgBarRed.setProgress(100);
        //Reset boxer image
        ImgViewCenter.setImageResource(R.drawable.boxer_neutral);

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

        //Hide encryption key
        ImgViewTBox.setVisibility(View.INVISIBLE);
        TxtViewTBoxLn1.setText("");
        TxtViewTBoxLn2.setText("");

        //Distributes algebra and generates question
        if (ValRoundCount > 0) {EncryptionBuild();}
        QNCreator();
    }

    public void RoundPause() {
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
        //Show Encryption
        ImgViewTBox.setVisibility(View.VISIBLE);
        TxtViewTBoxLn1.setText(EncLn1);
        TxtViewTBoxLn2.setText(EncLn2);
    }

    public void RoundResume() {
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
        //Hide encryption
        ImgViewTBox.setVisibility(View.INVISIBLE);
        TxtViewTBoxLn1.setText("");
        TxtViewTBoxLn2.setText("");

    }

    public void RoundEnd() {
        //Play audio to ring bell 5 times
        AudBell5.start();
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
        //Set bell to start new round
        BellFunc = 1;
        //Show round stats
        ImgViewTBox.setVisibility(View.VISIBLE);
        String holder = "Pauses : " + ValPauseCount;
        TxtViewTBoxLn1.setText(holder);
        holder = "W/L : " + ValQNCorrectCount + "/" + (ValQNCount - ValQNCorrectCount);
        TxtViewTBoxLn2.setText(holder);
    }


    public void EncryptionBuild() {
        //Create a loop to generate a 10-digit number as the encryption key
        for (int i =0; i <10; i++){
            int num = ThreadLocalRandom.current().nextInt(0, 9+1);
            while (EncKey.contains(Integer.toString(num)))
                num = ThreadLocalRandom.current().nextInt(0, 9+1);
            EncKey = EncKey + Integer.toString(num);
            //E.g. a key of 1234... will mean A=1,B=2,C=3 etc.
        }
        EncLn1 = "A="+EncKey.charAt(0)+" B="+EncKey.charAt(1)+" C="+EncKey.charAt(2)+" D="+EncKey.charAt(3)+" E="+EncKey.charAt(4);
        EncLn2 = "F="+EncKey.charAt(5)+" G="+EncKey.charAt(6)+" H="+EncKey.charAt(7)+" I="+EncKey.charAt(8)+" J="+EncKey.charAt(9);
    }

    public void EncryptionParse() {
        System.out.println(EncKey);
        try {ValQN = ValQN.replace(Character.toString(EncKey.charAt(0)),"A");}
        catch (Exception e) {System.out.println("No 1's");}
        try {ValQN = ValQN.replace(Character.toString(EncKey.charAt(1)),"B");}
        catch (Exception e) {System.out.println("No 2's");}
        try {ValQN = ValQN.replace(Character.toString(EncKey.charAt(2)),"C");}
        catch (Exception e) {System.out.println("No 3's");}
        try {ValQN = ValQN.replace(Character.toString(EncKey.charAt(3)),"D");}
        catch (Exception e) {System.out.println("No 4's");}
        try {ValQN = ValQN.replace(Character.toString(EncKey.charAt(4)),"E");}
        catch (Exception e) {System.out.println("No 5's");}
        try {ValQN = ValQN.replace(Character.toString(EncKey.charAt(5)),"F");}
        catch (Exception e) {System.out.println("No 6's");}
        try {ValQN = ValQN.replace(Character.toString(EncKey.charAt(6)),"G");}
        catch (Exception e) {System.out.println("No 7's");}
        try {ValQN = ValQN.replace(Character.toString(EncKey.charAt(7)),"H");}
        catch (Exception e) {System.out.println("No 8's");}
        try {ValQN = ValQN.replace(Character.toString(EncKey.charAt(8)),"I");}
        catch (Exception e) {System.out.println("No 9's");}
        try {ValQN = ValQN.replace(Character.toString(EncKey.charAt(9)),"J");}
        catch (Exception e) {System.out.println("No 0's");}
        TxtViewQN.setText(ValQN);
    }

    public void QNCreator() {
        //Reset remembered Values and count a new question
        ValUserANS = "";
        ValQNCount += 1;
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
        }
        //Recording the question and answer
        ValQN = holder;
        ValTrueANS = Integer.toString(trueAnswer);
        //Scramble the question with the Encryption function
        System.out.println(ValQN);
        System.out.println(ValTrueANS);
        EncryptionParse();
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
        //Changes center image of a boxer being hit and updating Progress bar depending on answer
        if (error == 0 ) {
            ProgBarRed.incrementProgressBy(DMGIncrement);
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
            //If whole answer is correct, add to the correct answer count
            if (ValUserANS.equals(ValTrueANS)){
                ValQNCorrectCount += 1;
                //Check if match is won, if not, start a new question
                if (ProgBarRed.getProgress() == 0) {
                    RoundEnd();
                } else {
                    QNCreator();
                }
            }
        }
        else {
            ProgBarBlue.incrementProgressBy(DMGIncrement);
            if (ImgViewCenterNum == 21){
                ImgViewCenter.setImageResource(R.drawable.boxer_redhit2);
                ImgViewCenterNum = 22;
                AudHit3.start();
            }
            else {ImgViewCenter.setImageResource(R.drawable.boxer_redhit1);
                ImgViewCenterNum = 21;
                AudHit4.start();
            }
            //Check if match is lost, if not, start a new question
            if (ProgBarBlue.getProgress() == 0) {
                RoundEnd();
            } else {
                QNCreator();
            }
        }

    }
}
