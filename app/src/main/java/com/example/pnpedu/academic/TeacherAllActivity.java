package com.example.pnpedu.academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pnpedu.R;
import com.example.pnpedu.adapter.adapterteacherall;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Teacher;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class TeacherAllActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewteacherall;
    ArrayList<Teacher> ArrayListTeacher;
    database database;
    adapterteacherall adapterteacherall;
    TextView tvnameteacherall,tvcodeteacherall,tvgenderteacherall,tvemailteacherall,tvpassteacherall,tvdobteacherall;
    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_all);

        toolbar = findViewById(R.id.toolbarteacherall);
        listViewteacherall = findViewById(R.id.listviewteacherall);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);
        ArrayListTeacher = new ArrayList<>();
        ArrayListTeacher.clear();
        Cursor cursor = database.getAllDataTeacher();
        while (cursor.moveToNext()){
            int id_sub = cursor.getInt(7);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String gender = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday = cursor.getString(4);
            String email = cursor.getString(5);
            String pass = cursor.getString(6);
            ArrayListTeacher.add(new Teacher(id, name, gender, code, birthday, email, pass, id_sub));
        }
        adapterteacherall = new adapterteacherall(TeacherAllActivity.this,ArrayListTeacher);

        //Hien thi listview
        listViewteacherall.setAdapter(adapterteacherall);
        cursor.moveToFirst();
        cursor.close();
        //Tao su kien click vao item teacher
        bottomSheetDialog = new BottomSheetDialog(this);
        listViewteacherall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String name = ArrayListTeacher.get(i).getTeacher_name();
                String gender = ArrayListTeacher.get(i).getGender();
                String code = ArrayListTeacher.get(i).getTeacher_code();
                String birthday = ArrayListTeacher.get(i).getDate_of_birth();
                String email = ArrayListTeacher.get(i).getEmail();
                String pass = ArrayListTeacher.get(i).getPassword();

                View viewDialog = getLayoutInflater().inflate(R.layout.teacher_info_bottom_sheet,null);
                bottomSheetDialog.setContentView(viewDialog);
                bottomSheetDialog.show();
                bottomSheetDialog.setCancelable(true);
                tvnameteacherall = viewDialog.findViewById(R.id.txtTeacherNameall);
                tvgenderteacherall = viewDialog.findViewById(R.id.txtTeacherGenderall);
                tvcodeteacherall = viewDialog.findViewById(R.id.txtTeacherCodeall);
                tvdobteacherall = viewDialog.findViewById(R.id.txtTeacherdoball);
                tvemailteacherall = viewDialog.findViewById(R.id.txtTeacherEmailall);
                tvpassteacherall = viewDialog.findViewById(R.id.txtTeacherPassall);

                tvnameteacherall.setText(name);
                tvgenderteacherall.setText(gender);
                tvcodeteacherall.setText(code);
                tvdobteacherall.setText(birthday);
                tvemailteacherall.setText(email);
                tvpassteacherall.setText(pass);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            //Chuyen qua man hinh add student
            default:
                if (item.getItemId() == android.R.id.home) {
                    onBackPressed();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }
}