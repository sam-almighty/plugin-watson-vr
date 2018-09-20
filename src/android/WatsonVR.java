package com.sample;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import java.io.FileInputStream;
import java.io.File;
import android.util.Log;
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
// import okhttp3.OkHttpClient;
// import okhttp3.Request;
// import okhttp3.Response;
// import okhttp3.MultipartBody;
// import okhttp3.MultipartBody.Builder;
// import okhttp3.MediaType;
// import okhttp3.RequestBody;
// import okhttp3.*;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.app.Activity;
import android.os.AsyncTask;
import com.ibm.watson.developer_cloud.service.exception.ForbiddenException;
import com.ibm.watson.developer_cloud.visual_recognition.v3.*;
import com.ibm.watson.developer_cloud.service.security.*;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.*;

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

        // try {
        //     File file2 = new File("./dogs.jpg");
        //     final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/jpg");

        //     RequestBody req = new MultipartBody.Builder().setType(MultipartBody.FORM)
        //             .addFormDataPart("images_file","profile.jpg", RequestBody.create(MEDIA_TYPE_PNG, file2)).build();

        //     Request request = new Request.Builder()
            
        //             .header("Authorization", "Basic YXBpa2V5OmZHVmZNVDRPd0xhUnJlbUg1bmhuak9LdUxWT19XY001cjZyY1BGUWY5dEJV")
        //             .url("https://gateway.watsonplatform.net/visual-recognition/api/v3/classify?version=2018-03-19")
        //             .post(req)
        //             .build();

        //     OkHttpClient client = getSingletonInstance();
        //     Response response = client.newCall(request).execute();

        //     Log.d("watson", "uploadImage:"+response.body().string());
        //     callbackContext.success("success!!: "+response.body().string());


            

        // } catch (Exception e) {
        //     Log.d("watson", "error:"+e.toString());
        //     callbackContext.error(e.toString());
        // }
        try{
            Log.d("watson","FILE TO UPLOAD PATH: "+file);
            VisualRecognition service = new VisualRecognition("2016-05-20");
            IamOptions iamOptions = new IamOptions.Builder()
            .apiKey("fGVfMT4OwLaRremH5nhnjOKuLVO_WcM5r6rcPFQf9tBU")
            .build();
            service.setIamCredentials(iamOptions);
            String temp = file.replace("file:","");
            //file = "/storage/emulated/0/Android/data/com.sample/cache/1537445653241.jpg";
            ClassifyOptions options = new ClassifyOptions.Builder()
            .imagesFile(new File(temp))
            .build();
            ClassifiedImages result = service.classify(options).execute();
            Log.d("watson",result.toString());
            callbackContext.success(result.toString());
        } catch(Exception e) {
            Log.d("watson", "error:"+e.toString());
            callbackContext.error(e.toString());
        }
       
    }

    // private OkHttpClient getSingletonInstance(){
    //     if(client == null) 
    //         client = new OkHttpClient();
    //     return client;
    // }

    private void classify(String versionDate, String apiKey, CallbackContext callbackContext) {
       
        //URL is already present in SDK, no need to pass
        //String URL = "https://gateway.watsonplatform.net/visual-recognition/api";
        //VisualRecognition visualRecognition = new VisualRecognition(versionDate, apiKey);
        //InputStream imagesStream = null;
        //HttpClient httpClient = new DefaultHttpClient();
        //OkHttpClient client = new OkHttpClient();
        try {
            // imagesStream = new FileInputStream(image.getPath());

            // ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
            //         .imagesFile(imagesStream)
            //         .imagesFilename(image.getName())
            //         .parameters("{\"classifier_ids\": [\"food\"], \"owners\": [\"IBM\"]}")
            //         .build();
            
            //ClassifiedImages result = visualRecognition.classify(classifyOptions).execute();
            
            Log.d("watson","success");
            callbackContext.success("success!!");
        }  catch (Exception e) {
            Log.d("watson","error");
            callbackContext.error(e.toString());
        }
    }
}
