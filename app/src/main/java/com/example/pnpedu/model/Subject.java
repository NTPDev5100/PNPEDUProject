package com.example.pnpedu.model;

public class Subject {

    //Các biến subject
    //id môn học
    private int id;
    //tên môn học
    private String subject_name;
    //số tiết học
    private int number_of_lessons;
    //thời gian học
    private String time;

    public Subject(String subject_name, int number_of_lessons, String time) {
        this.subject_name = subject_name;
        this.number_of_lessons = number_of_lessons;
        this.time = time;
    }

    public Subject(int id, String subject_name, int number_of_lessons, String time) {
        this.id = id;
        this.subject_name = subject_name;
        this.number_of_lessons = number_of_lessons;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getNumber_of_lessons() {
        return number_of_lessons;
    }

    public void setNumber_of_lessons(int number_of_lessons) {
        this.number_of_lessons = number_of_lessons;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
