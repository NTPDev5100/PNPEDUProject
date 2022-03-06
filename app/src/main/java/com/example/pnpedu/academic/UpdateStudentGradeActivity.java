package com.example.pnpedu.academic;

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
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Score;

public class UpdateStudentGradeActivity extends AppCompatActivity {
    EditText edtUpdateScoreCode, edtUpdateScore1, edtUpdateScore2, edtUpdateScore3, edtUpdateFinal, edtUpdateTotal;
    Button btnUpdateScore;
    com.example.pnpedu.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_studen_grade);

        edtUpdateScoreCode=findViewById(R.id.EditTextUpdateScoreCodead);
        edtUpdateScore1=findViewById(R.id.EditTextUpdateScore1ad);
        edtUpdateScore2 = findViewById(R.id.EditTextUpdateScore2ad);
        edtUpdateScore3=findViewById(R.id.EditTextUpdateScore3ad);
        edtUpdateFinal = findViewById(R.id.EditTextUpdateFinalad);
        edtUpdateTotal = findViewById(R.id.EditTextUpdateTotalad);

        btnUpdateScore = findViewById(R.id.buttonUpdateGradead);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(     !edtUpdateScore1.getText().toString().equals("") &&
                        !edtUpdateScore2.getText().toString().equals("") &&
                        !edtUpdateScore3.getText().toString().equals("") &&
                        !edtUpdateFinal.getText().toString().equals("")) {
                    float score1 = Float.parseFloat(edtUpdateScore1.getText().toString());
                    float score2 = Float.parseFloat(edtUpdateScore2.getText().toString());
                    float score3 = Float.parseFloat(edtUpdateScore3.getText().toString());
                    float final_score = Float.parseFloat(edtUpdateFinal.getText().toString());
                    edtUpdateTotal.setText(String.valueOf((score1 + score2 + score3 + final_score + final_score) / 5));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edtUpdateScore1.addTextChangedListener(textWatcher);
        edtUpdateScore2.addTextChangedListener(textWatcher);
        edtUpdateScore3.addTextChangedListener(textWatcher);
        edtUpdateFinal.addTextChangedListener(textWatcher);

        Intent intent = getIntent();
        int id =intent.getIntExtra("id",0);
        String score_code = intent.getStringExtra("score_code");
        float score1 = intent.getFloatExtra("score1",0);
        float score2 = intent.getFloatExtra("score2",0);
        float score3 = intent.getFloatExtra("score3",0);
        float finalscore = intent.getFloatExtra("finalscore",0);
        float totalscore = intent.getFloatExtra("totalscore",0);
        int id_student =intent.getIntExtra("id_student",0);

        edtUpdateScoreCode.setText(score_code);
        edtUpdateScore1.setText(String.valueOf(score1));
        edtUpdateScore2.setText(String.valueOf(score2));
        edtUpdateScore3.setText(String.valueOf(score3));
        edtUpdateFinal.setText(String.valueOf(finalscore));
        edtUpdateTotal.setText(String.valueOf(totalscore));
        database = new database(this);

        btnUpdateScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id,id_student);
            }
        });

    }
    private void DialogUpdate(int id, int id_student) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdategrade);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesupdateGrade);
        Button btnNo = dialog.findViewById(R.id.buttonNoupdateGrade);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String score_code = edtUpdateScoreCode.getText().toString();
                float score1 = Float.parseFloat(edtUpdateScore1.getText().toString().trim());
                float score2 = Float.parseFloat(edtUpdateScore2.getText().toString().trim());
                float score3 = Float.parseFloat(edtUpdateScore3.getText().toString().trim());
                float final_score = Float.parseFloat(edtUpdateFinal.getText().toString().trim());
                float total_score = Float.parseFloat(edtUpdateTotal.getText().toString().trim());

                Score score = new Score(score_code,score1,score2,score3,final_score,total_score);
                //update
                database.UpdateScore(score,id);
                //Chuyen qua activity student va cap nhat lai danh sach sinh vien
                Intent intent = new Intent(UpdateStudentGradeActivity.this, StudentGradeActivity.class);
                //Gui id cua student
                intent.putExtra("id_student",id_student);
                startActivity(intent);
                Toast.makeText(UpdateStudentGradeActivity.this,"more success",Toast.LENGTH_SHORT).show();
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