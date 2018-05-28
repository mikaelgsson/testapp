package com.example.mikael.testapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//mg: import tealiumSDK
import com.tealium.library.*;
import java.util.HashMap;

//mg: added import for adobeSDK
import com.adobe.mobile.*;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //mg: Initiated adobeSDK Context
        Config.setContext(this.getApplicationContext());



    }

    @Override
    protected void onResume() {
        super.onResume();

        //mg: adobeSDK Lifecycle Call
        Config.collectLifecycleData(this);
        //mg: added adobe lifecyclecall

        //mg: adobeSDK track page when the activity loads
        Analytics.trackState("testApp:view:mainActivity", null);

        //mg: tealiumSDK track page when activity loads
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("event_name", "testApp:view:testEvent");

        Tealium.getInstance("INSTANCE").trackView("screenName", data);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //mg: adobeSDK Lifecycle Pause
        Config.pauseCollectingLifecycleData();


    }


}
