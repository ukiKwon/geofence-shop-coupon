package com.android.raywenderlich.remindmethere;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    private LottieAnimationView animationView;
    private LottieDrawable lottieDrawable;
    private long start_time, wait_time;
    private final int INTRO_CYCLE_TIME = 4300;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        animationView = (LottieAnimationView) findViewById(R.id.lottie_view_gps);
        setConfig();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome = new Intent( IntroActivity.this, GeoshopMainActivity.class);
                startActivity(welcome);
            }
        }, INTRO_CYCLE_TIME);
    }
    public void setConfig() {
        animationView.cancelAnimation();
        animationView.playAnimation();
        animationView.setProgress(0.5f);
    }
    @Override
    public void onPause() {
        super.onPause();
        // Remove the activity when its off the screen
        finish();
    }
}
