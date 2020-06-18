package com.gj.quiz.quiztopia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz1 extends AppCompatActivity implements SensorEventListener {

    TextView mQuestionTextView;
    TextView mAnswerTextView;
    TextView mScoreTextView;
    //ProgressBar mProgressBar;
    TextView mTimeTextView;

    static MediaPlayer correctEffect;
    static MediaPlayer wrongEffect;
    static MediaPlayer endEffect;
    int mIndex;
    int mQuestion;
    int mAnswer;
    int mScore;
    int mTotal;

    private SensorManager sensorManager;
    private Sensor sensor;
    private long lastUpdateTime;
    private double lastX, lastY, lastZ;
    private static final int G_TIME_THRESHOLD = 500;
    private static final int G_MOVE_THRESHOLD = 5;
    private final float G_INCORRECT = 6;
    private final float G_CORRECT = -5;
    private CountDownTimer quizTimer;
    //private TextView tv;

    private List<QA> mQuestionSet;

    //final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionSet.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //load the question data
        mQuestionSet = new ArrayList<>();
        mQuestionSet.add(new QA (R.string.q1_1, R.string.a1_1));
        mQuestionSet.add(new QA (R.string.q1_2, R.string.a1_2));
        mQuestionSet.add(new QA (R.string.q1_3, R.string.a1_3));
        mQuestionSet.add(new QA (R.string.q1_4, R.string.a1_4));
        mQuestionSet.add(new QA (R.string.q1_5, R.string.a1_5));
        mQuestionSet.add(new QA (R.string.q1_6, R.string.a1_6));
        mQuestionSet.add(new QA (R.string.q1_7, R.string.a1_7));
        mQuestionSet.add(new QA (R.string.q1_8, R.string.a1_8));
        mQuestionSet.add(new QA (R.string.q1_9, R.string.a1_9));
        mQuestionSet.add(new QA (R.string.q1_10, R.string.a1_10));
        mQuestionSet.add(new QA (R.string.q1_11, R.string.a1_11));
        mQuestionSet.add(new QA (R.string.q1_12, R.string.a1_12));
        mQuestionSet.add(new QA (R.string.q1_13, R.string.a1_13));
        mQuestionSet.add(new QA (R.string.q1_14, R.string.a1_14));
        mQuestionSet.add(new QA (R.string.q1_15, R.string.a1_15));
        mQuestionSet.add(new QA (R.string.q1_16, R.string.a1_16));
        mQuestionSet.add(new QA (R.string.q1_17, R.string.a1_17));
        mQuestionSet.add(new QA (R.string.q1_18, R.string.a1_18));
        mQuestionSet.add(new QA (R.string.q1_19, R.string.a1_19));
        mQuestionSet.add(new QA (R.string.q1_20, R.string.a1_20));
        mQuestionSet.add(new QA (R.string.q1_21, R.string.a1_21));
        mQuestionSet.add(new QA (R.string.q1_22, R.string.a1_22));
        mQuestionSet.add(new QA (R.string.q1_23, R.string.a1_23));
        mQuestionSet.add(new QA (R.string.q1_24, R.string.a1_24));
        mQuestionSet.add(new QA (R.string.q1_25, R.string.a1_25));
        mQuestionSet.add(new QA (R.string.q1_26, R.string.a1_26));
        mQuestionSet.add(new QA (R.string.q1_27, R.string.a1_27));
        mQuestionSet.add(new QA (R.string.q1_28, R.string.a1_28));
        mQuestionSet.add(new QA (R.string.q1_29, R.string.a1_29));
        mQuestionSet.add(new QA (R.string.q1_30, R.string.a1_30));
        mQuestionSet.add(new QA (R.string.q1_31, R.string.a1_31));
        mQuestionSet.add(new QA (R.string.q1_32, R.string.a1_32));
        mQuestionSet.add(new QA (R.string.q1_33, R.string.a1_33));
        mQuestionSet.add(new QA (R.string.q1_34, R.string.a1_34));

        //shuffle the data
        Collections.shuffle(mQuestionSet);

        //set layout
        setContentView(R.layout.activity_quiz1);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener( this, sensor, SensorManager.SENSOR_DELAY_NORMAL );

        if(savedInstanceState != null) {
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
        } else {
            mScore = 0;
            mIndex = 0;
        }

        mQuestionTextView = (TextView) findViewById( R.id.q1_text_view );
        mAnswerTextView = (TextView) findViewById( R.id.a1_text_view );
        mScoreTextView = (TextView) findViewById( R.id.q1_score );
        mTimeTextView = (TextView) findViewById( R.id.q1_timer );
       //mProgressBar = (ProgressBar) findViewById( R.id.q1_progress_bar );
        QA questionData = mQuestionSet.get(mIndex);
        mQuestion = questionData.getQuestionID();
        mAnswer = questionData.getAnswerID();
        mQuestionTextView.setText(mQuestion);
        mAnswerTextView.setText(mAnswer);

        mScoreTextView.setText("Score " + mScore + "/" + mTotal);
        mTimeTextView.setText("1:00");

        quizTimer = new CountDownTimer(60000, 500) {

            public void onTick(long millisUntilFinished) {
                mTimeTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                mTimeTextView.setText("Finished!");
                endEffect.start();
                AlertDialog.Builder alert = new AlertDialog.Builder(Quiz1.this, R.style.AppCompatAlertDialogStyle);
                alert.setTitle( "Game Over" );
                alert.setMessage( "Correctly Answered " + mScore + " Of " + mTotal + " Questions!" );
                alert.setCancelable(false);
                alert.setPositiveButton( "Go back to the Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.show();
                sensorManager.unregisterListener(Quiz1.this);
                quizTimer.cancel();
            }
        };
        quizTimer.start();
        correctEffect = MediaPlayer.create( this, R.raw.correct_answer );
        wrongEffect = MediaPlayer.create( this, R.raw.wrong_answer );
        endEffect = MediaPlayer.create( this, R.raw.quiz_end );
    }

    private void updateQuestion() {

        mIndex = (++mIndex)%mQuestionSet.size();

        if(mIndex == 0) {
            endEffect.start();
            AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
            alert.setTitle( "Game Over" );
            alert.setMessage( "Correctly Answered " + mScore + " Of " + mTotal + " Questions!" );
            alert.setPositiveButton( "Go back to the Menu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    return;
                }
            });
            alert.show();
            sensorManager.unregisterListener(Quiz1.this);
            quizTimer.cancel();
        }

        QA questionData = mQuestionSet.get(mIndex);
        mQuestion = questionData.getQuestionID();
        mAnswer = questionData.getAnswerID();
        mQuestionTextView.setText(mQuestion);
        mAnswerTextView.setText(mAnswer);

        //mProgressBar.incrementProgressBy( PROGRESS_BAR_INCREMENT );
        mScoreTextView.setText( "Correct: " + mScore + "/" + mTotal);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // we only need z values of the accelerometer
        double curX = event.values[0];
        double curY = event.values[1];
        double curZ = event.values[2];

        // need to check the difference
        double diffX = Math.abs(lastX - curX);
        double diffY = Math.abs(lastY - curY);
        double diffZ = Math.abs(lastZ - curZ);

        long currentTime = System.currentTimeMillis();
        long timeDifference = (currentTime - lastUpdateTime);
        if (timeDifference > G_TIME_THRESHOLD && diffZ > G_MOVE_THRESHOLD) {
            lastUpdateTime = currentTime;

            if (curZ < G_CORRECT) { // Correct Answer
                correctEffect.start();
                mScore++;
                mTotal++;
                updateQuestion();
            } else if (curZ > G_INCORRECT) { // Wrong Answer
                wrongEffect.start();
                mTotal++;
                updateQuestion();
            }

            // set the last know values of x,y,z
            lastX = event.values[0];
            lastY = event.values[1];
            lastZ = event.values[2];
        }

    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    //save the current state
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);
    }

    @Override
    public void onBackPressed() {
        if (quizTimer != null) quizTimer.cancel();

        super.onBackPressed();
    }
}
