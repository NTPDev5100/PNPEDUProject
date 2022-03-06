package com.example.pnpedu.model;

public class Classmodel {

    private int id_class;
    private String class_name;
    private String class_code;
    private String study_time;
    private String schedule;
    private String start_day;
    private String end_day;
    private int id_teacher;

    public Classmodel(String class_name, String class_code, String study_time, String schedule, String start_day, String end_day) {
        this.class_name = class_name;
        this.class_code = class_code;
        this.study_time = study_time;
        this.schedule = schedule;
        this.start_day = start_day;
        this.end_day = end_day;
    }

    public Classmodel(String class_name, String class_code, String study_time, String schedule, String start_day, String end_day, int id_teacher) {
        this.class_name = class_name;
        this.class_code = class_code;
        this.study_time = study_time;
        this.schedule = schedule;
        this.start_day = start_day;
        this.end_day = end_day;
        this.id_teacher = id_teacher;
    }

    public Classmodel(int id_class, String class_name, String class_code, String study_time, String schedule, String start_day, String end_day, int id_teacher) {
        this.id_class = id_class;
        this.class_name = class_name;
        this.class_code = class_code;
        this.study_time = study_time;
        this.schedule = schedule;
        this.start_day = start_day;
        this.end_day = end_day;
        this.id_teacher = id_teacher;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getStudy_time() {
        return study_time;
    }

    public void setStudy_time(String study_time) {
        this.study_time = study_time;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getStart_day() {
        return start_day;
    }

    public void setStart_day(String start_day) {
        this.start_day = start_day;
    }

    public String getEnd_day() {
        return end_day;
    }

    public void setEnd_day(String end_day) {
        this.end_day = end_day;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }
}
