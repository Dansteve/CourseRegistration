
package com.royalniks.courseregistration;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.AsyncTask;

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
/**
 * Created by Dansteve on 30/06/2017.
 */

public class EditFunction extends AsyncTask <String,Void,String>{
    Intent intent;
    Context context;
    ProgressDialog progressBar;
    AlertDialog alertDialog;
    EditFunction(Context ctx) {context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String edit_url = "http://mobile1234.000webhostapp.com/edit.php";
        if(type.equals("edit")){
            try {
                String f_name = params[1];
                String l_name = params[2];
                String matric = params[3];
                String faculty = params[4];
                String department = params[5];
                String courseA = params[6];
                String courseB = params[7];
                String courseC = params[8];
                String user_id = params[9];
                URL url = new URL(edit_url);
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
                        URLEncoder.encode("user_id","UTF-8")+"="+URLEncoder.encode(user_id,"UTF-8")+"&"+
                        URLEncoder.encode("courseC","UTF-8")+"="+URLEncoder.encode(courseC,"UTF-8");
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
        progressBar.setMessage("Saving Changed");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Edit Status");
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

    }

    @Override
    protected void onPostExecute(String  result) {

        progressBar.hide();
        alertDialog.setMessage("Successful");
        alertDialog.show();
        alertDialog.closeOptionsMenu();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
