package com.example.pnpedu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pnpedu.academic.ClassActivity;
import com.example.pnpedu.R;
import com.example.pnpedu.model.Classmodel;

import java.util.ArrayList;

public class adapterclass extends BaseAdapter {

    private ClassActivity context;
    private ArrayList<Classmodel> ArrayListClass;


    public adapterclass(ClassActivity context, ArrayList<Classmodel> arrayListClass) {
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

        view = inflater.inflate(R.layout.listclass,null);

        TextView txtName = view.findViewById(R.id.TextViewClassName);
        TextView txtCode = view.findViewById(R.id.TextViewClassCode);

        ImageButton imgbtnDelete = view.findViewById(R.id.classdelete);
        ImageButton imgbtnUpdate = view.findViewById(R.id.classupdate);
        ImageButton imgbtnInfomation = view.findViewById(R.id.classinformation);

        Classmodel classmodel = ArrayListClass.get(i);

        txtName.setText(classmodel.getClass_name());
        txtCode.setText(classmodel.getClass_code());

        int id = classmodel.getId_class();

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
}
