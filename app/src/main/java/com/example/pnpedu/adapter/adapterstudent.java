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
import com.example.pnpedu.academic.StudentActivity;
import com.example.pnpedu.model.Student;

import java.util.ArrayList;
import java.util.Locale;

public class adapterstudent extends BaseAdapter {
    private StudentActivity context;
    private ArrayList<Student> ArrayListStudent;
    private ArrayList<Student> copylist;

    public adapterstudent(StudentActivity context, ArrayList<Student> arrayListStudent) {
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

        view = inflater.inflate(R.layout.liststudent,null);

        TextView txtName = view.findViewById(R.id.TextViewStudentName);
        TextView txtCode = view.findViewById(R.id.TextViewStudentCode);
        ImageView imgStudent = (ImageView) view.findViewById(R.id.imagehinhh);

        ImageButton imgbtnDelete = view.findViewById(R.id.studentdelete);
        ImageButton imgbtnUpdate = view.findViewById(R.id.studentupdate);
        ImageButton imgbtnInfomation = view.findViewById(R.id.studentinformation);

        Student student = ArrayListStudent.get(i);
        txtName.setText(student.getStudent_name());
        txtCode.setText(student.getStudent_code());
        Bitmap bitmap = BitmapFactory.decodeByteArray(student.getImage(), 0, student.getImage().length);
        imgStudent.setImageBitmap(bitmap);

        int id = student.getId_student();

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
