package com.example.pnpedu.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pnpedu.R;
import com.example.pnpedu.academic.AddStudentActivity;
import com.example.pnpedu.academic.StudentActivity;
import com.example.pnpedu.academic.UpdateStudentActivity;
import com.example.pnpedu.adapter.adapterstudent;
import com.example.pnpedu.adapter.adapterteacherstudentgrade;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Score;
import com.example.pnpedu.model.Student;

import java.util.ArrayList;

public class TeacherStudentGradeActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewsGrade;

    ArrayList<Score> ArrayListScore;
    database database;
    adapterteacherstudentgrade mAdapterteacherstudentgrade;

    int id_student = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_student_grade);


        toolbar = findViewById(R.id.toolbarGrade);
        listViewsGrade = findViewById(R.id.listviewgrade);

        Intent intent = getIntent();
        id_student = intent.getIntExtra("id_student",0);

        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        mAdapterteacherstudentgrade = new adapterteacherstudentgrade(TeacherStudentGradeActivity.this,ArrayListScore);

        //Hien thi listview
        listViewsGrade.setAdapter(mAdapterteacherstudentgrade);

        cursor.moveToFirst();
        cursor.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddscore,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            //Chuyen qua man hinh add student
            case R.id.menuaddscore:

                Intent intent = new Intent(TeacherStudentGradeActivity.this, AddGradeActivity.class);
                intent.putExtra("id_student", id_student);
                startActivity(intent);
                finish();
                break;
            default:
                if (item.getItemId() == android.R.id.home) {
                    onBackPressed();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void delete(final int id) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogdeletescore);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesDeleteScore);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteScore);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xoa student trong database
                database.DeleteScore(id);


                //cap nhat lai activity student
                Intent intent = new Intent(TeacherStudentGradeActivity.this,TeacherStudentGradeActivity.class);
                intent.putExtra("id_student",id_student);
                startActivity(intent);
                finish();
                dialog.dismiss();

            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void update(final int id_score) {
        Cursor cursor = database.getDataScore(id_student);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == id_score){
                Intent intent = new Intent(TeacherStudentGradeActivity.this, UpdateGradeActivity.class);

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