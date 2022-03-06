package com.example.pnpedu.academic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pnpedu.R;

public class InformationStudentActivity extends AppCompatActivity {

    TextView txtName,txtGender,txtCode,txtdob,txtnameinfo,txtcodeinfo;
    ImageView imgPicture;
    Button btncancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_student);

        txtnameinfo = findViewById(R.id.txtStudentNameinfo);
        txtcodeinfo = findViewById(R.id.txtStudentCodeinfo);
        txtName=findViewById(R.id.txtStudentNameshape);
        txtCode = findViewById(R.id.txtStudentCodeshape);
        txtGender=findViewById(R.id.txtStudentGendershape);
        txtdob = findViewById(R.id.txtStudentdobshape);
        imgPicture = (ImageView) findViewById(R.id.imageviewInforStudentshape);
        btncancel = findViewById(R.id.btncancelinfo);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        //Nhan du lieu
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        byte[] image = intent.getByteArrayExtra("picture");
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);

        //Gan len textview tuong ung
        txtnameinfo.setText(name);
        txtcodeinfo.setText(code);
        txtName.setText(name);
        txtGender.setText(gender);
        txtCode.setText(code);
        txtdob.setText(birthday);
        imgPicture.setImageBitmap(bitmap);
    }
}