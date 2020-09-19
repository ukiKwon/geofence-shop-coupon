package com.android.raywenderlich.remindmethere;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class RunningUserActivity extends BaseActivity{
    LottieAnimationView mlottieview;
    TextView tv_timer;
    final int d_h = 0, d_m = 0, d_s = 0, d_ms = 0;//Todo : 가게 주인이 제안한 타이머가 넘겨옴, 현재는 default
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findingshop);
        //configure-view
        tv_timer = (TextView)findViewById(R.id.textview_run_lefttime);
        new CountDownTimer(240000, 1000) {

            public void onTick(long millisUntilFinished) {
                long result = millisUntilFinished / 1000;
                tv_timer.setText("앞으로 " + result + " 초");
//                long h = (millisUntilFinished/1000)/3600;
//                long m = (millisUntilFinished/1000) / 60;
//                long s = (millisUntilFinished/1000) % 60;
//                String result = String.format("%02d:%02d:%02d", h, m, s);
                //시간이 1분 남았을 경우, Burning 모드 발동
                if (result <= 60000) {
                    Toast.makeText(RunningUserActivity.this, "1분 남았습니다.", Toast.LENGTH_SHORT).show();
                }
            }
            public void onFinish() {
                tv_timer.setText("도착");
            }
        }.start();

        //lottie-view;running_man
        mlottieview = (LottieAnimationView) findViewById(R.id.lottie_view_run);
        setLottieView();
    }
    public void setLottieView() {
        mlottieview.cancelAnimation();
        //todo : 시간 타이머 완료되면 사람 뭔가 머리를 쥐며 쓰러지는 animation 혹은 종료를 보이는 Timer 표시
        //mlottieview.setAnimation(SERVER_STATE & CUSTOM_STATE? R.raw.tab : R.raw.loading);
        //mlottieview.setAnimation("runman.json");
        mlottieview.playAnimation();
        mlottieview.setProgress(0.5f);
        mlottieview.setSpeed(1.5f);
    }
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            finish();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 쿠폰을 포기합니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
