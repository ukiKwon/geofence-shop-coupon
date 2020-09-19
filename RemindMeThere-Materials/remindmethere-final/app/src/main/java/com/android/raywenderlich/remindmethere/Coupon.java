package com.android.raywenderlich.remindmethere;

import android.graphics.drawable.Drawable;
import android.location.Location;

public class Coupon {
    Drawable mimage;
    //String mCategory;
    String mStoreName;
    String mDiscountRate;
    Location mLocation;
    float mLeftTime;

    Coupon(Drawable image, String storename, String mDiscountRate, float mLeftTime) {
        mimage = image;
        mStoreName = storename;
        mDiscountRate = mDiscountRate;
        mLeftTime = mLeftTime;
    }
    public void setDiscountRate(String dicountrate) {
        mDiscountRate = dicountrate;
    }
    public void setLocation(Location location) {
        mLocation = location;
    }
    public void setLeftTime(float leftTime) {
        mLeftTime = leftTime;
    }
    public Drawable getImage() {return this.mimage;}
    public String getDiscountRate() {return this.mDiscountRate;}
    public String getName() {return this.mStoreName;}
    public Location getLocation() {return this.mLocation;}
    public float getLeftTime() {return this.mLeftTime;}
}
