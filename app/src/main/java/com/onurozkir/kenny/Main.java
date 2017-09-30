package com.onurozkir.kenny;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Main extends AppCompatActivity {

    TextView timer;
    TextView score;
    ImageView column, column2, column3, column4, column5, column6, column7, column8, column9;
    int number;
    CountDownTimer myTimer;
    AlertDialog.Builder alert;
    ImageView [] imageArray;
    Handler handler;
    Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        column = (ImageView) findViewById(R.id.column);
        column2 = (ImageView) findViewById(R.id.column2);
        column3 = (ImageView) findViewById(R.id.column3);
        column4 = (ImageView) findViewById(R.id.column4);
        column5 = (ImageView) findViewById(R.id.column5);
        column6 = (ImageView) findViewById(R.id.column6);
        column7 = (ImageView) findViewById(R.id.column7);
        column8 = (ImageView) findViewById(R.id.column8);
        column9 = (ImageView) findViewById(R.id.column9);
        imageArray = new ImageView[]{column, column2, column3, column4, column5, column6, column7, column8, column9};
        score = (TextView) findViewById(R.id.score);
        number = 0;
        score.setText("Puan : " + number);

    }

    public void startGame(View view)
    {
        myTimer = new CountDownTimer(30500,1000){
            public void onTick (long l){
                timer = (TextView) findViewById(R.id.timer);
                timer.setText("Süre : " + l / 1000);

            }
            public void onFinish(){
                score = (TextView) findViewById(R.id.score);
                timer = (TextView) findViewById(R.id.timer);
                timer.setText("Süre Doldu");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
            }
        }.start();
        hindImages();
    }

    public void stopGame(View view)
    {
        score = (TextView) findViewById(R.id.score);
        handler.removeCallbacks(runnable);
        myTimer.cancel();
        timer.setText("Zaman : 0");
        alert = new AlertDialog.Builder(this);
        alert.setTitle("Oyun Bitti");
        alert.setMessage("Oyun " + score);
        alert.show();
    }

    public void increaseScore(View view){
        score = (TextView) findViewById(R.id.score);
        number++;
        score.setText("Puan :" + number);
    }

    public void hindImages()
    {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9-0);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }
}