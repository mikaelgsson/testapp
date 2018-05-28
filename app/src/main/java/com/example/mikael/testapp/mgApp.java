package com.example.mikael.testapp;

import android.app.Application;

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
        // Currently optional - add a 6 character alphanumeric data source ID.
        // If you have not been given a data source ID, you can omit this. Note that this is a mock value
        //tealConfig.setDataSourceId("abc123");
        /* setForceOverrideLogLevel - optional - forces the log level to be set to one of "dev", "qa", "prod" or "silent".
         * Overrides remote publish setting. Remote publish settings used if omitted. */
        //tealConfig.setForceOverrideLogLevel("prod");
        Tealium.createInstance("INSTANCE", tealConfig);

    }



}

