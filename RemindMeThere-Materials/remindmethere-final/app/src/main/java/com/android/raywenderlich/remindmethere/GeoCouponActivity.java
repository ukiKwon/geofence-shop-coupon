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
        addItem(getDrawable(R.drawable.logo_nicy), "신발 매장", "17%", "10", "2F");
        addItem(getDrawable(R.drawable.logo_puma), "신발 매장", "15%", "14", "3F");
        addItem(getDrawable(R.drawable.logo_sinsaegae), "백화점 의류", "20%", "5", "4F");
        addItem(getDrawable(R.drawable.logo_adidas), "신발 매장", "20%", "4", "1F");
    }
    public void addItem(Drawable icon, String storename, String discount, String lefttime, String floor) {
        Coupon item = new Coupon(icon, storename, discount, lefttime ,floor);
        mDataset.add(item);
    }
}
