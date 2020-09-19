package com.android.raywenderlich.remindmethere;

import android.animation.ValueAnimator;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class RunningUserActivity extends BaseActivity{
    LottieAnimationView mlottieview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findingshop);

        //lottie-view;running_man
        mlottieview = (LottieAnimationView) findViewById(R.id.lottie_view_run);
        setLottieView();
    }
    public void setLottieView() {
        mlottieview.cancelAnimation();
        //todo : 시간 타이머 완료되면 사람 뭔가 머리를 쥐며 쓰러지는 animation 혹은 종료를 보이는 Timer 표시
        //mlottieview.setAnimation(SERVER_STATE & CUSTOM_STATE? R.raw.tab : R.raw.loading);
        mlottieview.setAnimation("runman.json");
        mlottieview.playAnimation();
        mlottieview.setProgress(0.5f);

    }
}
