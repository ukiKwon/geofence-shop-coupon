package com.android.raywenderlich.remindmethere;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GeoCouponActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private CouponAdapter mAdapter;//private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    //원래는 Server로부터 상호 작용 필요
    private ArrayList < Coupon > mDataset = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_coupon);
        //gengerate coupon
        genSampleCoupon();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CouponAdapter(mDataset);
        mAdapter.setOnItemClickListener(new CouponAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(GeoCouponActivity.this, RunningUserActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }
    //generate Sample Coupon
    private void genSampleCoupon() {
        addItem(getDrawable(R.drawable.icon_store1), "Oho", "13%", 4);
        addItem(getDrawable(R.drawable.icon_store2), "Amuna", "15%", 3);
        addItem(getDrawable(R.drawable.icon_store3), "Teach", "10%", 6);
        addItem(getDrawable(R.drawable.icon_store4), "Me", "20%", 2);
    }
    public void addItem(Drawable icon, String storename, String discount, float lefttime) {
        Coupon item = new Coupon(icon, storename, discount, Float.valueOf(lefttime));
        mDataset.add(item);
    }
}
