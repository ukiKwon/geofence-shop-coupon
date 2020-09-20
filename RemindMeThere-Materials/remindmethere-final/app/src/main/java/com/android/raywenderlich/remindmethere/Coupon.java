package com.android.raywenderlich.remindmethere;

import android.graphics.drawable.Drawable;
import android.location.Location;

import org.json.JSONObject;

public class Coupon {
    Drawable mimage;
    //String mCategory;
    String mStoreName;
    String mDiscountRate;
    String mLocation;//Location mLocation;
    String mLeftTime;
    JSONObject mSendInfo;

    Coupon(Drawable image, String storename, String discountRate, String leftTime, String floor) {
        mimage = image;
        mStoreName = storename;
        mDiscountRate = discountRate;
        mLeftTime = leftTime;
        mLocation = floor;
    }
    Coupon(JSONObject mjson) {
        mSendInfo = mjson;
    }
    public void setDiscountRate(String dicountrate) {
        mDiscountRate = dicountrate;
    }
    public void setLocation(String location) {
        mLocation = location;
    }
    public void setLeftTime(String leftTime) {
        mLeftTime = leftTime;
    }
    public Drawable getImage() {return this.mimage;}
    public String getDiscountRate() {return this.mDiscountRate;}
    public String getName() {return this.mStoreName;}
    public String getLocation() {return this.mLocation;}
    public String getLeftTime() {return this.mLeftTime;}
    public JSONObject getmSendInfo() {return mSendInfo;}
}
