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

public class UpdateClassActivity extends AppCompatActivity {


    EditText edtUpdateName,edtUpdateCode,edtUpdateStudyTime,edtUpdateSchedule,edtUpdateStartDay,edtUpdateEndDay;
    Button btnUpdateClass;
    database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_class);


        edtUpdateEndDay = findViewById(R.id.EditTextUpdateClassEndday);
        edtUpdateStartDay = findViewById(R.id.EditTextUpdateClassStartday);
        edtUpdateSchedule = findViewById(R.id.EditTextUpdateClassSchedule);
        edtUpdateStudyTime = findViewById(R.id.EditTextUpdateClassStudyTime);
        edtUpdateCode = findViewById(R.id.EditTextUpdateClassCode);
        edtUpdateName = findViewById(R.id.EditTextUpdateClassName);

        btnUpdateClass = findViewById(R.id.buttonUpdateClass);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        String code = intent.getStringExtra("code");
        String studytime = intent.getStringExtra("studytime");
        String schedule = intent.getStringExtra("schedule");
        String startday = intent.getStringExtra("startday");
        String endday = intent.getStringExtra("endday");
        int id_teacher = intent.getIntExtra("id_teacher",0);

        //Gan gia tri len edittext
        edtUpdateName.setText(name);
        edtUpdateCode.setText(code);
        edtUpdateStudyTime.setText(studytime);
        edtUpdateSchedule.setText(schedule);
        edtUpdateStartDay.setText(startday);
        edtUpdateEndDay.setText(endday);

        database = new database(this);

        btnUpdateClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id,id_teacher);
            }
        });



    }

    private void DialogUpdate(int id,int id_teacher) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogupdateclass);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesupdateClass);
        Button btnNo = dialog.findViewById(R.id.buttonNoupdateClass);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtUpdateName.getText().toString().trim();
                String code = edtUpdateCode.getText().toString().trim();
                String studytime = edtUpdateStudyTime.getText().toString().trim();
                String schedule = edtUpdateSchedule.getText().toString().trim();
                String startday = edtUpdateStartDay.getText().toString().trim();
                String endday = edtUpdateEndDay.getText().toString().trim();

                Classmodel classmodel = createclass();
                if(name.equals("") || code.equals("") || studytime.equals("") || schedule.equals("") || startday.equals("") || endday.equals("")){
                    Toast.makeText(UpdateClassActivity.this,"Did not enter enough information", Toast.LENGTH_SHORT).show();
                }
                else {
                    //update
                    database.UpdateClass(classmodel,id);
                    //Chuyen qua activity class va cap nhat lai danh sach class
                    Intent intent = new Intent(UpdateClassActivity.this, ClassActivity.class);
                    //Gui id cua teacher
                    intent.putExtra("id_teacher",id_teacher);
                    startActivity(intent);
                    Toast.makeText(UpdateClassActivity.this,"more success",Toast.LENGTH_SHORT).show();
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

    private Classmodel createclass() {
        String name = edtUpdateName.getText().toString().trim();
        String code = edtUpdateCode.getText().toString().trim();
        String studytime = edtUpdateStudyTime.getText().toString().trim();
        String schedule = edtUpdateSchedule.getText().toString().trim();
        String startday = edtUpdateStartDay.getText().toString().trim();
        String endday = edtUpdateEndDay.getText().toString().trim();

        Classmodel classmodel = new Classmodel(name,code,studytime,schedule,startday,endday);
        return classmodel;
    }
}