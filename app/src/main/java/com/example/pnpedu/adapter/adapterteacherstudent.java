package com.example.pnpedu.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pnpedu.R;
import com.example.pnpedu.model.Student;
import com.example.pnpedu.teacher.TeacherStudentActivity;

import java.util.ArrayList;
import java.util.Locale;

public class adapterteacherstudent extends BaseAdapter {
    private TeacherStudentActivity context;
    private ArrayList<Student> ArrayListStudent;
    private ArrayList<Student> copylist;

    public adapterteacherstudent(TeacherStudentActivity context, ArrayList<Student> arrayListStudent) {
        this.context = context;
        ArrayListStudent = arrayListStudent;
        this.copylist = new ArrayList<Student>();
        this.copylist.addAll(arrayListStudent);
    }

    @Override
    public int getCount() {
        return ArrayListStudent.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListStudent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listteacherstudent,null);

        TextView txtName = view.findViewById(R.id.TextViewStudentNameForTeacher);
        TextView txtCode = view.findViewById(R.id.TextViewStudentCodeForTeacher);
        ImageView imgStudent = (ImageView) view.findViewById(R.id.imagehinhhforteacher);

        Student student = ArrayListStudent.get(i);
        txtName.setText(student.getStudent_name());
        txtCode.setText(student.getStudent_code());
        Bitmap bitmap = BitmapFactory.decodeByteArray(student.getImage(), 0, student.getImage().length);
        imgStudent.setImageBitmap(bitmap);

        int id = student.getId_student();
        /*ImageButton imgbtnAdd = view.findViewById(R.id.gradeadd);
        ImageButton imgbtnUpdate = view.findViewById(R.id.gradeupdate);
        ImageButton imgbtnInfomation = view.findViewById(R.id.gradeinformation);



        imgbtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //context.update(id);
            }
        });

        imgbtnInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //context.information(id);
            }
        });*/
        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        ArrayListStudent.clear();
        if(charText.length()==0){
            ArrayListStudent.addAll(copylist);
        }else {
            for(Student student : copylist){
                if(student.getStudent_name().toLowerCase(Locale.getDefault()).contains(charText)){
                    ArrayListStudent.add(student);
                }
            }
        }
        notifyDataSetChanged();
    }
}
