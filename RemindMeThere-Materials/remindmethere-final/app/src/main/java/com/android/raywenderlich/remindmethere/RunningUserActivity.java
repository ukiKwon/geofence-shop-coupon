package com.android.raywenderlich.remindmethere;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class RunningUserActivity extends BaseActivity{
    LottieAnimationView mlottieview;
    TextView tv_timer;
    TextView tv_run_title;
    private DialogBottomUp customDialog = new DialogBottomUp();
    //todo : 가게 주인이 설정한 최대 할인율과 최소 할인율
    TextView tv_max_discount;//최대할인율
    TextView tv_min_discount;//최소할인율
    final float custom_max_discount = 17;
    final float custom_min_discount = 10;
    float discountRatio_unit = 0.01f;
    int coupon_msec = 10000;
    final long warnBoundary_sec = 5;
    //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findingshop);
        //configure-view
        tv_timer = (TextView)findViewById(R.id.textview_run_lefttime);
        tv_run_title = (TextView)findViewById(R.id.textview_run_title);
        tv_max_discount = (TextView)findViewById(R.id.textview_max_dicount);
        tv_min_discount = (TextView)findViewById(R.id.textview_min_dicount);
        tv_max_discount.setText(String.valueOf(custom_max_discount));
        tv_min_discount.setText(String.valueOf(custom_min_discount));
        //calculate discount ratio : 할인율 감소 비율 책정
        calculateDiscountRatio();
        //timer
        new CountDownTimer(coupon_msec, 1000) {
            public void onTick(long millisUntilFinished) {
                long result = millisUntilFinished / 1000;//left-time;남은시간
                float curMaxDiscount = Float.parseFloat(tv_max_discount.getText().toString());//max-discount;최대남은할인
                //
                tv_timer.setText("앞으로 " + result + " 초");
                tv_max_discount.setText(String.format("%.2f", (curMaxDiscount-discountRatio_unit)));
                System.out.println("> cur :" + (curMaxDiscount - discountRatio_unit));
                //discountRatio_unit
                //시간이 1분 남았을 경우, Burning 모드 발동
                if (result <= warnBoundary_sec) {//60000) {
                    Toast.makeText(RunningUserActivity.this, "1분 남았음", Toast.LENGTH_SHORT).show();
                    tv_max_discount.setTextColor(Color.parseColor("#dc3c40"));
                }
            }
            public void onFinish() {
                tv_timer.setText("");
                tv_run_title.setText("도착");
                customDialog.show(getSupportFragmentManager(), "custom_dialog");
            }
        }.start();
        //lottie-view;running_man
        mlottieview = (LottieAnimationView) findViewById(R.id.lottie_view_run);
        setLottieView();
    }
    //msec 당 감소되는 할인율 결정
    private void calculateDiscountRatio() {
        float diff_discount = (custom_max_discount-custom_min_discount);//7
        discountRatio_unit = diff_discount/(coupon_msec/1000);//0.007
        System.out.println("ratio per sec:" + discountRatio_unit);
    }
    public void setLottieView() {
        mlottieview.cancelAnimation();
        //todo : 시간 타이머 완료되면 사람 뭔가 머리를 쥐며 쓰러지는 animation 혹은 종료를 보이는 Timer 표시
        //mlottieview.setAnimation(SERVER_STATE & CUSTOM_STATE? R.raw.tab : R.raw.loading);
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
