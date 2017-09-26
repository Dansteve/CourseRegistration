package com.royalniks.courseregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Damilare on 21/06/2017.
 */

public class DisplayData extends AppCompatActivity {
    TextView fname,userid, lname,mat_no,password, dept, faculty,course1,course2,course3;
    Button logout,Editbtn;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_data);
        fname = (TextView)findViewById(R.id.fname);
        lname=(TextView)findViewById(R.id.lname);
//        userid = (TextView)findViewById(R.id.user_id);
        mat_no=(TextView)findViewById(R.id.matric);
//        password=(TextView)findViewById(R.id.password);
        dept=(TextView)findViewById(R.id.department);
        faculty=(TextView)findViewById(R.id.faculty);
        course1=(TextView)findViewById(R.id.course1);
        course2=(TextView)findViewById(R.id.course2);
        course3=(TextView)findViewById(R.id.course3);

        Bundle bundle = getIntent().getExtras();

//Extract the data…
        String  data = bundle.getString("dataValue");
        try {
            JSONObject temp = new JSONObject(data);


            fname.setText(temp.getString("f_name"));
            id = temp.getString("user_id");
            lname.setText(temp.getString("l_name"));
            mat_no.setText(temp.getString("matric"));
            dept.setText(temp.getString("department"));
            faculty.setText(temp.getString("faculty"));
            course1.setText(temp.getString("course1"));
            course2.setText(temp.getString("course2"));
            course3.setText(temp.getString("course3"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

//Create the text view


//        Editbtn= (Button) findViewById(R.id.btnEdit);
//        Editbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        logout= (Button) findViewById(R.id.btnLogOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayData.this, Login.class);
                startActivity(intent);
            }
        });

    }
//    public  void onEditData(View view) {
//        String firstname = fname.getText().toString();
//        String lastname = lname.getText().toString();
//        String matric = mat_no.getText().toString();
//        String pass = password.getText().toString();
//        String department = dept.getText().toString();
//        String fac = faculty.getText().toString();
//        String courseA = course1.getText().toString();
//        String courseB = course2.getText().toString();
//        String courseC = course3.getText().toString();
//
//        String type = "edit";
//
//        EditFuntion editfuntion = new EditFuntion(this);
//        editfuntion.execute(type, firstname, lastname, matric, fac, department, courseA, courseB, courseC);
//
//    }

    public  void onEdit(View view) {
        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String matric = mat_no.getText().toString();
        String department = dept.getText().toString();
        String fac = faculty.getText().toString();
        String courseA = course1.getText().toString();
        String courseB = course2.getText().toString();
        String courseC = course3.getText().toString();

        String type = "edit";

        EditFunction editFunction = new EditFunction(this);
        editFunction.execute(type, firstname, lastname, matric, fac, department, courseA, courseB, courseC ,id);

    }

    public void onBackPressed() {
        //do nothing
    }

}
