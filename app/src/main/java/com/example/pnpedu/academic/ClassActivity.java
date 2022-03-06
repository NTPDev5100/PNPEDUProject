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


import com.example.pnpedu.R;
import com.example.pnpedu.adapter.adapterclass;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Classmodel;


import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewclass;

    ArrayList<Classmodel> ArrayListClass;
    database database;
    adapterclass adapterclass;

    int id_teacher = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        toolbar = findViewById(R.id.toolbarClass);
        listViewclass = findViewById(R.id.listviewclass);

        Intent intent = getIntent();
        id_teacher = intent.getIntExtra("id_teacher",0);

        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListClass = new ArrayList<>();
        ArrayListClass.clear();

        Cursor cursor = database.getDataClass(id_teacher);
        while (cursor.moveToNext()){
            int id_tea = cursor.getInt(7);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String code = cursor.getString(2);
            String studytime = cursor.getString(3);
            String schedule = cursor.getString(4);
            String startday = cursor.getString(5);
            String endday = cursor.getString(6);

            ArrayListClass.add(new Classmodel(id,name,code,studytime,schedule,startday,endday,id_tea));
        }
        adapterclass = new adapterclass(ClassActivity.this,ArrayListClass);

        //Hien thi listview
        listViewclass.setAdapter(adapterclass);
        cursor.moveToFirst();
        cursor.close();

        //Tao su kien click vao item student
        listViewclass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(ClassActivity.this, StudentActivity.class);
                int id_class = ArrayListClass.get(i).getId_class();
                intent.putExtra("id_class",id_class);
                startActivity(intent);
            }
        });

    }


    //Them icon add
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddclass,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            //Chuyen qua man hinh add class
            case R.id.menuaddclass:

                Intent intent = new Intent(ClassActivity.this, AddClassActivity.class);
                intent.putExtra("id_teacher",id_teacher);
                startActivity(intent);
                finish();
                break;
            //con lai nut back chuyen qua teacher activity
            default:
                if(item.getItemId() == android.R.id.home){
                    onBackPressed();
                    return true;
                }
        }

        return super.onOptionsItemSelected(item);
    }
    //back tren dien thoai ra teacher activity

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void information(final int pos){
        Cursor cursor = database.getDataClass(id_teacher);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == pos){
                Intent intent = new Intent(ClassActivity.this, InformationClassActivity.class);

                intent.putExtra("id",pos);

                String name = cursor.getString(1);
                String code = cursor.getString(2);
                String studytime = cursor.getString(3);
                String schedule = cursor.getString(4);
                String startday = cursor.getString(5);
                String endday = cursor.getString(6);
                int id_teacher = cursor.getInt(7);

                intent.putExtra("name",name);
                intent.putExtra("code",code);
                intent.putExtra("studytime",studytime);
                intent.putExtra("schedule",schedule);
                intent.putExtra("startday",startday);
                intent.putExtra("endday",endday);

                startActivity(intent);
            }
        }
        cursor.close();
    }
    //update
    public  void update(final int id_class){
        Cursor cursor = database.getDataClass(id_teacher);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == id_class){
                Intent intent = new Intent(ClassActivity.this, UpdateClassActivity.class);

                intent.putExtra("id",id_class);

                String name = cursor.getString(1);
                String code = cursor.getString(2);
                String studytime = cursor.getString(3);
                String schedule = cursor.getString(4);
                String startday = cursor.getString(5);
                String endday = cursor.getString(6);
                int id_teacher = cursor.getInt(7);

                intent.putExtra("name",name);
                intent.putExtra("code",code);
                intent.putExtra("studytime",studytime);
                intent.putExtra("schedule",schedule);
                intent.putExtra("startday",startday);
                intent.putExtra("endday",endday);
                intent.putExtra("id_teacher",id_teacher);

                startActivity(intent);
                finish();
            }
        }
        cursor.close();
    }

    //Xoa
    public void delete(final int position){
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogdeleteclass);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesDeleteClass);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteClass);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xoa class trong database
                database.DeleteClass(position);

                //Xoa student
                database.DeleteClassStudent(position);

                //cap nhat lai activity class
                Intent intent = new Intent(ClassActivity.this,ClassActivity.class);
                intent.putExtra("id_teacher",id_teacher);
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
}