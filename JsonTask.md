c class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String[] urls) {
            try {
                //JSONObject�� ����� key value �������� ���� �������ش�.
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("bid", bankerid);
                HttpURLConnection con = null;
                BufferedReader reader = null;
                try {
                    //URL url = new URL(urls[0] + mServerTargetDir);
                    URL url = new URL(urls[0]);
                    //������ ��
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod(mDeliver[0]);//POST������� ����
                    con.setRequestProperty("Cache-Control", "no-cache");//ĳ�� ����
                    con.setRequestProperty("Content-Type", "application/json");//application JSON �������� ����
                    con.setRequestProperty("Accept", "text/html");//������ response �����͸� html�� ����
                    con.setDoOutput(true);//Outstream���� post �����͸� �Ѱ��ְڴٴ� �ǹ�
                    con.setDoInput(true);//Inputstream���� �����κ��� ������ �ްڴٴ� �ǹ�
                    con.connect();

                    //������ ���������ؼ� ��Ʈ�� ����
                    OutputStream outStream = con.getOutputStream();

                    //���۸� �����ϰ� ����
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close();//���۸� �޾���

                    //������ ���� �����͸� ����
                    InputStream stream = con.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    return buffer.toString();//������ ���� ���� ���� �������� �Ƹ� OK!!�� ���ð���
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
                            reader.close();//���۸� �ݾ���
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
            CUSTOM_STATE = checkException(result);
            SERVER_STATE = (result != null);
            //init-visited data setting
            String[] res = null;
            if (SERVER_STATE && CUSTOM_STATE) {
                res = result.split(DELIMETER);
                if (res.length >= 2) {
                    System.out.println("id : " + res[0].substring(1));
                    System.out.println("name : " + res[1].substring(0, res[1].length() - 1));
                    mVisitedData = new VisitedData(res[0], res[1]);
                    result = res[1].substring(0, res[1].length() - 1);
                }
            }
            //If the server is on
            setLottieView();
            setTextView(result);
        }
    }
    public void setLottieView() {
        mAnimationView.cancelAnimation();
        mAnimationView.setAnimation(SERVER_STATE & CUSTOM_STATE? R.raw.tab : R.raw.loading);
        mAnimationView.playAnimation();
    }
    public void setTextView(String _custom_name){
        System.out.println("_custom_name : " + _custom_name);
        mCustomData.setText(SERVER_STATE & CUSTOM_STATE? (_custom_name + getString(R.string.found_custom)) : getString(R.string.not_found_custom));
        mServerData.setText(SERVER_STATE & CUSTOM_STATE? getString(R.string.server_connected) : getString(R.string.server_not_connected));
    }
    public boolean checkException(String _custom_name) {
        boolean flag = true;
        for (int i = 0; flag && i < ERROR_STATE.length; ++i )
            flag = (ERROR_STATE[i] == _custom_name);
        return !flag;
    }
    public void cameraPermissionChecker() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.CAMERA)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
    public void voicePermissionChecker(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_VOICE : {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }case MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE : {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }case MY_PERMISSIONS_READ_EXTERNAL_STORAGE : {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
