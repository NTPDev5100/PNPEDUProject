package com.example.pnpedu.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pnpedu.MainActivity;
import com.example.pnpedu.R;
import com.example.pnpedu.academic.HomeActivity;
import com.example.pnpedu.adapter.adapterteacherclass;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Classmodel;

import java.util.ArrayList;

public class TeacherHomeActivity extends AppCompatActivity {

    ListView listViewclass;
    Button logoutteacher;
    ArrayList<Classmodel> ArrayListClass;
    database database;
    com.example.pnpedu.adapter.adapterteacherclass adapterteacherclass;
    int id_teacher = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        logoutteacher = findViewById(R.id.logoutteacher);
        logoutteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherHomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(TeacherHomeActivity.this,"Succesfully",Toast.LENGTH_SHORT).show();
            }
        });
        listViewclass = findViewById(R.id.listviewclassforteacher);
        Intent intent = getIntent();
        id_teacher = intent.getIntExtra("id_teacher",0);

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
        adapterteacherclass = new adapterteacherclass(TeacherHomeActivity.this,ArrayListClass);
        listViewclass.setAdapter(adapterteacherclass);
        cursor.moveToFirst();
        cursor.close();

        listViewclass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(TeacherHomeActivity.this,TeacherStudentActivity.class);
                int id_class = ArrayListClass.get(i).getId_class();
                intent.putExtra("id_class",id_class);
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