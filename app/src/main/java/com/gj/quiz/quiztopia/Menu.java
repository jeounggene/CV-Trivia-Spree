package com.gj.quiz.quiztopia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    Button mQuiz1;
    Button mQuiz2;
    Button mQuizSolo;
    Button mIntro;
    Button mInst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu );

        mIntro = findViewById( R.id.intro );
        mInst = findViewById( R.id.inst );
        mQuiz1 = findViewById( R.id.quiz_dual_1 );
        mQuiz2 = findViewById( R.id.quiz_dual_2 );
        mQuizSolo = findViewById( R.id.quiz_solo );

        mIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( "About Us", "pressed!" );

                AlertDialog.Builder alert = new AlertDialog.Builder(Menu.this, R.style.AppCompatAlertDialogStyle);
                alert.setTitle( "About Us" );
                alert.setMessage( R.string.intros );
                alert.setCancelable(false);
                alert.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();

                /*
                LayoutInflater inflater = (LayoutInflater) Menu.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup1_layout,null);
                float density = Menu.this.getResources().getDisplayMetrics().density;
                final PopupWindow pw = new PopupWindow(layout, (int)density*500, (int)density*400, true);
                //Button to close the pop-up
                layout.findViewById(R.id.popup1_close).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        pw.dismiss();
                    }
                });

                //Set up touch closing outside of pop-up
                pw.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                pw.setTouchInterceptor(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            pw.dismiss();
                            return true;
                        }
                        return false;
                    }
                });
                pw.setOutsideTouchable(true);
                // display the pop-up in the center
                pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
                */
            }
        });

        mInst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( "About Us", "pressed!" );

                AlertDialog.Builder alert = new AlertDialog.Builder(Menu.this, R.style.AppCompatAlertDialogStyle);
                alert.setTitle( "How to Play" );
                alert.setMessage( R.string.insts );
                alert.setCancelable(false);
                alert.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();

/*
                LayoutInflater inflater = (LayoutInflater) Menu.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup2_layout,null);
                float density = Menu.this.getResources().getDisplayMetrics().density;
                final PopupWindow pw = new PopupWindow(layout, (int)density*640, (int)density*480, true);
                //Button to close the pop-up
                layout.findViewById(R.id.popup2_close).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        pw.dismiss();
                    }
                });

                //Set up touch closing outside of pop-up
                pw.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                pw.setTouchInterceptor(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            pw.dismiss();
                            return true;
                        }
                        return false;
                    }
                });
                pw.setOutsideTouchable(true);
                // display the pop-up in the center
                pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
*/
            }
        });

        mQuiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( "Quiz1", "pressed!" );
                Intent intent = new Intent (getApplicationContext(), StartQ1.class);
                startActivity( intent );
            }
        });

        mQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( "Quiz2", "pressed!" );
                Intent intent = new Intent (getApplicationContext(), StartQ2.class);
                startActivity( intent );
            }
        });

        mQuizSolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( "Quiz_Solo", "pressed!" );
                Intent intent = new Intent (getApplicationContext(), Quizs.class);
                startActivity( intent );
            }
        });

    }
}
