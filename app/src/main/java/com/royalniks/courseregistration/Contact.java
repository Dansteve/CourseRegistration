package com.royalniks.courseregistration;

/**
 * Created by Damilare on 21/06/2017.
 */

public class Contact {
    String fname, lname,mat_no,password, dept, faculty,course1,course2,course3;
    public  Contact(String fname, String lname, String mat_no, String password, String dept, String faculty, String course1, String course2, String course3){
        this.fname=fname;
        this.lname=lname;
        this.mat_no = mat_no;
        this.password=password;
        this.dept= dept;
        this.faculty= faculty;
        this.course1= course1;
        this.course2 = course2;
        this.course3=course3;
    }
    public Contact(String mat_no, String password){
        this.mat_no = mat_no;
        this.password=password;
    }
}
