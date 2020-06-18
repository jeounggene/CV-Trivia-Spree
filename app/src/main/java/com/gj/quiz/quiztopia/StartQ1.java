package com.gj.quiz.quiztopia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StartQ1 extends AppCompatActivity {

    private CountDownTimer quizTimer;
    TextView mTimeTextView;
    static MediaPlayer timerEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_start_q1 );
        mTimeTextView = (TextView) findViewById( R.id.start_q1_countdown);
        timerEffect = MediaPlayer.create( this, R.raw.countdown );

        quizTimer = new CountDownTimer(5300, 1000) {
            public void onTick(long millisUntilFinished) {
                mTimeTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                mTimeTextView.setText("0");
                Intent intent = new Intent(StartQ1.this, Quiz1.class);
                startActivity( intent );
                finish();
            }
        };
        quizTimer.start();
        timerEffect.start();
    }

    @Override
    public void onBackPressed() {
        if (timerEffect != null) timerEffect.stop();
        if (quizTimer != null) quizTimer.cancel();
        super.onBackPressed();
    }

}
