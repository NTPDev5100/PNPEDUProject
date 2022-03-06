package com.example.pnpedu.academic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pnpedu.R;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Teacher;

public class AddTeacherActivity extends AppCompatActivity {

    Button buttonAddTeacher;
    EditText edtTeacherName,edtTeacherCode,edtDOB,edtEmail,edtPass;
    RadioButton rbtMale,rbtFemale;
    database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        buttonAddTeacher =findViewById(R.id.buttonAddTeacher);
        edtPass = findViewById(R.id.EditTextTeacherPassword);
        edtEmail = findViewById(R.id.EditTextTeacherEmail);
        edtDOB = findViewById(R.id.EditTextTeacherBirhtday);
        edtTeacherCode = findViewById(R.id.EditTextTeacherCode);
        edtTeacherName = findViewById(R.id.EditTextTeacherName);

        rbtFemale = findViewById(R.id.radiobuttonFemale);
        rbtMale = findViewById(R.id.radiobuttonMale);

        //Lay id subject
        Intent intent = getIntent();
        int id_subject = intent.getIntExtra("id_subject",0);

        database = new database(this);
        buttonAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(id_subject);
            }
        });
    }

    //dialog add
    private void DialogAdd(int id_subject) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogaddteacher);
        dialog.setCanceledOnTouchOutside(false);


        Button btnYes = dialog.findViewById(R.id.buttonYesAddTeacher);
        Button btnNo = dialog.findViewById(R.id.buttonNoAddTeacher);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtTeacherName.getText().toString().trim();
                String code = edtTeacherCode.getText().toString().trim();
                String bithday = edtDOB.getText().toString().trim();
                String gender = "";

                //Kiem tra radiobutton true tai dau thi lay gia tri nam hay nu gan vao gender
                if (rbtMale.isChecked()){
                    gender="Male";
                }
                else if (rbtFemale.isChecked()){
                    gender="Female";
                }

                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();

                if(name.equals("") || code.equals("") || bithday.equals("") || email.equals("") || pass.equals("") || gender.equals("") ){
                    Toast.makeText(AddTeacherActivity.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                }
                else {
                    Teacher teacher = CreateTeacher(id_subject);

                    database.AddTeacher(teacher);

                    Intent intent = new Intent(AddTeacherActivity.this, TeacherActivity.class);
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);

                    Toast.makeText(AddTeacherActivity.this,"more success", Toast.LENGTH_SHORT).show();
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
    private Teacher CreateTeacher(int id_subject){

        String name = edtTeacherName.getText().toString().trim();
        String code = edtTeacherCode.getText().toString().trim();
        String bithday = edtDOB.getText().toString().trim();
        String gender = "";

        //Kiem tra radiobutton true tai dau thi lay gia tri nam hay nu gan vao gender
        if (rbtMale.isChecked()){
            gender="Male";
        }
        else if (rbtFemale.isChecked()){
            gender="Female";
        }

        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();

        Teacher teacher = new Teacher(name,gender,code,bithday,email,pass,id_subject);
        return teacher;
    }


}