package com.gj.quiz.quiztopia;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz2 extends AppCompatActivity implements SensorEventListener {

    TextView mQuestionTextView;
    TextView mScoreTextView;
    //ProgressBar mProgressBar;
    TextView mTimeTextView;

    static MediaPlayer correctEffect;
    static MediaPlayer wrongEffect;
    static MediaPlayer endEffect;
    int mIndex;
    int mQuestion;
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
        mQuestionSet.add(new QA (R.string.q2_1, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_2, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_3, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_4, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_5, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_6, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_7, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_8, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_9, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_10, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_11, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_12, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_13, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_14, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_15, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_16, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_17, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_18, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_19, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_20, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_21, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_22, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_23, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_24, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_25, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_26, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_27, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_28, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_29, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_30, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_31, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_32, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_33, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_34, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_35, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_36, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_37, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_38, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_39, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_40, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_41, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_42, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_43, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_44, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_45, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_46, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_47, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_48, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_49, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_50, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_51, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_52, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_53, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_54, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_55, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_56, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_57, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_58, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_59, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_60, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_61, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_62, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_63, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_64, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_65, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_66, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_67, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_68, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_69, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_70, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_71, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_72, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_73, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_74, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_75, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_76, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_77, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_78, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_79, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_80, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_81, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_82, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_83, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_84, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_85, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_86, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_87, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_88, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_89, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_90, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_91, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_92, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_93, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_94, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_95, R.string.a2_all));
        mQuestionSet.add(new QA (R.string.q2_96, R.string.a2_all));

        //shuffle the data
        Collections.shuffle(mQuestionSet);

        setContentView(R.layout.activity_quiz2);

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

        mQuestionTextView = (TextView) findViewById( R.id.q2_text_view );
        mScoreTextView = (TextView) findViewById( R.id.q2_score );
        mTimeTextView = (TextView) findViewById( R.id.q2_timer );
        //mProgressBar = (ProgressBar) findViewById( R.id.q2_progress_bar );
        QA questionData = mQuestionSet.get(mIndex);
        mQuestion = questionData.getQuestionID();
        mQuestionTextView.setText(mQuestion);

        mScoreTextView.setText("Score " + mScore + "/" + mTotal);
        mTimeTextView.setText("1:00");

        quizTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimeTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                mTimeTextView.setText("Finished!");
                endEffect.start();
                AlertDialog.Builder alert = new AlertDialog.Builder(Quiz2.this, R.style.AppCompatAlertDialogStyle);
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
                sensorManager.unregisterListener(Quiz2.this);
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
            sensorManager.unregisterListener(Quiz2.this);
            quizTimer.cancel();
        }

        QA questionData = mQuestionSet.get(mIndex);
        mQuestion = questionData.getQuestionID();
        mQuestionTextView.setText(mQuestion);

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
