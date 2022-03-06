package com.example.pnpedu.model;

public class Score {
    private int id_score;
    private String score_code;
    private float score1;
    private float score2;
    private float score3;
    private float final_score;
    private float total;
    private int id_student;


    public Score(String score_code, float score1, float score2, float score3, float final_score, float total) {
        this.score_code = score_code;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.final_score = final_score;
        this.total = total;
    }

    public Score(String score_code, float score1, float score2, float score3, float final_score, float total, int id_student) {
        this.score_code = score_code;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.final_score = final_score;
        this.total = total;
        this.id_student = id_student;
    }

    public Score(int id_score, String score_code, float score1, float score2, float score3, float final_score, float total, int id_student) {
        this.id_score = id_score;
        this.score_code = score_code;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.final_score = final_score;
        this.total = total;
        this.id_student = id_student;
    }

    public int getId_score() {
        return id_score;
    }

    public void setId_score(int id_score) {
        this.id_score = id_score;
    }

    public String getScore_code() {
        return score_code;
    }

    public void setScore_code(String score_code) {
        this.score_code = score_code;
    }

    public float getScore1() {
        return score1;
    }

    public void setScore1(float score1) {
        this.score1 = score1;
    }

    public float getScore2() {
        return score2;
    }

    public void setScore2(float score2) {
        this.score2 = score2;
    }

    public float getScore3() {
        return score3;
    }

    public void setScore3(float score3) {
        this.score3 = score3;
    }

    public float getFinal_score() {
        return final_score;
    }

    public void setFinal_score(float final_score) {
        this.final_score = final_score;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }
}
