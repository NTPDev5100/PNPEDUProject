package com.example.pnpedu.academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import androidx.appcompat.widget.Toolbar;

import com.example.pnpedu.R;
import com.example.pnpedu.adapter.adapterteacher;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Teacher;

import java.util.ArrayList;

public class TeacherActivity extends AppCompatActivity {


    Toolbar toolbar;
    ListView listViewteacher;

    ArrayList<Teacher> ArrayListTeacher;
    database database;
    adapterteacher adapterteacher;

    int id_subject = 0;
    int counter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        toolbar = findViewById(R.id.toolbarTeacher);
        listViewteacher = findViewById(R.id.listviewteacher);

        Intent intent = getIntent();
        id_subject = intent.getIntExtra("id_subject",0);


        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);


        ArrayListTeacher = new ArrayList<>();
        ArrayListTeacher.clear();

        Cursor cursor = database.getDataTeacher(id_subject);
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
        adapterteacher = new adapterteacher(TeacherActivity.this,ArrayListTeacher);

        //Hien thi listview
        listViewteacher.setAdapter(adapterteacher);
        cursor.moveToFirst();
        cursor.close();


        //Tao su kien click vao item teacher
        listViewteacher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(TeacherActivity.this, ClassActivity.class);
                int id_teacher = ArrayListTeacher.get(i).getId_teacher();
                intent.putExtra("id_teacher",id_teacher);
                startActivity(intent);
            }
        });

    }

    //Them icon add
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddteacher,menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);

        SearchView searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        searchView.setQueryHint("Search Teacher");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(TeacherActivity.this,query,Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterteacher.filter(newText.trim());
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            //Chuyen qua man hinh add teacher
            case R.id.menuaddteacher:

                Intent intent = new Intent(TeacherActivity.this, AddTeacherActivity.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //back tren dien thoai ra subject activity
    @Override
    public void onBackPressed() {
       super.onBackPressed();
            finish();
    }

    public void information(final int pos){
        Cursor cursor = database.getDataTeacher(id_subject);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == pos){
                Intent intent = new Intent(TeacherActivity.this, InfomationTeacherActivity.class);

                intent.putExtra("id",pos);

                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                String email = cursor.getString(5);
                String pass = cursor.getString(6);
                int id_subject = cursor.getInt(7);

                intent.putExtra("name",name);
                intent.putExtra("gender",gender);
                intent.putExtra("code",code);
                intent.putExtra("birthday",birthday);
                intent.putExtra("email",email);
                intent.putExtra("password",pass);

                startActivity(intent);
            }
        }
        cursor.close();
    }


    //Xoa
    public void delete(final int position){
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogdeleteteacher);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesDeleteTeacher);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteTeacher);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xoa teacher trong database
                database.DeleteTeacher(position);

                //Xoa class
                database.DeleteTeacherClass(position);

                //cap nhat lai activity teacher
                Intent intent = new Intent(TeacherActivity.this,TeacherActivity.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
                finish();

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
    public  void update(final int id_teacher){
        Cursor cursor = database.getDataTeacher(id_subject);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == id_teacher){
                Intent intent = new Intent(TeacherActivity.this, UpdateTeacherActivity.class);

                intent.putExtra("id",id_teacher);

                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                String email = cursor.getString(5);
                String pass = cursor.getString(6);
                int id_subject = cursor.getInt(7);

                intent.putExtra("name",name);
                intent.putExtra("gender",gender);
                intent.putExtra("code",code);
                intent.putExtra("birthday",birthday);
                intent.putExtra("email",email);
                intent.putExtra("password",pass);
                intent.putExtra("id_subject",id_subject);

                startActivity(intent);
                finish();
            }
        }
        cursor.close();
    }


}