package com.example.pnpedu.academic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pnpedu.R;

public class InformationClassActivity extends AppCompatActivity {

    TextView txtName,txtCode,txtStudyTime,txtSchedule,txtStartday,txtEndday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_class);

        txtName=findViewById(R.id.txtClassName);
        txtCode = findViewById(R.id.txtClassCode);
        txtStudyTime=findViewById(R.id.txtClassStudyTime);
        txtSchedule = findViewById(R.id.txtClassSchedule);
        txtStartday = findViewById(R.id.txtClassStartDay);
        txtEndday = findViewById(R.id.txtClassEndDay);

        //Nhan du lieu
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String code = intent.getStringExtra("code");
        String studytime = intent.getStringExtra("studytime");
        String schedule = intent.getStringExtra("schedule");
        String startday = intent.getStringExtra("startday");
        String endday = intent.getStringExtra("endday");


        //Gan len textview tuong ung
        txtName.setText(name);
        txtCode.setText(code);
        txtStudyTime.setText(studytime);
        txtSchedule.setText(schedule);
        txtStartday.setText(startday);
        txtEndday.setText(endday);

    }
}