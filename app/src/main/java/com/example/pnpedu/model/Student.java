package com.example.pnpedu.model;

public class Student {

    private int id_student;
    private String student_name;
    private String gender;
    private String student_code;
    private String date_of_birth;
    private byte[] image;
    private int id_class;



    public Student(String student_name, String gender, String student_code, String date_of_birth, byte[] image) {
        this.student_name = student_name;
        this.gender = gender;
        this.student_code = student_code;
        this.date_of_birth = date_of_birth;
        this.image = image;
    }

    public Student(String student_name, String gender, String student_code, String date_of_birth, byte[] image, int id_class) {
        this.student_name = student_name;
        this.gender = gender;
        this.student_code = student_code;
        this.date_of_birth = date_of_birth;
        this.image = image;
        this.id_class = id_class;
    }

    public Student(int id_student, String student_name, String gender, String student_code, String date_of_birth, byte[] image, int id_class) {
        this.id_student = id_student;
        this.student_name = student_name;
        this.gender = gender;
        this.student_code = student_code;
        this.date_of_birth = date_of_birth;
        this.image = image;
        this.id_class = id_class;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }
}
