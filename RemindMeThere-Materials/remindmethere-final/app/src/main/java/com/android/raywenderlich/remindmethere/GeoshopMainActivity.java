package com.android.raywenderlich.remindmethere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GeoshopMainActivity extends BaseActivity {
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geoshop_main);
        //click
        btn1 = (Button)findViewById(R.id.button_content1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeoshopMainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Log.i("GeoshopMainActivity", "onResume()");
    }
}
