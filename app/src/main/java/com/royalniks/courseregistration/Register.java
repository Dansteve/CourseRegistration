package com.royalniks.courseregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class Register extends AppCompatActivity {
EditText fname, lname,mat_no,password, dept, faculty,course1,course2,course3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        mat_no=(EditText)findViewById(R.id.matric);
        password=(EditText)findViewById(R.id.password);
        dept=(EditText)findViewById(R.id.department);
        faculty=(EditText)findViewById(R.id.faculty);
        course1=(EditText)findViewById(R.id.course1);
        course2=(EditText)findViewById(R.id.course2);
        course3=(EditText)findViewById(R.id.course3);
        //Button signin =(Button)findViewById(R.id.)
    }

    public void onLoginClick(View view){
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }
    public  void onRegisterClick(View view) {
        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String matric = mat_no.getText().toString();
        String pass = password.getText().toString();
        String department = dept.getText().toString();
        String fac = faculty.getText().toString();
        String courseA = course1.getText().toString();
        String courseB = course2.getText().toString();
        String courseC = course3.getText().toString();

        String type = "register";

        RegisterFunction registerFunction = new RegisterFunction(this);
        registerFunction.execute(type, firstname, lastname, matric, fac, department, courseA, courseB, courseC, pass);

    }


}
