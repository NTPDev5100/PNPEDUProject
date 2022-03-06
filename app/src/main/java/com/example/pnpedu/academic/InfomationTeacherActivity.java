package com.example.pnpedu.academic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pnpedu.R;

public class InfomationTeacherActivity extends AppCompatActivity {

    TextView txtName,txtGender,txtCode,txtdob,txtemail,txtpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation_teacher);

        txtName=findViewById(R.id.txtTeacherName);
        txtCode = findViewById(R.id.txtTeacherCode);
        txtGender=findViewById(R.id.txtTeacherGender);
        txtdob = findViewById(R.id.txtTeacherdob);
        txtemail = findViewById(R.id.txtTeacherEmail);
        txtpass = findViewById(R.id.txtTeacherPass);

        //Nhan du lieu
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        String email = intent.getStringExtra("email");
        String pass = intent.getStringExtra("password");


        //Gan len textview tuong ung
        txtName.setText(name);
        txtGender.setText(gender);
        txtCode.setText(code);
        txtdob.setText(birthday);
        txtemail.setText(email);
        txtpass.setText(pass);


    }
}