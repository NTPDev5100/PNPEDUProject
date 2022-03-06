package com.example.pnpedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pnpedu.R;
import com.example.pnpedu.academic.TeacherAllActivity;
import com.example.pnpedu.model.Teacher;

import java.util.ArrayList;

public class adapterteacherall extends BaseAdapter {

    private TeacherAllActivity context;
    private ArrayList<Teacher> ArrayListTeacherAll;

    public adapterteacherall(TeacherAllActivity context, ArrayList<Teacher> arrayListTeacherAll) {
        this.context = context;
        ArrayListTeacherAll = arrayListTeacherAll;
    }

    @Override
    public int getCount() {
        return ArrayListTeacherAll.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListTeacherAll.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listallteacher,null);

        TextView txtName = view.findViewById(R.id.TextViewTeacherNameAll);
        TextView txtCode = view.findViewById(R.id.TextViewTeacherCodeAll);


        Teacher teacher = ArrayListTeacherAll.get(i);
        txtName.setText(teacher.getTeacher_name());
        txtCode.setText(teacher.getTeacher_code());
        return view;
    }
}
