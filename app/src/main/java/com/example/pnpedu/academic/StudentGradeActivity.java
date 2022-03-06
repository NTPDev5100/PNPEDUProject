package com.example.pnpedu.academic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.pnpedu.R;
import com.example.pnpedu.adapter.adapterstudentgrade;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Score;

import java.util.ArrayList;

public class StudentGradeActivity extends AppCompatActivity {

    ListView listViewsGrade;

    ArrayList<Score> ArrayListScore;
    com.example.pnpedu.database.database database;
    com.example.pnpedu.adapter.adapterstudentgrade adapterstudentgrade;
    int id_student =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grade);

        listViewsGrade = findViewById(R.id.listviewstudentgrade);

        Intent intent = getIntent();
        id_student = intent.getIntExtra("id_student",0);
        database = new database(this);

        ArrayListScore = new ArrayList<>();
        ArrayListScore.clear();
        Cursor cursor = database.getDataScore(id_student);
        while (cursor.moveToNext()){
            int id_student = cursor.getInt(7);
            int id = cursor.getInt(0);
            String score_code = cursor.getString(1);
            float score1 = cursor.getFloat(2);
            float score2 = cursor.getFloat(3);
            float score3 = cursor.getFloat(4);
            float finalscore = cursor.getFloat(5);
            float totalscore = cursor.getFloat(6);

            ArrayListScore.add(new Score(id,score_code,score1,score2,score3,finalscore,totalscore,id_student));
        }

        adapterstudentgrade = new adapterstudentgrade(StudentGradeActivity.this,ArrayListScore);

        listViewsGrade.setAdapter(adapterstudentgrade);
        cursor.moveToFirst();
        cursor.close();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public void update(final int id_score) {
        Cursor cursor = database.getDataScore(id_student);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == id_score){
                Intent intent = new Intent(StudentGradeActivity.this, UpdateStudentGradeActivity.class);

                intent.putExtra("id",id_score);

                String score_code = cursor.getString(1);
                float score1 = cursor.getFloat(2);
                float score2 = cursor.getFloat(3);
                float score3 = cursor.getFloat(4);
                float finalscore = cursor.getFloat(5);
                float totalscore = cursor.getFloat(6);
                int id_student = cursor.getInt(7);

                intent.putExtra("score_code",score_code);
                intent.putExtra("score1",score1);
                intent.putExtra("score2",score2);
                intent.putExtra("score3",score3);
                intent.putExtra("finalscore",finalscore);
                intent.putExtra("totalscore",totalscore);
                intent.putExtra("id_student",id_student);
                startActivity(intent);
                finish();
            }
        }
        cursor.close();
    }

}