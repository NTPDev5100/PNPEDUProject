package com.example.pnpedu.academic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pnpedu.R;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Subject;

public class UpdateSubjectActivity extends AppCompatActivity {

    EditText edtUpdateName,edtUpdateLesson,edtUpdateTime;
    Button btnUpdatesubject;
    database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        edtUpdateLesson = findViewById(R.id.EditTextUpdateSubjeclesson);
        edtUpdateTime =findViewById(R.id.EditTextUpdateSubjectTime);
        edtUpdateName=findViewById(R.id.EditTextUpdateSubjectName);
        btnUpdatesubject = findViewById(R.id.buttonUpdateSubject);


        //Lay du lieu
        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        int lesson = intent.getIntExtra("lesson",0);
        String time = intent.getStringExtra("time");


        edtUpdateName.setText(name);
        edtUpdateLesson.setText(lesson+ "");
        edtUpdateTime.setText(time);

        database = new database(this);

        btnUpdatesubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id);
            }
        });

    }

    private void DialogUpdate(int id) {

        Dialog dialog = new Dialog(this);


        dialog.setContentView(R.layout.dialogupdatesubject);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesupdate);
        Button btnNo = dialog.findViewById(R.id.buttonNoupdate);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectname = edtUpdateName.getText().toString().trim();
                String lesson = edtUpdateLesson.getText().toString().trim();
                String time = edtUpdateTime.getText().toString().trim();

                if(subjectname.equals("") || lesson.equals("")|| time.equals("")){
                    Toast.makeText(UpdateSubjectActivity.this,"Did you enter enough information",Toast.LENGTH_SHORT).show();
                }
                else{
                    Subject subject = updatesubject();

                    database.UpdateSubject(subject,id);
                    //update thanh cong thi qua activity subject
                    Intent intent = new Intent(UpdateSubjectActivity.this, SubjectActivity.class);
                    startActivity(intent);
                    Toast.makeText(UpdateSubjectActivity.this,"more success",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                finish();
            }
        });
        dialog.show();
    }
    //Luu du lieu edittext cap nhat
    private Subject updatesubject(){
        String subjectname = edtUpdateName.getText().toString().trim();
        int lesson = Integer.parseInt(edtUpdateLesson.getText().toString().trim());
        String time = edtUpdateTime.getText().toString().trim();

        Subject subject = new Subject(subjectname,lesson,time);
        return subject;
    }
}