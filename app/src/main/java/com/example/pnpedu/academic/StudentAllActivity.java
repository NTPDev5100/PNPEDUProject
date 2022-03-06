package com.example.pnpedu.academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnpedu.R;
import com.example.pnpedu.adapter.adapterstudentall;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Student;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class StudentAllActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewstudentall;

    ArrayList<Student> ArrayListStudent;
    com.example.pnpedu.database.database database;
    adapterstudentall adapterstudentall;

    TextView tvnamestudentall,tvgenderstudentall,tvcodestudentall,tvdobstudentall;
    ImageView imageView;
    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_all);

        toolbar = findViewById(R.id.toolbarStudentall);
        listViewstudentall = findViewById(R.id.listviewstudentall);
        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);
        ArrayListStudent = new ArrayList<>();
        ArrayListStudent.clear();
        Cursor cursor = database.getAllDataStudent();
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
        adapterstudentall = new adapterstudentall(StudentAllActivity.this,ArrayListStudent);

        //Hien thi listview
        listViewstudentall.setAdapter(adapterstudentall);
        cursor.moveToFirst();
        cursor.close();
        //Tao su kien click vao item student
        bottomSheetDialog = new BottomSheetDialog(this);
        listViewstudentall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String name = ArrayListStudent.get(i).getStudent_name();
                String code = ArrayListStudent.get(i).getStudent_code();
                String gender = ArrayListStudent.get(i).getGender();
                String birthday = ArrayListStudent.get(i).getDate_of_birth();
                byte[] image = ArrayListStudent.get(i).getImage();
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);

                View viewDialog = getLayoutInflater().inflate(R.layout.student_info_bottom_sheet,null);
                bottomSheetDialog.setContentView(viewDialog);
                bottomSheetDialog.show();
                bottomSheetDialog.setCancelable(true);
                tvnamestudentall = viewDialog.findViewById(R.id.txtStudentNameall);
                tvgenderstudentall = viewDialog.findViewById(R.id.txtStudentGenderall);
                tvcodestudentall = viewDialog.findViewById(R.id.txtStudentCodeall);
                tvdobstudentall =  viewDialog.findViewById(R.id.txtStudentdoball);
                imageView = viewDialog.findViewById(R.id.imageviewInforStudentall);

                tvnamestudentall.setText(name);
                tvgenderstudentall.setText(gender);
                tvcodestudentall.setText(code);
                tvdobstudentall.setText(birthday);
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    //Them icon tim kiem
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusearchstudent,menu);

        MenuItem searchItem = menu.findItem(R.id.item_search_studentall);

        SearchView searchView = (SearchView) menu.findItem(R.id.item_search_studentall).getActionView();
        searchView.setQueryHint("Search Student");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(StudentAllActivity.this,query,Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterstudentall.filter(newText.trim());
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            //Chuyen qua man hinh add student
            case R.id.item_search_studentall:
                //con lai nut back chuyen qua teacher activity
            default:
                if (item.getItemId() == android.R.id.home) {
                    onBackPressed();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

}