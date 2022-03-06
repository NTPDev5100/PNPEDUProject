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

public class AddSubjectActivity extends AppCompatActivity {

    Button buttonAddSubject;
    EditText edtSubjectName,edtSubjectLesson,edtSubjectTime;
    database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        buttonAddSubject = findViewById(R.id.buttonAddSubject);
        edtSubjectLesson = findViewById(R.id.EditTextSubjeclesson);
        edtSubjectName = findViewById(R.id.EditTextSubjectName);
        edtSubjectTime = findViewById(R.id.EditTextSubjectTime);

        database = new database(this);

        buttonAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
            }


        });
    }
    private void DialogAdd() {

        //Tạo đối tượng của sổ
        Dialog dialog = new Dialog(this);

        //Nap layout vao dialog
        dialog.setContentView(R.layout.dialogadd);

        //Tat lick ngoai la thoat
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectname = edtSubjectName.getText().toString().trim();
                String lesson= edtSubjectLesson.getText().toString().trim();
                String time = edtSubjectTime.getText().toString().trim();

                //Neu du lieu chua nhap day du
                if(subjectname.equals("") || lesson.equals("") || time.equals("")){
                    Toast.makeText(AddSubjectActivity.this,"Did not enter enough information ",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Gán cho đối tượng subject giá trị được nhập vào
                    Subject subject = CreateSubject();

                    //add trong database
                    database.AddSubjects(subject);
                    //add thanh cong thi chuyen qua giao dien subject
                    Intent intent = new Intent(AddSubjectActivity.this, SubjectActivity.class);
                    startActivity(intent);

                    Toast.makeText(AddSubjectActivity.this,"more success",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        //Nếu không add nữa
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                finish();
            }
        });
        //Show dialog
        dialog.show();
    }

    //Create subject
    private Subject CreateSubject(){
        String subjectname = edtSubjectName.getText().toString().trim();
        int lesson= Integer.parseInt(edtSubjectLesson.getText().toString().trim());
        String time = edtSubjectTime.getText().toString().trim();

        Subject subject = new Subject(subjectname,lesson,time);
        return subject;
    }
}