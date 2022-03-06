package com.example.pnpedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pnpedu.R;
import com.example.pnpedu.teacher.TeacherHomeActivity;
import com.example.pnpedu.model.Classmodel;

import java.util.ArrayList;

public class adapterteacherclass extends BaseAdapter {

    private TeacherHomeActivity context;
    private ArrayList<Classmodel> ArrayListClass;

    public adapterteacherclass(TeacherHomeActivity context, ArrayList<Classmodel> arrayListClass) {
        this.context = context;
        ArrayListClass = arrayListClass;
    }
    @Override
    public int getCount() {
        return ArrayListClass.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListClass.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listteacherclass,null);
        TextView txtName = view.findViewById(R.id.TextViewClassNameForTeacher);
        TextView txtCode = view.findViewById(R.id.TextViewClassCodeForTeacher);


        Classmodel classmodel = ArrayListClass.get(i);

        txtName.setText(classmodel.getClass_name());
        txtCode.setText(classmodel.getClass_code());

        int id = classmodel.getId_class();

        return view;
    }
}
