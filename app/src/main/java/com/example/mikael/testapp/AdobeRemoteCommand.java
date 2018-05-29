package com.example.mikael.testapp;


import android.app.Application;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tealium.internal.tagbridge.RemoteCommand;
import org.json.JSONObject;

import java.util.Map;

import com.google.gson.Gson;
import com.adobe.mobile.*;


/** This should init the adobe Remote-command
 ** and provide mapping towards the trackState and trackAction calls.
 */

public final class AdobeRemoteCommand extends RemoteCommand {

    private String KEY_EVENT_NAME = "adobe_event_name"; //for setting action / state names
    private String KEY_EVENT_PARAMS = "adobe_event_params"; // main data JSON
    private String KEY_COMMAND_NAME = "command_name"; //the commandname to invoke


    public AdobeRemoteCommand () {
        super("analytics", "Analytics Remote Command");

    }


    @Override
    protected void onInvoke(RemoteCommand.Response response){
        String command = response.getRequestPayload().optString(KEY_COMMAND_NAME, null);

        String[] commandArray;
        // split the commands into an array
        commandArray = command.split(",");

        for (int j = 0, commandlen = commandArray.length; j < commandlen; j++) {
            command = commandArray[j];
            command = command.trim();
            switch (command) {
                case "action":
                    //grab parameters from tagbridge Response
                    String eventName = response.getRequestPayload().optString(KEY_EVENT_NAME, null);
                    JSONObject eventJSON = response.getRequestPayload().optJSONObject(KEY_EVENT_PARAMS);

                    //convert JSON to Map
                    JsonParser jsonParserA = new JsonParser();
                    JsonObject eventGSON = (JsonObject)jsonParserA.parse(eventJSON.toString());

                    Gson gsonA = new Gson();
                    Map actionMap = gsonA.fromJson(eventGSON, Map.class);

                    //Call Adobe Trackaction
                    if (eventName != null && eventJSON != null) {
                        Analytics.trackAction(eventName, actionMap);
                    }
                    break;
                case "state":
                    //grab parameters from tagbridge Response
                    String stateName = response.getRequestPayload().optString(KEY_EVENT_NAME, null);
                    JSONObject stateJSON = response.getRequestPayload().optJSONObject(KEY_EVENT_PARAMS);

                    //convert JSON to Map
                    JsonParser jsonParserB = new JsonParser();
                    JsonObject stateGSON = (JsonObject)jsonParserB.parse(stateJSON.toString());

                    Gson gsonB = new Gson();
                    Map stateMap = gsonB.fromJson(stateGSON, Map.class);;

                    //Call Adobe Trackaction
                    if (stateName != null && stateJSON != null) {
                        Analytics.trackState(stateName, stateMap);
                    }
                    break;
            }

        }
        response.send();
    }





}
