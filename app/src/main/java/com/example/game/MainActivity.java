package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //text views
    public TextView firstTV;
    public TextView secondTV;
    public TextView thirdTV;
    public TextView fourthTV;
    public TextView fifthTV;
    public TextView sixthTV;

    //Edit texts
    public EditText answerOneET;
    public EditText answerTwoET;
    public EditText answerThreeET;

    //image views
    public ImageView firstIV;
    public ImageView secondIV;
    public ImageView thirdIV;

    //all Buttons
    public Button firstBtn;
    public Button secondBtn;
    public Button thirdBtn;
    public Button resetBtn;

    //all the variables
    public int firstRandom = 0, secondRandom = 0;
    public int countCorrectAnswers = 0;
    public int sumRandoms = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //all the Text views
        firstTV = (TextView) findViewById(R.id.firstTV);
        secondTV = (TextView) findViewById(R.id.secondTV);
        thirdTV = (TextView) findViewById(R.id.thirdTV);
        fourthTV = (TextView) findViewById(R.id.fourthTV);
        fifthTV = (TextView) findViewById(R.id.fifthTV);
        sixthTV = (TextView) findViewById(R.id.sixthTV);

        //all the edit texts
        answerOneET = (EditText) findViewById(R.id.firstAnswerEt);
        answerTwoET = (EditText) findViewById(R.id.secondAnswerET);
        answerThreeET = (EditText) findViewById(R.id.thirdAnswerET);

        //all the image views
        firstIV = (ImageView) findViewById(R.id.firstResultIV);
        secondIV = (ImageView) findViewById(R.id.secondResultIV);
        thirdIV  = (ImageView) findViewById(R.id.thirdResultIV);

        //all the Buttons
        firstBtn = (Button) findViewById(R.id.checkAnswerOneBtn);
        secondBtn = (Button) findViewById(R.id.checkAnswerTwoBtn);
        thirdBtn = (Button) findViewById(R.id.checkAnswer3Btn);
        resetBtn = (Button) findViewById(R.id.resetBtn);

        firstRandom = showNumbers(firstTV);
        secondRandom = showNumbers(secondTV);


        secondBtn.setEnabled(false);
        thirdBtn.setEnabled(false);
    }

    public void clickedAnswerOne(View view)
    {
        int result = 0;
        result = takeAnswer(answerOneET);

        sumRandoms = firstRandom + secondRandom;

        if(result == sumRandoms)
        {
            firstIV.setImageResource(R.drawable.correct_answer);
            countCorrectAnswers++;
        }
        else
        {
            firstIV.setImageResource(R.drawable.incorrect_answer);
        }

        thirdTV.setText(String.valueOf(sumRandoms));
        firstRandom = showNumbers(fourthTV);

        firstBtn.setEnabled(false);
        secondBtn.setEnabled(true);
    }

    public void clickedAnswerTwo(View view)
    {
        int result = 0;

        result = takeAnswer(answerTwoET);



        if(result == sumRandoms + firstRandom)
        {
            secondIV.setImageResource(R.drawable.correct_answer);
            countCorrectAnswers++;
        }
        else
        {
            secondIV.setImageResource(R.drawable.incorrect_answer);
        }

        sumRandoms = sumRandoms + firstRandom;
        firstRandom = showNumbers(sixthTV);
        fifthTV.setText(String.valueOf(sumRandoms));

        thirdBtn.setEnabled(true);
        secondBtn.setEnabled(false);
    }

    public void clickedAnswerThree(View view)
    {
        int result = 0;
        
        result = takeAnswer(answerThreeET);

        if(result == sumRandoms + firstRandom)
        {
            thirdIV.setImageResource(R.drawable.correct_answer);
            countCorrectAnswers++;
        }
        else
        {
            thirdIV.setImageResource(R.drawable.incorrect_answer);
        }

        Toast.makeText(this, countCorrectAnswers + "/3" + ", " + (countCorrectAnswers * 100) / 3 + "%", Toast.LENGTH_SHORT).show();
        thirdBtn.setEnabled(false);

        countCorrectAnswers = 0;
    }

    public void resetClicled(View view)
    {
        thirdTV.setText(null);
        fourthTV.setText(null);
        fifthTV.setText(null);
        sixthTV.setText(null);

        firstBtn.setEnabled(true);
        secondBtn.setEnabled(false);
        thirdBtn.setEnabled(false);

        firstIV.setImageResource(0);
        secondIV.setImageResource(0);
        thirdIV.setImageResource(0);

        answerOneET.setText("");
        answerTwoET.setText("");
        answerThreeET.setText("");

        firstRandom = showNumbers(firstTV);
        secondRandom = showNumbers(secondTV);
    }

    private static int showNumbers(TextView txt)
    {
        Random rand = new Random();
        int randomNum1 = rand.nextInt(89) + 10;

        txt.setText(String.valueOf(randomNum1));
        return randomNum1;
    }

    private int takeAnswer(EditText answerOneET)
    {
        String strResult = answerOneET.getText().toString();
        int result = Integer.parseInt(strResult);

        return result;
    }
}