package com.example.pnpedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pnpedu.R;
import com.example.pnpedu.academic.TeacherActivity;
import com.example.pnpedu.model.Teacher;

import java.util.ArrayList;
import java.util.Locale;

public class adapterteacher extends BaseAdapter {

    private TeacherActivity context;
    private ArrayList<Teacher> ArrayListTeacher;
    private  ArrayList<Teacher> copylist;

    public adapterteacher(TeacherActivity context, ArrayList<Teacher> arrayListTeacher) {
        this.context = context;
        ArrayListTeacher = arrayListTeacher;

        this.copylist = new ArrayList<Teacher>();
        this.copylist.addAll(arrayListTeacher);

    }


    @Override
    public int getCount() {
        return ArrayListTeacher.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListTeacher.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listteacher,null);

        TextView txtName = view.findViewById(R.id.TextVewTeacherName);
        TextView txtCode = view.findViewById(R.id.TextViewTeacherCode);

        ImageButton imgbtnDelete = view.findViewById(R.id.teacherdelete);
        ImageButton imgbtnUpdate = view.findViewById(R.id.teacherupdate);
        ImageButton imgbtnInfomation = view.findViewById(R.id.teacherinformation);

        Teacher teacher = ArrayListTeacher.get(i);

        txtName.setText(teacher.getTeacher_name());
        txtCode.setText(teacher.getTeacher_code());

        int id = teacher.getId_teacher();

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

        imgbtnInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });


        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        ArrayListTeacher.clear();
        if(charText.length()==0){
            ArrayListTeacher.addAll(copylist);
        }else {
            for(Teacher teacher : copylist){
                if(teacher.getTeacher_name().toLowerCase(Locale.getDefault()).contains(charText)){
                    ArrayListTeacher.add(teacher);
                }
            }
        }
        notifyDataSetChanged();
    }
}
