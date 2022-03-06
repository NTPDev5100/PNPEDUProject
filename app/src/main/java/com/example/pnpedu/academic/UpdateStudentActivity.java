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

public class UpdateStudentActivity extends AppCompatActivity {


    EditText edtUpdateName,edtUpdateCode,edtUpdateBirthday;
    RadioButton rbtMale,rbtFemale;
    Button btnUpdateStudent,btnCamUpdate,btnGalleryUpdate;
    ImageView imgPictureUpdate;
    database database;

    int REQUEST_CODE_CAMERA = 100;
    int REQUEST_CODE_GALLERY = 101;
    byte[] image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        edtUpdateBirthday = findViewById(R.id.EditTextStudentBirhtdayUpdate);
        edtUpdateCode = findViewById(R.id.EditTextStudentCodeUpdate);
        edtUpdateName = findViewById(R.id.EditTextStudentNameUpdate);

        rbtFemale = findViewById(R.id.radiobuttonFemaleUpdateStudent);
        rbtMale = findViewById(R.id.radiobuttonMaleUpdateStudent);
        btnUpdateStudent = findViewById(R.id.buttonUpdateStudent);

        imgPictureUpdate = (ImageView) findViewById(R.id.imageviewStudentUpdate);
        btnCamUpdate = findViewById(R.id.buttonCamStudentUpdate);
        btnGalleryUpdate = findViewById(R.id.buttonGalleryStudentUpdate);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        byte[] image = intent.getByteArrayExtra("picture");
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        int id_class = intent.getIntExtra("id_class",0);

        //Gan gia tri len edittext va radiobutton
        edtUpdateName.setText(name);
        edtUpdateCode.setText(code);
        edtUpdateBirthday.setText(birthday);
        if(gender.equals("Male")){
            rbtMale.setChecked(true);
            rbtFemale.setChecked(false);
        }
        else {
            rbtFemale.setChecked(true);
            rbtMale.setChecked(false);
        }
        imgPictureUpdate.setImageBitmap(bitmap);



        btnCamUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        btnGalleryUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
        });
        database = new database(this);

        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id,id_class);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap decodeStream = (Bitmap) data.getExtras().get("data");
            imgPictureUpdate.setImageBitmap(decodeStream);
            image = getBytes(decodeStream);
        }
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                imgPictureUpdate.setImageBitmap(decodeStream);
                image = getBytes(decodeStream);

            } catch (Exception ex) {
                Log.e("ex", ex.getMessage());
            }
        }

    }

    private void DialogUpdate(int id, int id_class) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdatestudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesupdateStudent);
        Button btnNo = dialog.findViewById(R.id.buttonNoupdateStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtUpdateName.getText().toString().trim();
                String code = edtUpdateCode.getText().toString().trim();
                String bithday = edtUpdateBirthday.getText().toString().trim();
                String gender = "";
                //Kiem tra radiobutton true tai dau thi lay gia tri nam hay nu gan vao gender
                if (rbtMale.isChecked()){
                    gender="Male";
                }
                else if (rbtFemale.isChecked()){
                    gender="Female";
                }
                BitmapDrawable drawable = (BitmapDrawable) imgPictureUpdate.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                image = getBytes(bitmap);

                Student student = new Student(name,gender,code,bithday,image);
                    //update
                    database.UpdateStudent(student,id);
                    //Chuyen qua activity student va cap nhat lai danh sach sinh vien
                    Intent intent = new Intent(UpdateStudentActivity.this, StudentActivity.class);
                    //Gui id cua class
                    intent.putExtra("id_class",id_class);
                    startActivity(intent);
                    Toast.makeText(UpdateStudentActivity.this,"more success",Toast.LENGTH_SHORT).show();
                    finish();
                    dialog.dismiss();

            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public static byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100 , stream);
        return stream.toByteArray();
    }
}