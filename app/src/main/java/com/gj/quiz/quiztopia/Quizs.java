package com.gj.quiz.quiztopia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Quizs extends AppCompatActivity {

    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    TextView mScoreTextView;
    TextView mTimeTextView;
    //ProgressBar mProgressBar;
    static MediaPlayer correctEffect;
    static MediaPlayer wrongEffect;
    static MediaPlayer endEffect;
    int mIndex;
    int mQuestion;
    boolean mAnswer;
    int mScore;
    int mTotal;

    private CountDownTimer quizTimer;
    private List<QASolo> mQuestionSet;

    //final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionSet.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load the question data
        mQuestionSet = new ArrayList<>();
        mQuestionSet.add(new QASolo (R.string.q3_1, true));
        mQuestionSet.add(new QASolo (R.string.q3_2, true));
        mQuestionSet.add(new QASolo (R.string.q3_3, true));
        mQuestionSet.add(new QASolo (R.string.q3_4, true));
        mQuestionSet.add(new QASolo (R.string.q3_5, true));
        mQuestionSet.add(new QASolo (R.string.q3_6, true));
        mQuestionSet.add(new QASolo (R.string.q3_7, true));
        mQuestionSet.add(new QASolo (R.string.q3_8, true));
        mQuestionSet.add(new QASolo (R.string.q3_9, true));
        mQuestionSet.add(new QASolo (R.string.q3_10, false));
        mQuestionSet.add(new QASolo (R.string.q3_11, false));
        mQuestionSet.add(new QASolo (R.string.q3_12, false));
        mQuestionSet.add(new QASolo (R.string.q3_13, false));
        mQuestionSet.add(new QASolo (R.string.q3_14, false));
        mQuestionSet.add(new QASolo (R.string.q3_15, false));
        mQuestionSet.add(new QASolo (R.string.q3_16, false));
        mQuestionSet.add(new QASolo (R.string.q3_17, false));
        mQuestionSet.add(new QASolo (R.string.q3_18, false));
        mQuestionSet.add(new QASolo (R.string.q3_19, false));
        mQuestionSet.add(new QASolo (R.string.q3_20, true));
        mQuestionSet.add(new QASolo (R.string.q3_21, false));
        mQuestionSet.add(new QASolo (R.string.q3_22, true));
        mQuestionSet.add(new QASolo (R.string.q3_23, false));
        mQuestionSet.add(new QASolo (R.string.q3_24, false));
        mQuestionSet.add(new QASolo (R.string.q3_25, true));
        mQuestionSet.add(new QASolo (R.string.q3_26, false));
        mQuestionSet.add(new QASolo (R.string.q3_27, false));
        mQuestionSet.add(new QASolo (R.string.q3_28, true));
        mQuestionSet.add(new QASolo (R.string.q3_29, true));
        mQuestionSet.add(new QASolo (R.string.q3_30, true));
        mQuestionSet.add(new QASolo (R.string.q3_31, false));
        mQuestionSet.add(new QASolo (R.string.q3_32, false));

        //shuffle the data
        Collections.shuffle(mQuestionSet);

        //set layout
        setContentView(R.layout.activity_quizs);

        if(savedInstanceState != null) {
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
        } else {
            mScore = 0;
            mIndex = 0;
        }

        mTrueButton = (Button) findViewById( R.id.true_button );
        mFalseButton = (Button) findViewById( R.id.false_button );
        mQuestionTextView = (TextView) findViewById( R.id.qs_text_view );
        mScoreTextView = (TextView) findViewById( R.id.qs_score );
        mTimeTextView = (TextView) findViewById( R.id.qs_timer );
        //mProgressBar = (ProgressBar) findViewById( R.id.qs_progress_bar );
        QASolo questionData = mQuestionSet.get(mIndex);
        mQuestion = questionData.getQuestionID();
        mAnswer = questionData.isAnswer();
        mQuestionTextView.setText(mQuestion);
        mScoreTextView.setText("Score " + mScore + "/" + mTotal);

        correctEffect = MediaPlayer.create( this, R.raw.correct_answer );
        wrongEffect = MediaPlayer.create( this, R.raw.wrong_answer );
        endEffect = MediaPlayer.create( this, R.raw.quiz_end );

        quizTimer = new CountDownTimer(5999, 500) {
            public void onTick(long millisUntilFinished) {
                mTimeTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                mTimeTextView.setText("0");
                mTotal++;
                wrongEffect.start();
                updateQuestion();
            }
        };
        quizTimer.start();

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateQuestion();
            }
        });
        mFalseButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestion();
            }
        });

    }

    private void updateQuestion() {

        mIndex = (++mIndex)%mQuestionSet.size();

        if(mIndex == 0 || mTotal==10) {
            //reached the end
            endEffect.start();
            AlertDialog.Builder alert = new AlertDialog.Builder(Quizs.this, R.style.AppCompatAlertDialogStyle);
            alert.setTitle( "Game Over" );
            alert.setMessage( "Correctly Answered " + mScore + " Of " + mTotal + " Questions!" );
            alert.setCancelable(false);
            alert.setPositiveButton( "Go back to the Menu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            quizTimer.cancel();
            alert.show();
        } else {
            quizTimer.start();
        }

        QASolo questionData = mQuestionSet.get(mIndex);
        mQuestion = questionData.getQuestionID();
        mAnswer = questionData.isAnswer();
        mQuestionTextView.setText(mQuestion);
        //mProgressBar.incrementProgressBy( PROGRESS_BAR_INCREMENT );
        mScoreTextView.setText("Score " + mScore + "/" + mTotal);
    }

    private void checkAnswer (boolean userSelection) {

        QASolo questionData = mQuestionSet.get(mIndex);
        boolean correctAnswer = questionData.isAnswer();

        if(correctAnswer == userSelection) {
            correctEffect.start();
            mTotal++;
            mScore++;
        }
        else {
            wrongEffect.start();
            mTotal++;
        }
    }

    @Override
    //save the current state
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);
    }

    @Override
    protected void onResume() {
        if (quizTimer != null) quizTimer.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (quizTimer != null) quizTimer.cancel();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (quizTimer != null) quizTimer.cancel();
        super.onBackPressed();
    }
}
