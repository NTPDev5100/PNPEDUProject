package com.example.pnpedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pnpedu.R;
import com.example.pnpedu.academic.StudentActivity;
import com.example.pnpedu.model.Score;
import com.example.pnpedu.model.Student;
import com.example.pnpedu.teacher.TeacherStudentGradeActivity;

import java.util.ArrayList;
import java.util.Locale;

public class adapterteacherstudentgrade extends BaseAdapter {

    private TeacherStudentGradeActivity context;
    private ArrayList<Score> ArrayListScore;


    public adapterteacherstudentgrade(TeacherStudentGradeActivity context, ArrayList<Score>  arrayListScore) {
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

        view = inflater.inflate(R.layout.listteacherstudentgrade,null);


        TextView txtCode = view.findViewById(R.id.txtScoreCode);
        TextView txtScore1 = view.findViewById(R.id.txtScore1);
        TextView txtScore2 = view.findViewById(R.id.txtScore2);
        TextView txtScore3 = view.findViewById(R.id.txtScore3);
        TextView txtScorefinal = view.findViewById(R.id.txtFinal);
        TextView txtScoretotal = view.findViewById(R.id.txtTotal);

        ImageButton imgbtnDelete = view.findViewById(R.id.scoredelete);
        ImageButton imgbtnUpdate = view.findViewById(R.id.scoreupdate);

        Score score = ArrayListScore.get(i);
        txtCode.setText(score.getScore_code());
        txtScore1.setText(String.valueOf(score.getScore1()));
        txtScore2.setText(String.valueOf(score.getScore2()));
        txtScore3.setText(String.valueOf(score.getScore3()));
        txtScorefinal.setText(String.valueOf(score.getFinal_score()));
        txtScoretotal.setText(String.valueOf(score.getTotal()));

        int id = score.getId_score();

       imgbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(id);
            }
        });

        imgbtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               context.update(id);
            }
        });



        return view;
    }
}
