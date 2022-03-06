package com.example.pnpedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pnpedu.R;
import com.example.pnpedu.academic.SubjectActivity;
import com.example.pnpedu.model.Subject;

import java.util.ArrayList;

public class adaptersubject extends BaseAdapter {


    private SubjectActivity context;

    private ArrayList<Subject> ArrayListSubject;

    public adaptersubject(SubjectActivity context, ArrayList<Subject> arrayListSubject) {
        this.context = context;
        ArrayListSubject = arrayListSubject;
    }

    @Override
    public int getCount() {
        return ArrayListSubject.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListSubject.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listsubject,null);

        TextView TextVewSubjectName = view.findViewById(R.id.TextVewSubjectName);
        TextView TextViewLessons = view.findViewById(R.id.TextViewLessons);
        ImageButton imageDelete = view.findViewById(R.id.subjectdelete);
        ImageButton imageInformation = view.findViewById(R.id.subjectinformation);
        ImageButton imageUpdate = view.findViewById(R.id.subjectupdate);

        Subject subject = ArrayListSubject.get(i);

        TextViewLessons.setText(subject.getNumber_of_lessons()+"");
        TextVewSubjectName.setText(subject.getSubject_name());

        int id = subject.getId();

        //Click thong tin
        imageInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });

        //Click xoa
        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goi toi delete()
                context.delete(id);
            }
        });

        //Click update
        imageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.update(id);
            }
        });

        return view;
    }
}
