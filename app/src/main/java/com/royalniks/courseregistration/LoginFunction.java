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

import static android.R.attr.data;
import static android.R.attr.positiveButtonText;
import static android.R.attr.type;
import static android.R.attr.visible;
import static android.support.v4.content.ContextCompat.startActivity;
import static com.royalniks.courseregistration.R.string.firstname;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dansteve on 30/06/2017.
 */

public class LoginFunction extends AsyncTask <String,Void,String>{
    Intent intent;
    Context context;
    AlertDialog alertDialog;
    ProgressDialog progressBar;
    LoginFunction (Context ctx) {
       context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://mobile1234.000webhostapp.com/login.php";
        if(type.equals("login")){
            try {
                String matric = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("matric","UTF-8")+"="+URLEncoder.encode(matric,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line ="";

                while ((line = bufferedReader.readLine())!= null){
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Logging In");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

    }

    @Override
    protected void onPostExecute(String  result) {
        progressBar.hide();
          try {
              if(result.equals("error")){
                      alertDialog.setMessage("Invaild Matric or Password");
                      alertDialog.show();
                      alertDialog.closeOptionsMenu();

              } if(result.equals("invalid")) {
                  alertDialog.setMessage("Please Input Matric and Password");
                  alertDialog.show();
                  alertDialog.closeOptionsMenu();
              }
            JSONObject temp = new JSONObject(result);
            if(temp.getString("status").equals("success")){
                Intent in = new Intent(context, TableActivity.class);

                //Create the bundle
                Bundle bundle = new Bundle();
                //Add your data from getFactualResults method to bundle

                //Add your data to bundle
                bundle.putString("dataValue",result);

                //Add the bundle to the intent
                in.putExtras(bundle);

                //Fire the second activity
                context.startActivity(in);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void done(){

    }
}
