package com.example.pnpedu.academic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pnpedu.R;

public class InformationSubjectActivity extends AppCompatActivity {

    TextView tvName,tvLesson,tvTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_subject);

        tvLesson = findViewById(R.id.txtSubjectlesson);
        tvTime = findViewById(R.id.txtSubjecttime);
        tvName=findViewById(R.id.txtSubjectname);

        //Lay du lieu
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int lesson = intent.getIntExtra("lesson",0);
        String time = intent.getStringExtra("time");

        //Gan gia tri len
        tvName.setText(name);
        tvLesson.setText(lesson+"");
        tvTime.setText(time);
    }
}