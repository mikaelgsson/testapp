package com.example.mikael.testapp;

import android.app.Application;
import android.util.Log;

import com.tealium.internal.tagbridge.RemoteCommand;
import com.tealium.library.Tealium;

public class mgApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        //mg: init tealiumSDK
        Tealium.Config tealConfig = Tealium.Config.create(this,
                "hm",
                "sandbox-mg",
                "dev");

        Tealium inst = Tealium.createInstance("INSTANCE", tealConfig);


        RemoteCommand adobe = new AdobeRemoteCommand();
        inst.addRemoteCommand(adobe);

        inst.addRemoteCommand(new RemoteCommand("test", "troubleshooting") {
            // onInvoke will be called when Tealium iQ triggers the command
            @Override
            protected void onInvoke(Response response) throws Exception {
                // log
                Log.d("test: ", "some random output");

            }
        });



    }




}

