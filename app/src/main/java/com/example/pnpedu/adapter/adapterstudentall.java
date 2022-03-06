package com.example.pnpedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pnpedu.R;
import com.example.pnpedu.academic.StudentAllActivity;
import com.example.pnpedu.model.Student;

import java.util.ArrayList;
import java.util.Locale;

public class adapterstudentall extends BaseAdapter {
    private StudentAllActivity context;
    private ArrayList<Student> ArrayListStudent;
    private ArrayList<Student> copylist;


    public adapterstudentall(StudentAllActivity context, ArrayList<Student> arrayListStudent) {
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

        view = inflater.inflate(R.layout.listallstudent,null);

        TextView txtName = view.findViewById(R.id.TextViewStudentNameAll);
        TextView txtCode = view.findViewById(R.id.TextViewStudentCodeAll);


        Student student = ArrayListStudent.get(i);
        txtName.setText(student.getStudent_name());
        txtCode.setText(student.getStudent_code());

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
