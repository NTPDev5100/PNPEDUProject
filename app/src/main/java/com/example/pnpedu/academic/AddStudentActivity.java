package com.example.pnpedu.academic;


import androidx.appcompat.app.AppCompatActivity;



import android.app.Dialog;

import android.content.Intent;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pnpedu.R;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Student;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class AddStudentActivity extends AppCompatActivity {


    Button buttonAddStudent,btnCam,btnGallery;
    ImageView imgPicture;
    EditText edtStudentName,edtStudentCode,edtDOB;
    RadioButton rbtMale,rbtFemale;
    database database;

    int REQUEST_CODE_CAMERA = 100;
    int REQUEST_CODE_GALLERY = 101;
    byte[] image = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        buttonAddStudent =findViewById(R.id.buttonAddStudent);
        edtDOB = findViewById(R.id.EditTextStudentBirhtday);
        edtStudentCode = findViewById(R.id.EditTextStudentCode);
        edtStudentName = findViewById(R.id.EditTextStudentName);

        rbtFemale = findViewById(R.id.radiobuttonFemaleStudent);
        rbtMale = findViewById(R.id.radiobuttonMaleStudent);


        imgPicture = (ImageView) findViewById(R.id.imageviewStudent);
        btnCam = findViewById(R.id.buttonCamStudent);
        btnGallery = findViewById(R.id.buttonGalleryStudent);

        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
        });


        //Lay id class
        Intent intent = getIntent();
        int id_class = intent.getIntExtra("id_class",0);

        database = new database(this);
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(id_class);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap decodeStream = (Bitmap) data.getExtras().get("data");
            imgPicture.setImageBitmap(decodeStream);
            image = getBytes(decodeStream);
        }
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                imgPicture.setImageBitmap(decodeStream);
                image = getBytes(decodeStream);

            } catch (Exception ex) {
                Log.e("ex", ex.getMessage());
            }
        }

    }

    private void DialogAdd(int id_class) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogaddstudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesAddStudent);
        Button btnNo = dialog.findViewById(R.id.buttonNoAddStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtStudentName.getText().toString().trim();
                String code = edtStudentCode.getText().toString().trim();
                String bithday = edtDOB.getText().toString().trim();
                String gender = "";
                //Kiem tra radiobutton true tai dau thi lay gia tri nam hay nu gan vao gender
                if (rbtMale.isChecked()){
                    gender="Male";
                }
                else if (rbtFemale.isChecked()){
                    gender="Female";
                }
                BitmapDrawable drawable = (BitmapDrawable) imgPicture.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                image = getBytes(bitmap);

                Student student = new Student(name,gender,code,bithday,image,id_class);

                database.AddStudent(student);
                Intent intent = new Intent(AddStudentActivity.this, StudentActivity.class);
                intent.putExtra("id_class",id_class);
                startActivity(intent);
                Toast.makeText(AddStudentActivity.this,"more success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();
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



    public static byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100 , stream);
        return stream.toByteArray();
    }

}



