package com.android.raywenderlich.remindmethere;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GeoshopMainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Log.i("GeoshopMainActivity", "onResume()");
    }
}
