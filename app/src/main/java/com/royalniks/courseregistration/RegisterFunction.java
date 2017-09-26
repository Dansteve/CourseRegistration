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
import static com.royalniks.courseregistration.R.string.firstname;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dansteve on 30/06/2017.
 */

public class RegisterFunction extends AsyncTask <String,Void,String> {
    Intent intent;
    Context context;
    AlertDialog alertDialog;
    ProgressDialog progressBar;
    RegisterFunction (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String register_url = "http://mobile1234.000webhostapp.com/register.php";
        if(type.equals("register")){
            try {
                String f_name = params[1];
                String l_name = params[2];
                String matric = params[3];
                String faculty = params[4];
                String department = params[5];
                String courseA = params[6];
                String courseB = params[7];
                String courseC = params[8];
                String password = params[9];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("f_name","UTF-8")+"="+URLEncoder.encode(f_name,"UTF-8")+"&"+
                        URLEncoder.encode("l_name","UTF-8")+"="+URLEncoder.encode(l_name,"UTF-8")+"&"+
                        URLEncoder.encode("matric","UTF-8")+"="+URLEncoder.encode(matric,"UTF-8")+"&"+
                        URLEncoder.encode("faculty","UTF-8")+"="+URLEncoder.encode(faculty,"UTF-8")+"&"+
                        URLEncoder.encode("department","UTF-8")+"="+URLEncoder.encode(department,"UTF-8")+"&"+
                        URLEncoder.encode("courseA","UTF-8")+"="+URLEncoder.encode(courseA,"UTF-8")+"&"+
                        URLEncoder.encode("courseB","UTF-8")+"="+URLEncoder.encode(courseB,"UTF-8")+"&"+
                        URLEncoder.encode("courseC","UTF-8")+"="+URLEncoder.encode(courseC,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
        progressBar.setMessage("Registering Data");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Register Status");

    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.hide();
            if(result.equals("error")){
                alertDialog.setMessage("Pls insert all fields");
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();

            }if(result.equals("success")){
                alertDialog.setMessage("Thanks for Registering kindly Login");
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent in = new Intent(context, Login.class);
                        context.startActivity(in);
                    }
                });
                alertDialog.show();
            }

      }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}