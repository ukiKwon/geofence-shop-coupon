package com.android.raywenderlich.remindmethere;

import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendCustomerInfoActivity extends BaseActivity{
    //aws-commu
    private final String mProtocol = "http://";
    private final String mServerPublicIp = "101.101.167.242";
    private final String mServerPort = "3000";
    private final int mServerCount = 3;
    private String mUrls[] = new String[mServerCount];
    private final String[] mDeliver = {"POST", "GET"};
    private final String[] ERROR_STATE = {"10001", "10002"};
    private final String[] mServerTargetDir = {"/index", "/main", "/getCustomInfo"};
    //mainactivity-variables
    private final String TAG = "SendCustomerInfoActivity";
    private final String DELIMETER = ":";
    private boolean SERVER_STATE = false;
    private boolean CUSTOM_STATE = false;
    //mainactivity-view
    Button mbtn;
    TextView tv_sendinfo_res;
    //sample input
    String uid = "463100";
    String cateStore = "강남점 신세계";
    String infoCoupon = "34% 신발 할인권";
    String infoDiscount = "21";
    //
    Coupon mCoupon;
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
        //view
        tv_sendinfo_res = (TextView)findViewById(R.id.tv_sendinfo_res);
        mbtn = (Button)findViewById(R.id.btn_sendinfo);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String[] urls) {
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("uid", uid);
                jsonObject.accumulate("cateStore", cateStore);
                jsonObject.accumulate("infoCoupon", infoCoupon);
                jsonObject.accumulate("infoDiscount", infoDiscount);

                HttpURLConnection con = null;
                BufferedReader reader = null;
                try {
                    //URL url = new URL(urls[0] + mServerTargetDir);
                    URL url = new URL(urls[0]);
                    //연결을 함
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod(mDeliver[0]);//POST방식으로 보냄
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                    con.connect();

                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();

                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close();//버퍼를 받아줌

                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();//버퍼를 닫아줌
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //status check
//            CUSTOM_STATE = checkException(result);
//            SERVER_STATE = (result != null);
//            //init-visited data setting
//            String[] res = null;
//            if (SERVER_STATE && CUSTOM_STATE) {
//                res = result.split(DELIMETER);
//                if (res.length >= 2) {
//                    System.out.println("id : " + res[0].substring(1));
//                    System.out.println("name : " + res[1].substring(0, res[1].length() - 1));
//                    result = res[1].substring(0, res[1].length() - 1);
//                }
//            }
            //If the server is on
            tv_sendinfo_res.setText(result);
        }
    }
    public boolean checkException(String _custom_name) {
        boolean flag = true;
        for (int i = 0; flag && i < ERROR_STATE.length; ++i )
            flag = (ERROR_STATE[i] == _custom_name);
        return !flag;
    }
}
