package com.sample;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import java.io.FileInputStream;
import java.io.File;
import java.io.StringWriter;
import android.util.Log;
import android.net.Uri;
import org.apache.commons.io.IOUtils;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.app.Activity;
import android.os.AsyncTask;
import com.ibm.watson.developer_cloud.service.exception.ForbiddenException;
import com.ibm.watson.developer_cloud.visual_recognition.v3.*;
import com.ibm.watson.developer_cloud.service.security.*;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;


public class WatsonVR extends CordovaPlugin{
    //OkHttpClient client;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("classify")) {
            String versionDate = args.getString(0);
            String apiKey = args.getString(1);
            String file = args.getString(2);
            this.uploadImage(file, callbackContext);
            return true;
        } else if(action.equals("classifyImageFromUrl")) {
            String versionDate = args.getString(0);
            String apiKey = args.getString(1);
            this.classifyImageFromUrl(versionDate, apiKey, callbackContext);
        }
        return false;
    }

    private void classifyImageFromUrl(String version, String apikey, CallbackContext callbackContext) {
        try{
            //OkHttpClient client = getSingletonInstance();
            // Request request = new Request.Builder()
            //     .header("Authorization", "Basic YXBpa2V5OmZHVmZNVDRPd0xhUnJlbUg1bmhuak9LdUxWT19XY001cjZyY1BGUWY5dEJV")
            //     .url("https://gateway.watsonplatform.net/visual-recognition/api/v3/classify?url=https://watson-developer-cloud.github.io/doc-tutorial-downloads/visual-recognition/fruitbowl.jpg&version=2018-03-19")
            //     .build();
            // Response response = client.newCall(request).execute();
            //callbackContext.success("success!!: "+response.body().string());
        } catch(Exception e){
            //callbackContext.error(e.toString());
        }
        
    }

    public void uploadImage(String file, CallbackContext callbackContext) {

        try{
            Log.d("SURBHI","FILE TO UPLOAD PATHHHH: "+file);
            VisualRecognition service = new VisualRecognition("2016-05-20");
            IamOptions iamOptions = new IamOptions.Builder()
                .apiKey("fGVfMT4OwLaRremH5nhnjOKuLVO_WcM5r6rcPFQf9tBU")
                .build();
            service.setIamCredentials(iamOptions);
            
            ClassifyOptions options = new ClassifyOptions.Builder()
                .imagesFile(new File(file))
                .build();
            ClassifiedImages result = service.classify(options).execute();
            
            Log.d("SURBHI",result.toString());
            
            callbackContext.success(result.toString());
        } catch(Exception e) {
            Log.d("SURBHI", "error:"+e.toString());
            callbackContext.error(e.toString());
        }
       
    }
}
