package com.example.fendi_pc.idleappexample;

import android.app.FragmentTransaction;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private long timestamp;
    private IdleTimeCountDownTimer idleTimer;

    //Idle time
    private long idleTime= 5000; // idle time in Millisecond
    private final long interval = 1000; // the count down interval in Millisecond
    private boolean gifFragmentIsCalled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //count down timer declaraction
        idleTimer = new IdleTimeCountDownTimer(idleTime, interval);

    }



    @Override
    public void onStop() {
        super.onStop();
        //stop timer when this activity is stop
        idleTimer.cancel();
    }

    @Override
    public void onUserInteraction(){
        super.onUserInteraction();

        Log.d("User Interaction:","Active");
        if(gifFragmentIsCalled){
            getSupportFragmentManager().beginTransaction().
                    remove(getSupportFragmentManager().findFragmentByTag("giffragment")).commit();
            gifFragmentIsCalled = false;
        }

        //Reset the timer on user interaction
        idleTimer.cancel();
        idleTimer.start();
    }

    // class for count down to countdown idle time
    public class IdleTimeCountDownTimer extends CountDownTimer {
        public IdleTimeCountDownTimer(long idleTime, long interval) {
            super(idleTime, interval);
        }

        @Override
        public void onFinish() {
            //When user interval reach the idleTime
            Log.d("User Interaction:","inActive");
            gifFragmentIsCalled = true;
            Fragment gifFragment = new GifFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, gifFragment,"giffragment")
                    .attach(gifFragment).commit();
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }
    }


}
