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

public class UpdateTeacherActivity extends AppCompatActivity {


    EditText edtUpdateName,edtUpdateCode,edtUpdateBirthday,edtUpdateEmail,edtUpdatePassword;
    RadioButton rbtMale,rbtFemale;
    Button btnUpdateTeacher;
    database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher);

        edtUpdatePassword = findViewById(R.id.EditTextTeacherPasswordUpdate);
        edtUpdateEmail = findViewById(R.id.EditTextTeacherEmailUpdate);
        edtUpdateBirthday = findViewById(R.id.EditTextTeacherBirhtdayUpdate);
        edtUpdateCode = findViewById(R.id.EditTextTeacherCodeUpdate);
        edtUpdateName = findViewById(R.id.EditTextTeacherNameUpdate);

        rbtFemale = findViewById(R.id.radiobuttonFemaleUpdate);
        rbtMale = findViewById(R.id.radiobuttonMaleUpdate);
        btnUpdateTeacher = findViewById(R.id.buttonUpdateTeacher);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        String email = intent.getStringExtra("email");
        String pass = intent.getStringExtra("password");
        int id_subject = intent.getIntExtra("id_subject",0);

        //Gan gia tri len edittext va radiobutton
        edtUpdateName.setText(name);
        edtUpdateCode.setText(code);
        edtUpdateBirthday.setText(birthday);
        edtUpdateEmail.setText(email);
        edtUpdatePassword.setText(pass);

        if(gender.equals("Male")){
            rbtMale.setChecked(true);
            rbtFemale.setChecked(false);
        }
        else {
            rbtFemale.setChecked(true);
            rbtMale.setChecked(false);
        }

        database = new database(this);

        btnUpdateTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id,id_subject);
            }
        });
    }

    private void DialogUpdate(int id,int id_subject) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogupdateteacher);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesupdateTeacher);
        Button btnNo = dialog.findViewById(R.id.buttonNoupdateTeacher);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtUpdateName.getText().toString().trim();
                String code = edtUpdateCode.getText().toString().trim();
                String birthday = edtUpdateBirthday.getText().toString().trim();
                String email = edtUpdateEmail.getText().toString().trim();
                String pass = edtUpdatePassword.getText().toString().trim();

                Teacher teacher = createteacher();
                if(name.equals("") || code.equals("") || birthday.equals("") || email.equals("") || pass.equals("")){
                    Toast.makeText(UpdateTeacherActivity.this,"Did not enter enough information", Toast.LENGTH_SHORT).show();
                }
                else {

                    //update
                    database.UpdateTeacher(teacher,id);
                    //Chuyen qua activity teacher va cap nhat lai danh sach teacher
                    Intent intent = new Intent(UpdateTeacherActivity.this, TeacherActivity.class);
                    //Gui id cua subject
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);
                    Toast.makeText(UpdateTeacherActivity.this,"more success",Toast.LENGTH_SHORT).show();
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

    //Create teacher
    private Teacher createteacher(){
        String name = edtUpdateName.getText().toString().trim();
        String code = edtUpdateCode.getText().toString().trim();
        String birthday = edtUpdateBirthday.getText().toString().trim();
        String email = edtUpdateEmail.getText().toString().trim();
        String pass = edtUpdatePassword.getText().toString().trim();
        String gender="";
        if(rbtMale.isChecked()){
            gender="Male";
        }else {
            gender="Female";
        }

        Teacher teacher = new Teacher(name,gender,code,birthday,email,pass);
        return teacher;
    }
}