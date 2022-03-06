package com.example.pnpedu.academic;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pnpedu.R;
import com.example.pnpedu.adapter.adapterstudent;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Student;
import com.example.pnpedu.teacher.TeacherStudentActivity;
import com.example.pnpedu.teacher.TeacherStudentGradeActivity;


import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {


    Toolbar toolbar;
    ListView listViewstudent;

    ArrayList<Student> ArrayListStudent;
    database database;
    adapterstudent adapterstudent;

    int id_class = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbar = findViewById(R.id.toolbarStudent);
        listViewstudent = findViewById(R.id.listviewstudent);

        Intent intent = getIntent();
        id_class = intent.getIntExtra("id_class",0);

        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListStudent = new ArrayList<>();
        ArrayListStudent.clear();

        Cursor cursor = database.getDataStudent(id_class);
        while (cursor.moveToNext()){
            int id_class = cursor.getInt(6);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String gender = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday = cursor.getString(4);
            byte[] picture = cursor.getBlob(5);

            ArrayListStudent.add(new Student(id,name,gender,code,birthday,picture,id_class));
        }
        adapterstudent = new adapterstudent(StudentActivity.this,ArrayListStudent);

        //Hien thi listview
        listViewstudent.setAdapter(adapterstudent);
        cursor.moveToFirst();
        cursor.close();

        listViewstudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(StudentActivity.this, StudentGradeActivity.class);
                int id_student = ArrayListStudent.get(i).getId_student();
                intent.putExtra("id_student", id_student);
                startActivity(intent);
            }
        });
    }

    //Them icon add
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddstudent,menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);

        SearchView searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        searchView.setQueryHint("Search Student");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(StudentActivity.this,query,Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterstudent.filter(newText.trim());
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            //Chuyen qua man hinh add student
            case R.id.menuaddstudent:

                Intent intent = new Intent(StudentActivity.this, AddStudentActivity.class);
                intent.putExtra("id_class", id_class);
                startActivity(intent);
                finish();
                break;
            case R.id.item_search:
                //con lai nut back chuyen qua teacher activity
            default:
                if (item.getItemId() == android.R.id.home) {
                    onBackPressed();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    //back tren dien thoai ra class activity

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void information(final int pos){
        Cursor cursor = database.getDataStudent(id_class);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == pos){
                Intent intent = new Intent(StudentActivity.this, InformationStudentActivity.class);

                intent.putExtra("id",pos);

                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                byte[] picture = cursor.getBlob(5);
                int id_class = cursor.getInt(6);

                intent.putExtra("name",name);
                intent.putExtra("gender",gender);
                intent.putExtra("code",code);
                intent.putExtra("birthday",birthday);
                intent.putExtra("picture",picture);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    //Xoa
    public void delete(final int position){
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogdeletestudent);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesDeleteStudent);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xoa student trong database
                database.DeleteStudent(position);


                //cap nhat lai activity student
                Intent intent = new Intent(StudentActivity.this,StudentActivity.class);
                intent.putExtra("id_class",id_class);
                startActivity(intent);
                finish();
                dialog.dismiss();

            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    //update
    public  void update(final int id_student){
        Cursor cursor = database.getDataStudent(id_class);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == id_student){
                Intent intent = new Intent(StudentActivity.this, UpdateStudentActivity.class);

                intent.putExtra("id",id_student);

                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                byte[] picture = cursor.getBlob(5);
                int id_class = cursor.getInt(6);

                intent.putExtra("name",name);
                intent.putExtra("gender",gender);
                intent.putExtra("code",code);
                intent.putExtra("birthday",birthday);
                intent.putExtra("picture",picture);
                intent.putExtra("id_class",id_class);
                startActivity(intent);
                finish();
            }
        }
        cursor.close();
    }
}