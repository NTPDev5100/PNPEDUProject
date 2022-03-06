package com.example.pnpedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pnpedu.R;
import com.example.pnpedu.academic.StudentGradeActivity;
import com.example.pnpedu.model.Score;
import com.example.pnpedu.teacher.TeacherStudentGradeActivity;

import java.util.ArrayList;

public class adapterstudentgrade extends BaseAdapter {

    private StudentGradeActivity context;
    private ArrayList<Score> ArrayListScore;

    public adapterstudentgrade(StudentGradeActivity context, ArrayList<Score> arrayListScore) {
        this.context = context;
        ArrayListScore = arrayListScore;
    }

    @Override
    public int getCount() {
        return ArrayListScore.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListScore.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.liststudentgrade,null);

        TextView txtCode = view.findViewById(R.id.txtScoreCodead);
        TextView txtScore1 = view.findViewById(R.id.txtScore1ad);
        TextView txtScore2 = view.findViewById(R.id.txtScore2ad);
        TextView txtScore3 = view.findViewById(R.id.txtScore3ad);
        TextView txtScorefinal = view.findViewById(R.id.txtFinalad);
        TextView txtScoretotal = view.findViewById(R.id.txtTotalad);

        Button btnUpdate = view.findViewById(R.id.btnupdategrade);

        Score score = ArrayListScore.get(i);
        txtCode.setText(score.getScore_code());
        txtScore1.setText(String.valueOf(score.getScore1()));
        txtScore2.setText(String.valueOf(score.getScore2()));
        txtScore3.setText(String.valueOf(score.getScore3()));
        txtScorefinal.setText(String.valueOf(score.getFinal_score()));
        txtScoretotal.setText(String.valueOf(score.getTotal()));

        int id = score.getId_score();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.update(id);
            }
        });

        return view;
    }
}
