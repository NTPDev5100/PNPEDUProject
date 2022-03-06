package com.example.pnpedu.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pnpedu.R;
import com.example.pnpedu.academic.AddStudentActivity;
import com.example.pnpedu.academic.StudentActivity;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Score;

public class AddGradeActivity extends AppCompatActivity {

    EditText edtAddScoreCode, edtAddScore1, edtAddScore2, edtAddScore3, edtAddFinal, edtAddTotal;
    Button btnAddScore;
    database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);

        edtAddScoreCode=findViewById(R.id.EditTextAddScoreCode);
        edtAddScore1=findViewById(R.id.EditTextAddScore1);
        edtAddScore2 = findViewById(R.id.EditTextAddScore2);
        edtAddScore3=findViewById(R.id.EditTextAddScore3);
        edtAddFinal = findViewById(R.id.EditTextAddFinal);
        edtAddTotal = findViewById(R.id.EditTextAddTotal);

        btnAddScore = findViewById(R.id.buttonAddGrade);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtAddScore1.getText().toString().equals("") &&
                        !edtAddScore2.getText().toString().equals("") &&
                        !edtAddScore3.getText().toString().equals("") &&
                        !edtAddFinal.getText().toString().equals("")) {
                    float score1 = Float.parseFloat(edtAddScore1.getText().toString());
                    float score2 = Float.parseFloat(edtAddScore2.getText().toString());
                    float score3 = Float.parseFloat(edtAddScore3.getText().toString());
                    float final_score = Float.parseFloat(edtAddFinal.getText().toString());
                    edtAddTotal.setText(String.valueOf((score1 + score2 + score3 + final_score + final_score) / 5));
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edtAddScore1.addTextChangedListener(textWatcher);
        edtAddScore2.addTextChangedListener(textWatcher);
        edtAddScore3.addTextChangedListener(textWatcher);
        edtAddFinal.addTextChangedListener(textWatcher);

        Intent intent = getIntent();
        int id_student = intent.getIntExtra("id_student",0);

        database = new database(this);
        btnAddScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(id_student);
            }
        });
    }

    private void DialogAdd(int id_student) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogaddgrade);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesAddScore);
        Button btnNo = dialog.findViewById(R.id.buttonNoAddScore);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String score_code = edtAddScoreCode.getText().toString();
                float score1 = Float.parseFloat(edtAddScore1.getText().toString().trim());
                float score2 = Float.parseFloat(edtAddScore2.getText().toString().trim());
                float score3 = Float.parseFloat(edtAddScore3.getText().toString().trim());
                float final_score = Float.parseFloat(edtAddFinal.getText().toString().trim());
                float total_score = Float.parseFloat(edtAddTotal.getText().toString().trim());

                Score score = new Score(score_code,score1,score2,score3,final_score,total_score,id_student);

                database.AddScore(score);

                Intent intent = new Intent(AddGradeActivity.this, TeacherStudentGradeActivity.class);
                intent.putExtra("id_student",id_student);
                startActivity(intent);
                Toast.makeText(AddGradeActivity.this,"more success", Toast.LENGTH_SHORT).show();
                finish();
                dialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                finish();
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}