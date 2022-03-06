package com.example.pnpedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnpedu.academic.HomeActivity;
import com.example.pnpedu.database.database;
import com.example.pnpedu.model.Admin;
import com.example.pnpedu.model.Teacher;
import com.example.pnpedu.teacher.TeacherHomeActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    private Button btnloginmain,btnloginteachemain,btnloginshape,btnloginshapeteacher;
    private  EditText edtUsernameAd,edtPasswordAd,edtEmailTeacher,edtPasswordTeacher,edtIDTeacher,usernameregister,passwordregister,repasswordregister;
    private LinearLayout linearLayout;
    private TextView tvRegister;
    database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Palette for activity main
        btnloginmain = findViewById(R.id.login);
        btnloginteachemain=findViewById(R.id.loginteacher);


        //get the bottom sheet view
        linearLayout=findViewById(R.id.bottom_sheet_layout);

        //init the bottom sheet view
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);


        btnloginmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new CustomBottomSheetDialogFragmentLogin().show(getSupportFragmentManager(),"Dialog");
                clickOpenBottomSheetDialogLogin();
            }
        });

        btnloginteachemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new CustomBottomSheetDialogFragmentRegister().show(getSupportFragmentManager(),"Dialog");
                clickOpenBottomSheetDialogLoginTeacher();
            }
        });


   }

   private void clickOpenBottomSheetDialogLogin(){
        View viewDialog = getLayoutInflater().inflate(R.layout.login_bottom_sheet,null);

       BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
       bottomSheetDialog.setContentView(viewDialog);
       bottomSheetDialog.show();
       bottomSheetDialog.setCancelable(true);

       Button btnCancel = viewDialog.findViewById(R.id.cancel);
       btnCancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bottomSheetDialog.dismiss();
           }
       });

       tvRegister = viewDialog.findViewById(R.id.tvregister);
       tvRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               clickOpenBottomSheetDialogRegister();
               bottomSheetDialog.dismiss();
           }
       });

       edtUsernameAd = viewDialog.findViewById(R.id.username_login);
       edtPasswordAd = viewDialog.findViewById(R.id.password_login);
       btnloginshape = viewDialog.findViewById(R.id.btnlogin_login);
       btnloginshape.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             clickLoginAdmin();
             bottomSheetDialog.dismiss();
           }
       });
   }

   private void clickLoginAdmin(){
        String strUsername = edtUsernameAd.getText().toString().trim();
        String strPassword = edtPasswordAd.getText().toString().trim();
        db = new database(this);
        Admin admin = new Admin(strUsername,strPassword);
        int loginadmin = admin.isValidl();
       if(loginadmin ==0)
       {
           Toast.makeText(MainActivity.this,"Please enter Username",Toast.LENGTH_SHORT).show();
       } else if (loginadmin ==1){
           Toast.makeText(MainActivity.this,"Please enter a password",Toast.LENGTH_SHORT).show();
       } else if (loginadmin ==2){
           Toast.makeText(MainActivity.this,"Password should be more the 6 character",Toast.LENGTH_SHORT).show();
       }
       else{
           Boolean checkuserpass = db.checkusernamepassword(strUsername,strPassword);
           if (checkuserpass==true)
           {
               Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
               intent.putExtra("Name_Admin",edtUsernameAd.getText().toString().trim());
               startActivity(intent);
               finish();
           }
           else {
               Toast.makeText(MainActivity.this,"Username or password invalid",Toast.LENGTH_SHORT).show();
           }
       }
   }

    private void clickOpenBottomSheetDialogLoginTeacher(){
        View viewDialog = getLayoutInflater().inflate(R.layout.loginteacher_bottom_sheet,null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(true);

        Button btnCancel = viewDialog.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });


        edtEmailTeacher = viewDialog.findViewById(R.id.username_login_teacher);
         edtPasswordTeacher = viewDialog.findViewById(R.id.password_login_teacher);
         edtIDTeacher = viewDialog.findViewById(R.id.id_login_teacher);
        btnloginshapeteacher = viewDialog.findViewById(R.id.btnlogin_login_teacher);
        db = new database(this);
        btnloginshapeteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLoginTeacher();
            }
        });
    }



    private void clickLoginTeacher(){
        String strEmail = edtEmailTeacher.getText().toString().trim();
        String strPassword = edtPasswordTeacher.getText().toString().trim();
        int strID = Integer.parseInt(edtIDTeacher.getText().toString().trim());
        db = new database(this);
        Teacher teacher = new Teacher(strID,strEmail,strPassword);
        int loginteacher = teacher.isValidl();
        if(loginteacher ==0)
        {
            Toast.makeText(MainActivity.this,"Please enter Email",Toast.LENGTH_SHORT).show();
        } else if (loginteacher ==1){
            Toast.makeText(MainActivity.this,"Please enter a valid email",Toast.LENGTH_SHORT).show();
        } else if (loginteacher ==2){
            Toast.makeText(MainActivity.this,"Please enter a password",Toast.LENGTH_SHORT).show();
        } else if (loginteacher ==3){
            Toast.makeText(MainActivity.this,"Password should be more the 6 character",Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean checkemailpass = db.checkemailpasswordteacher(strID,strEmail,strPassword);
            if(checkemailpass==true)
            {
            Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), TeacherHomeActivity.class);
            intent.putExtra("id_teacher",strID);
            startActivity(intent);
            finish();
            }
            else {
                Toast.makeText(MainActivity.this,"Email or password or id invalid",Toast.LENGTH_SHORT).show();
            }
        }
    }





    private void clickOpenBottomSheetDialogRegister(){
        View viewDialog = getLayoutInflater().inflate(R.layout.register_bottom_sheet,null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(true);

        Button btnCancel = viewDialog.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });


         usernameregister = viewDialog.findViewById(R.id.username_register);
         passwordregister= viewDialog.findViewById(R.id.password_register);
         repasswordregister= viewDialog.findViewById(R.id.repassword_register);
        Button btnacceptshape= viewDialog.findViewById(R.id.accept_register);
        db = new database(this);

        btnacceptshape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameregister.getText().toString().trim();
                String pass = passwordregister.getText().toString().trim();
                String repass = repasswordregister.getText().toString().trim();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(MainActivity.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = db.checkusername(user);
                        if(checkuser==false){
                            Admin admin = CreateAdmin();
                            Boolean insert = db.AddAdmin(admin);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this,"User already exists! please sign",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this,"Passwords not matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    private Admin CreateAdmin(){
        String user = usernameregister.getText().toString().trim();
        String pass = passwordregister.getText().toString().trim();

        Admin admin = new Admin(user,pass);
        return admin;
    }

}