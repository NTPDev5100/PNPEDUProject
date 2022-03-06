package com.example.pnpedu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.pnpedu.R;
import com.example.pnpedu.model.Admin;
import com.example.pnpedu.model.Classmodel;
import com.example.pnpedu.model.Score;
import com.example.pnpedu.model.Student;
import com.example.pnpedu.model.Subject;
import com.example.pnpedu.model.Teacher;

public class database extends SQLiteOpenHelper {


    //Tên database
    private static String DATABASE_NAME = "pnpmanagement";
    //Bảng môn học
    private static String TABLE_SUBJECTS = "subject";
    private static String ID_SUBJECTS = "idsubject";
    private static String SUBJECT_NAME = "subjectname";
    private static String NUMBER_OF_LESSONS = "numberoflessons";
    private static String TIME = "time";
    private static int VERSION = 1;

    //Bảng giáo viên
    private static String TABLE_TEACHER ="teacher";
    private static String ID_TEACHER = "idteacher";
    private static String TEACHER_NAME = "teachername";
    private static String GENDER_TEACHER ="gender";
    private static String TEACHER_CODE = "teachercode";
    private static String DATE_OF_BIRTH_TEACHER ="dateofbirth";
    private static String EMAIL ="email";
    private static String PASSWORD_TEACHER = "password";


    //Bảng lớp học
    private static String TABLE_CLASS ="class";
    private static String ID_CLASS ="idclass";
    private static String CLASS_NAME="classname";
    private static String CLASS_CODE="classcode";
    private static String STUDY_TIME ="studytime";
    private static String SCHEDULE ="schedule";
    private static String START_DAY ="startday";
    private static String END_DAY = "endday";

    //Bảng học viên
    private static String TABLE_STUDENT = "student";
    private static String ID_STUDENT = "idstudent";
    private static String STUDENT_NAME = "sudentname";
    private static String GENDER_STUDENT = "gender";
    private static String STUDENT_CODE = "studentcode";
    private static String DATE_OF_BIRTH_STUDENT = "dateofbirth";
    private static String IMAGE_STUDENT = "studentimage";

    //Bảng điểm
    private static String TABLE_SCORES ="scores";
    private static String ID_SCORES ="idscores";
    private static String SCORES_CODE = "scorescode";
    private static String SUBJECT_NAME_SCORES ="subject";
    private static String TEST_SCORE_1="testscore1";
    private static String TEST_SCORE_2="testscore2";
    private static String TEST_SCORE_3="testscore3";
    private static String FINAL_EXAM_SCORE ="finalexamscore";
    private static String TOTAL_SCORE = "totalscore";

    //Bảng tài khoản học vụ
    private static String TABLE_ADMIN = "admin";
    private static String ID_ADMIN ="idadmin";
    private static String USERNAME="username";
    private static String PASSWORD= "password";








    //Tạo bảng môn học
    private String SQLQuery = "CREATE TABLE "+ TABLE_SUBJECTS +" ( "+ID_SUBJECTS+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SUBJECT_NAME+" TEXT, "
            +NUMBER_OF_LESSONS+" INTEGER, "
            +TIME+" TEXT) ";

    //Tạo bảng giáo viên
    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TEACHER +" ( "+ID_TEACHER+" integer primary key AUTOINCREMENT, "
            +TEACHER_NAME+" TEXT, "
            +GENDER_TEACHER+" TEXT, "
            +TEACHER_CODE+" TEXT, "
            +DATE_OF_BIRTH_TEACHER+" TEXT, "
            +EMAIL+" TEXT, "
            +PASSWORD_TEACHER+" TEXT, "
            +ID_SUBJECTS+" INTEGER , FOREIGN KEY ( "+ ID_SUBJECTS +" ) REFERENCES "+
            TABLE_SUBJECTS+"("+ID_SUBJECTS+"))";

    //Tạo bảng lớp
    private String SQLQuery2 = "CREATE TABLE "+ TABLE_CLASS +" ( "+ID_CLASS+" integer primary key AUTOINCREMENT, "
            +CLASS_NAME+" TEXT, "
            +CLASS_CODE+" TEXT, "
            +STUDY_TIME+" TEXT, "
            +SCHEDULE+" TEXT, "
            +START_DAY+" TEXT, "
            +END_DAY+" TEXT, "
            +ID_TEACHER+" INTEGER , FOREIGN KEY ( "+ ID_TEACHER +" ) REFERENCES "+
            TABLE_TEACHER+"("+ID_TEACHER+"))";


    //Tạo bảng sinh viên
    private String SQLQuery3 = "CREATE TABLE "+ TABLE_STUDENT +" ( "+ID_STUDENT+" integer primary key AUTOINCREMENT, "
            +STUDENT_NAME+" TEXT, "
            +GENDER_STUDENT+" TEXT, "
            +STUDENT_CODE+" TEXT, "
            +DATE_OF_BIRTH_STUDENT+" TEXT, "
            +IMAGE_STUDENT+" blob ,"
            +ID_CLASS+" INTEGER , FOREIGN KEY ( "+ ID_CLASS +" ) REFERENCES "+
            TABLE_CLASS+"("+ID_CLASS+"))";

    //Tạo bảng điểm
    private String SQLQuery4 = "CREATE TABLE "+ TABLE_SCORES +" ( "+ID_SCORES+" integer primary key AUTOINCREMENT, "
            +SCORES_CODE+" TEXT, "
            +TEST_SCORE_1+" FLOAT, "
            +TEST_SCORE_2+" FLOAT, "
            +TEST_SCORE_3+" FLOAT, "
            +FINAL_EXAM_SCORE+" FLOAT, "
            +TOTAL_SCORE+" FLOAT, "
            +ID_STUDENT+" INTEGER , FOREIGN KEY ( "+ ID_STUDENT +" ) REFERENCES "+
            TABLE_STUDENT+"("+ID_STUDENT+"))";

    //Tao bang tai khoan hoc vu
    private String SQLQuery5 = "CREATE TABLE "+ TABLE_ADMIN +" ( "+ID_ADMIN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +USERNAME+" TEXT, "
            +PASSWORD+" TEXT) ";

    public database(@Nullable Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        sqLiteDatabase.execSQL(SQLQuery1);
        sqLiteDatabase.execSQL(SQLQuery2);
        sqLiteDatabase.execSQL(SQLQuery3);
        sqLiteDatabase.execSQL(SQLQuery4);
        sqLiteDatabase.execSQL(SQLQuery5);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN) ;
    }


    //Phuong thuc insert admin
    public boolean AddAdmin(Admin admin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERNAME,admin.getUsername());
        values.put(PASSWORD,admin.getPassword());

        long result =db.insert(TABLE_ADMIN,null,values);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM admin WHERE username= ?", new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM admin WHERE username = ? and password = ?", new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    public boolean checkemailpasswordteacher(int id,String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM teacher WHERE idteacher= ? and email = ? and password = ?", new String[]{String.valueOf(id),email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }



    //Phương thức insert subject
    public void AddSubjects(Subject subject){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBJECT_NAME,subject.getSubject_name());
        values.put(NUMBER_OF_LESSONS,subject.getNumber_of_lessons());
        values.put(TIME,subject.getTime());

        db.insert(TABLE_SUBJECTS,null,values);
        db.close();
    }


    //Phuong thuc cap nhat subject
    public boolean UpdateSubject(Subject subject, int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBJECT_NAME,subject.getSubject_name());
        values.put(NUMBER_OF_LESSONS,subject.getNumber_of_lessons());
        values.put(TIME,subject.getTime());

        db.update(TABLE_SUBJECTS,values,ID_SUBJECTS+"=" +id,null);
        return true;
    }

    //Lay du lieu subject
    public Cursor getDataSubject(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_SUBJECTS,null);
        return cursor;
    }

    //Phuong thuc xoa subject
    public int DeleteSubject(int i){
        //Chu y :getWritableDatabase(); la ca doc va ghi
        //getReadableDatabase(); chi doc, khong duoc phep chinh sua, khong can thiet thi dung Read cho toi uu
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_SUBJECTS,ID_SUBJECTS +" = " +i,null);
        return res;
    }

    //Xoá các teacher của subject đã bị xoá
    public int DeleteSubjectTeacher(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_TEACHER,ID_SUBJECTS +" = " +i,null);
        return res;
    }

    public  int getSubjectCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SUBJECTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    //Insert Teacher
    public void AddTeacher(Teacher teacher){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TEACHER_NAME,teacher.getTeacher_name());
        values.put(GENDER_STUDENT,teacher.getGender());
        values.put(TEACHER_CODE,teacher.getTeacher_code());
        values.put(DATE_OF_BIRTH_TEACHER,teacher.getDate_of_birth());
        values.put(EMAIL,teacher.getEmail());
        values.put(PASSWORD_TEACHER,teacher.getPassword());
        values.put(ID_SUBJECTS,teacher.getId_subject());

        db.insert(TABLE_TEACHER,null,values);
        db.close();
    }

    public Cursor getDataTeacher(int id_subject){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_TEACHER + " WHERE " + ID_SUBJECTS+" = "+id_subject,null);
        return res;
    }

    public Cursor getAllDataTeacher(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_TEACHER ,null);
        return res;
    }

    //Xoa teacher
    public int DeleteTeacher(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_TEACHER,ID_TEACHER+ " = " +i,null);
        return res;
    }

    //Cap nhat teacher
    public boolean UpdateTeacher(Teacher teacher,int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TEACHER_NAME,teacher.getTeacher_name());
        values.put(GENDER_TEACHER,teacher.getGender());
        values.put(TEACHER_CODE,teacher.getTeacher_code());
        values.put(DATE_OF_BIRTH_TEACHER,teacher.getDate_of_birth());
        values.put(EMAIL,teacher.getEmail());
        values.put(PASSWORD_TEACHER,teacher.getPassword());

        db.update(TABLE_TEACHER,values,ID_TEACHER+" = "+id,null);
        return true;
    }



    //Xoá các class của teacher đã bị xoá
    public int DeleteTeacherClass(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_CLASS,ID_TEACHER +" = " +i,null);
        return res;
    }

    public  int getTeacherCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TEACHER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    //Insert Class
    public void AddClass(Classmodel classmodel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CLASS_NAME,classmodel.getClass_name());
        values.put(CLASS_CODE,classmodel.getClass_code());
        values.put(STUDY_TIME,classmodel.getStudy_time());
        values.put(SCHEDULE,classmodel.getSchedule());
        values.put(START_DAY,classmodel.getStart_day());
        values.put(END_DAY,classmodel.getEnd_day());
        values.put(ID_TEACHER,classmodel.getId_teacher());

        db.insert(TABLE_CLASS,null,values);
        db.close();
    }

    public Cursor getDataClass(int id_teacher){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_CLASS + " WHERE " + ID_TEACHER+" = "+id_teacher,null);
        return res;
    }

    //Xoa class
    public int DeleteClass(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_CLASS,ID_CLASS+ " = " +i,null);
        return res;
    }

    //Xoá các class của teacher đã bị xoá
    public int DeleteClassStudent(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT,ID_CLASS +" = " +i,null);
        return res;
    }
    //Cap nhat class
    public boolean UpdateClass(Classmodel classmodel,int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CLASS_NAME,classmodel.getClass_name());
        values.put(CLASS_CODE,classmodel.getClass_code());
        values.put(STUDY_TIME,classmodel.getStudy_time());
        values.put(SCHEDULE,classmodel.getSchedule());
        values.put(START_DAY,classmodel.getStart_day());
        values.put(END_DAY,classmodel.getEnd_day());

        db.update(TABLE_CLASS,values,ID_CLASS+" = "+id,null);
        return true;
    }

    public  int getClassCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CLASS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    //Insert student
    public void AddStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME,student.getStudent_name());
        values.put(GENDER_STUDENT,student.getGender());
        values.put(STUDENT_CODE,student.getStudent_code());
        values.put(DATE_OF_BIRTH_STUDENT,student.getDate_of_birth());
        values.put(IMAGE_STUDENT,student.getImage());
        values.put(ID_CLASS,student.getId_class());

        db.insert(TABLE_STUDENT,null,values);
    }
    public Cursor getDataStudent(int id_class){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_STUDENT + " WHERE " + ID_CLASS+" = "+id_class,null);
        return res;
    }

    public Cursor getAllDataStudent(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_STUDENT ,null);
        return res;
    }
    //Xoa student
    public int DeleteStudent(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT,ID_STUDENT+ " = " +i,null);
        return res;
    }
    //Cap nhat student
    public boolean UpdateStudent(Student student,int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME,student.getStudent_name());
        values.put(GENDER_STUDENT,student.getGender());
        values.put(STUDENT_CODE,student.getStudent_code());
        values.put(DATE_OF_BIRTH_STUDENT,student.getDate_of_birth());
        values.put(IMAGE_STUDENT,student.getImage());
        db.update(TABLE_STUDENT,values,ID_STUDENT+" = "+id,null);
        return true;
    }
    public  int getStudentCount() {
        String countQuery = "SELECT  * FROM " + TABLE_STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    //Insert score
    public void AddScore(Score score){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SCORES_CODE,score.getScore_code());
        values.put(TEST_SCORE_1,score.getScore1());
        values.put(TEST_SCORE_2,score.getScore2());
        values.put(TEST_SCORE_3,score.getScore3());
        values.put(FINAL_EXAM_SCORE,score.getFinal_score());
        values.put(TOTAL_SCORE,score.getTotal());
        values.put(ID_STUDENT,score.getId_student());

        db.insert(TABLE_SCORES,null,values);
        db.close();
    }

    public Cursor getDataScore(int id_student){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_SCORES + " WHERE " + ID_STUDENT+" = "+id_student,null);
        return res;
    }

    public boolean UpdateScore(Score score, int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SCORES_CODE,score.getScore_code());
        values.put(TEST_SCORE_1,score.getScore1());
        values.put(TEST_SCORE_2,score.getScore2());
        values.put(TEST_SCORE_3,score.getScore3());
        values.put(FINAL_EXAM_SCORE,score.getFinal_score());
        values.put(TOTAL_SCORE,score.getTotal());
        db.update(TABLE_SCORES,values,ID_SCORES+" = "+id,null);
        return true;
    }
    public int DeleteScore(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_SCORES,ID_SCORES+ " = " +i,null);
        return res;
    }


}
