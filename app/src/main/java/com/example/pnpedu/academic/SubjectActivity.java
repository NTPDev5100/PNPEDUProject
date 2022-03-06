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
import androidx.appcompat.widget.Toolbar;

import com.example.pnpedu.R;
import com.example.pnpedu.adapter.adaptersubject;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Subject;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewsubject;
    ArrayList<Subject> ArrayListSubject;
    database database;
    adaptersubject adaptersubject;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);


        toolbar = findViewById(R.id.toolbarSubject);
        listViewsubject =findViewById(R.id.listviewSubject);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListSubject = new ArrayList<>();

        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int lesson= cursor.getInt(2);
            String time = cursor.getString(3);

            ArrayListSubject.add(new Subject(id,name,lesson,time));

        }

        adaptersubject = new adaptersubject(SubjectActivity.this,ArrayListSubject);
        listViewsubject.setAdapter(adaptersubject);
        cursor.moveToFirst();
        cursor.close();

        //Tao su kien click vao item subject
         listViewsubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(SubjectActivity.this,TeacherActivity.class);
                int id_subject = ArrayListSubject.get(i).getId();
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
            }
        });
    }


    //Them mot menu la add vao toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //Neu click vao add thi chuyen qua man hinh add subject
            case R.id.menuadd:
                Intent intent = new Intent(SubjectActivity.this, AddSubjectActivity.class);
                startActivity(intent);
                finish();
                break;
                //Neu click vao nut con laij la nut back thi quay lai main
            default:
                onBackPressed();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Neu click back o dien thoai se tro ve home activity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
            finish();
    }

    public void information(final int pos){


        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if(id == pos){
                Intent intent = new Intent(SubjectActivity.this, InformationSubjectActivity.class);

                intent.putExtra("id",id);
                String name=cursor.getString(1);
                int lesson=cursor.getInt(2);
                String time=cursor.getString(3);

                intent.putExtra("name",name);
                intent.putExtra("lesson",lesson);
                intent.putExtra("time",time);

                startActivity(intent);
            }

        }
    }
    //Phuong thuc xoa subject
    public void delete(final int position){
        //Doi tuong cua so
        Dialog dialog = new Dialog(this);

        //Nap layout vao dialog
        dialog.setContentView(R.layout.dialogdeletesubject);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesDeleteSubject);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteSubject);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Xoa subject trong csdl
                database.DeleteSubject(position);

                //Xoa teacher
                database.DeleteSubjectTeacher(position);
                //Cap nhat lai activitysubject
                Intent intent = new Intent(SubjectActivity.this,SubjectActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //dong dialog neu no
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        //Show dialog
        dialog.show();
    }

    public void update(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if(id == pos){
                Intent intent = new Intent(SubjectActivity.this, UpdateSubjectActivity.class);



                String name = cursor.getString(1);
                int lesson = cursor.getInt(2);
                String time =  cursor.getString(3);

                //Gui du lieu qua activity update
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("lesson",lesson);
                intent.putExtra("time",time);

                startActivity(intent);
                finish();
            }
        }
    }

}