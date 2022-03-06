package com.example.pnpedu.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pnpedu.R;
import com.example.pnpedu.academic.AddTeacherActivity;
import com.example.pnpedu.academic.StudentActivity;
import com.example.pnpedu.academic.SubjectActivity;
import com.example.pnpedu.academic.TeacherActivity;
import com.example.pnpedu.adapter.adapterstudent;
import com.example.pnpedu.adapter.adapterteacherclass;
import com.example.pnpedu.adapter.adapterteacherstudent;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Classmodel;
import com.example.pnpedu.model.Student;

import java.util.ArrayList;

public class TeacherStudentActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewstudent;

    ArrayList<Student> ArrayListstudent;
    com.example.pnpedu.database.database database;
    adapterteacherstudent adapter;
    int id_class = 0;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_student);

        toolbar = findViewById(R.id.toolbarStudentForTeacher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewstudent = findViewById(R.id.listviewstudentforteacher);
        Intent intent = getIntent();
        id_class = intent.getIntExtra("id_class", 0);

        database = new database(this);
        ArrayListstudent = new ArrayList<>();
        ArrayListstudent.clear();

        Cursor cursor = database.getDataStudent(id_class);
        while (cursor.moveToNext()) {
            int id_class = cursor.getInt(6);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String gender = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday = cursor.getString(4);
            byte[] picture = cursor.getBlob(5);

            ArrayListstudent.add(new Student(id, name, gender, code, birthday, picture, id_class));
        }
        adapter = new adapterteacherstudent(TeacherStudentActivity.this, ArrayListstudent);

        //Hien thi listview
        listViewstudent.setAdapter(adapter);
        cursor.moveToFirst();
        cursor.close();

        listViewstudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(TeacherStudentActivity.this, TeacherStudentGradeActivity.class);
                int id_student = ArrayListstudent.get(i).getId_student();
                intent.putExtra("id_student", id_student);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
