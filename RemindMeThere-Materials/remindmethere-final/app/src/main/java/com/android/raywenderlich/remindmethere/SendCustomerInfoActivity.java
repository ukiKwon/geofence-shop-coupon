package com.android.raywenderlich.remindmethere;

import android.os.Bundle;
import android.os.AsyncTask;

public class SendCustomerInfoActivity extends BaseActivity{
    //aws-commu
    private final String mProtocol = "http://";
    private final String mServerPublicIp = "13.125.216.41";
    private final String mServerPort = "3000";
    private final int mServerCount = 3;
    private String mUrls[] = new String[mServerCount];
    private final String[] mDeliver = {"POST", "GET"};
    private final String[] mServerTargetDir = {"/index", "/main", "/getInfo"};
    //mainactivity-variables
    private final String TAG = "SendCustomerInfoActivity";
    private final String DELIMETER = ":";
    private boolean SERVER_STATE = false;
    private boolean CUSTOM_STATE = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_info);
        setConfig();
    }
    private void setConfig() {
            //aws-connection
            mUrls[0] = mProtocol + mServerPublicIp + ":" + mServerPort + mServerTargetDir[2];
            new JSONTask().execute(mUrls);//AsyncTask 시작시킴
    }

}
