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
import com.example.pnpedu.model.Classmodel;

public class AddClassActivity extends AppCompatActivity {

    Button buttonaddClass;
    EditText edtClassName,edtClassCode,edtStutyTime,edtSchedule,edtStartDay,edtEndDay;
    database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        buttonaddClass = findViewById(R.id.buttonAddClass);
        edtEndDay = findViewById(R.id.EditTextClassEndDay);
        edtStartDay = findViewById(R.id.EditTextClassStartDay);
        edtSchedule = findViewById(R.id.EditTextClassSchedule);
        edtStutyTime = findViewById(R.id.EditTextClassStudyTime);
        edtClassCode = findViewById(R.id.EditTextClassCode);
        edtClassName =findViewById(R.id.EditTextClassName);
        //Lay id teacher
        Intent intent = getIntent();
        int id_teacher = intent.getIntExtra("id_teacher",0);

        database = new database(this);
        buttonaddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(id_teacher);
            }
        });

    }

    private void DialogAdd(int id_teacher) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogaddclass);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesAddClass);
        Button btnNo = dialog.findViewById(R.id.buttonNoAddClass);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtClassName.getText().toString().trim();
                String code = edtClassCode.getText().toString().trim();
                String studytime = edtStutyTime.getText().toString().trim();
                String schedule = edtSchedule.getText().toString().trim();
                String startday = edtStartDay.getText().toString().trim();
                String endday = edtEndDay.getText().toString().trim();

                if(name.equals("") || code.equals("") || studytime.equals("") || schedule.equals("") || startday.equals("") || endday.equals("") ){
                    Toast.makeText(AddClassActivity.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                }
                else {
                    Classmodel classmodel = CreateClass(id_teacher);

                    database.AddClass(classmodel);

                    Intent intent = new Intent(AddClassActivity.this, ClassActivity.class);
                    intent.putExtra("id_teacher",id_teacher);
                    startActivity(intent);
                    Toast.makeText(AddClassActivity.this,"more success", Toast.LENGTH_SHORT).show();
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

    private Classmodel CreateClass(int id_teacher){

        String name = edtClassName.getText().toString().trim();
        String code = edtClassCode.getText().toString().trim();
        String studytime = edtStutyTime.getText().toString().trim();
        String schedule = edtSchedule.getText().toString().trim();
        String startday = edtStartDay.getText().toString().trim();
        String endday = edtEndDay.getText().toString().trim();

        Classmodel classmodel = new Classmodel(name,code,studytime,schedule,startday,endday,id_teacher);
        return classmodel;
    }
}