package com.royalniks.courseregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Login extends AppCompatActivity {
    ProgressBar pnew;
    Button signin, signup;
    EditText matNum, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        matNum = (EditText) findViewById(R.id.txtMatNum);
        pass = (EditText) findViewById(R.id.txtpassword);

//        pnew = (ProgressBar) findViewById(R.id.pbHeaderProgress);
//
        signup = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
//
//
//        signin = (Button) findViewById(R.id.btnLogin);
//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, DisplayData.class);
//                startActivity(intent);
//            }
//        });

    }

    public void OnLogIn(View view) {
        String username = matNum.getText().toString();
        String password = pass.getText().toString();
        String type = "login";

//        pnew.setVisibility(View.VISIBLE);

        LoginFunction loginFunction = new LoginFunction(this);
        loginFunction.execute(type, username, password);

    }

    public void onBackPressed() {
        //do nothing
    }


}




