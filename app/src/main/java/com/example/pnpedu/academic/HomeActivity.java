package com.example.pnpedu.academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnpedu.MainActivity;
import com.example.pnpedu.R;
import com.example.pnpedu.database.database;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    int student_count=0;
    private LinearLayout linearLayoutSubject,linearLayoutStudent,linearLayoutTeacher;
    TextView tvshowcountstudent,tvshowcountsubject,tvshowcountclass,tvshowcountteacher;
    database database;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvhello = findViewById(R.id.textviewhello);

        Bundle bd = getIntent().getExtras();
        if (bd!=null){
            String ten = bd.getString("Name_Admin");
            tvhello.setText(ten);
        }

        linearLayoutSubject = findViewById(R.id.linearSubject);
        linearLayoutSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SubjectActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutStudent = findViewById(R.id.linearStudent);
        linearLayoutStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StudentAllActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutTeacher = findViewById(R.id.linearTeacher);
        linearLayoutTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TeacherAllActivity.class);
                startActivity(intent);
            }
        });



        final DrawerLayout drawerLayout =findViewById(R.id.drawerLayout);
        findViewById(R.id.imagemenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        abdt = new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(abdt);
        abdt.syncState();

        NavigationView nav_view = (NavigationView)findViewById(R.id.navigationView);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menuAboutUS){
                    Intent intent = new Intent(HomeActivity.this,Aboutus.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(HomeActivity.this,"Succesfully",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


        database = new database(this);
        tvshowcountstudent=(TextView) findViewById(R.id.showcountstudent);
        tvshowcountteacher=(TextView) findViewById(R.id.teachershow);
        tvshowcountclass=(TextView) findViewById(R.id.classshow);
        tvshowcountsubject=(TextView) findViewById(R.id.subjectshow);

        tvshowcountstudent.setText(String.valueOf(database.getStudentCount()));
        tvshowcountteacher.setText(String.valueOf(database.getTeacherCount()));
        tvshowcountclass.setText(String.valueOf(database.getClassCount()));
        tvshowcountsubject.setText(String.valueOf(database.getSubjectCount()));

       

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    //Neu click back o dien thoai se tro ve home activity
    @Override
    public void onBackPressed() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogbackathome);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesExit);
        Button btnNo = dialog.findViewById(R.id.buttonNoExit);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finishAffinity();
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
}