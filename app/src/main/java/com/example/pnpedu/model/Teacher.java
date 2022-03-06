package com.example.pnpedu.model;

import android.text.TextUtils;
import android.util.Patterns;

public class Teacher {

    private int id_teacher;
    private String teacher_name;
    private String gender;
    private String teacher_code;
    private String date_of_birth;
    private String email;
    private String password;
    private int id_subject;


    public Teacher(int id_teacher, String email, String password) {
        this.id_teacher = id_teacher;
        this.email = email;
        this.password = password;
    }

    public Teacher(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Teacher(String teacher_name, String gender, String teacher_code, String date_of_birth, String email, String password) {
        this.teacher_name = teacher_name;
        this.gender = gender;
        this.teacher_code = teacher_code;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.password = password;
    }

    public Teacher(String teacher_name, String gender, String teacher_code, String date_of_birth, String email, String password, int id_subject) {
        this.teacher_name = teacher_name;
        this.gender = gender;
        this.teacher_code = teacher_code;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.password = password;
        this.id_subject = id_subject;
    }

    public Teacher(int id_teacher, String teacher_name, String gender, String teacher_code, String date_of_birth, String email, String password, int id_subject) {
        this.id_teacher = id_teacher;
        this.teacher_name = teacher_name;
        this.gender = gender;
        this.teacher_code = teacher_code;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.password = password;
        this.id_subject = id_subject;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTeacher_code() {
        return teacher_code;
    }

    public void setTeacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public int isValidl()
    {
        if(TextUtils.isEmpty(getEmail()))
        {
            return 0;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()){
            return 1;
        }
        else if(TextUtils.isEmpty(getPassword())){
            return 2;
        }
        else if (getPassword().length()<6){
            return 3;
        }
        else
            return -1;

    }
}
