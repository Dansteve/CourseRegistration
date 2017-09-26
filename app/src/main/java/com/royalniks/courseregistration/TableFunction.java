package com.royalniks.courseregistration;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.content.Intent;

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
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import static android.R.attr.type;
import static android.support.v4.content.ContextCompat.startActivity;
import static com.royalniks.courseregistration.R.id.course1;
import static com.royalniks.courseregistration.R.id.course2;
import static com.royalniks.courseregistration.R.id.course3;
import static com.royalniks.courseregistration.R.string.firstname;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dansteve on 30/06/2017.
 */

public class TableFunction extends AsyncTask <String,Void,String> {
    Intent intent;
    Context context;
    AlertDialog alertDialog;
    TableFunction (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        if(type.equals("edit")){
                String f_name = params[1];
                String l_name = params[2];
                String matric = params[3];
                String faculty = params[4];
                String department = params[5];
                String courseA = params[6];
                String courseB = params[7];
                String courseC = params[8];
                String user_id = params[9];
            String result = "{\"status\":\"success\",\"user_id\":\"" +user_id+ "\",\"f_name\":\"" +f_name+ "\",\"l_name\":\"" +l_name+ "\",\"matric\":\"" +matric+ "\",\"faculty\":\"" +faculty+ "\",\"department\":\"" +department+ "\",\"course1\":\"" +courseA+ "\",\"course2\":\"" +courseB+ "\",\"course3\":\"" +courseC+ "\"}";
            return result;
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String  result) {
        try {
            JSONObject temp = new JSONObject(result);
            if (temp.getString("status").equals("success")) {
                Intent in = new Intent(context, DisplayData.class);

                //Create the bundle
                Bundle bundle = new Bundle();
                //Add your data from getFactualResults method to bundle

                //Add your data to bundle
                bundle.putString("dataValue", result);

                //Add the bundle to the intent
                in.putExtras(bundle);

                //Fire the second activity
                context.startActivity(in);

            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void done(){

    }
}
