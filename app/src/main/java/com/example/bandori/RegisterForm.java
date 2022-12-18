package com.example.bandori;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bandori.data.USERBandori;
import com.example.bandori.database.MusicDB;

import java.util.ArrayList;

public class RegisterForm extends AppCompatActivity implements View.OnClickListener{
    private final int LAUNCH_VALIDATION=1;
    private USERBandori user;
    private Button dob;
    private String DOB="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        EditText registerEmail = (EditText) findViewById(R.id.register_user);
        EditText phoneNumber = (EditText) findViewById(R.id.register_phone);
        EditText password = (EditText) findViewById(R.id.register_password);
        EditText conPass = (EditText) findViewById(R.id.register_con_password);
        RadioGroup gender = (RadioGroup) findViewById(R.id.register_gender);
        CheckBox terms = (CheckBox) findViewById(R.id.register_terms);
        dob = (Button) findViewById(R.id.register_dob);
        Button register = (Button) findViewById(R.id.register_button);
        TextView sign=(TextView) findViewById(R.id.login_button);
        dob.setOnClickListener(this);
        sign.setOnClickListener(v->{
            //finish this activity, get the User ID and go to home page
            Intent i = new Intent();
            i.putExtra("DATA","user");
            setResult(Activity.RESULT_CANCELED,i);
            finish();
        });
        register.setOnClickListener(v->{
            String phone = phoneNumber.getText().toString();
            String email = registerEmail.getText().toString();
            String pass = password.getText().toString();
            String gd="";
            if(gender.getCheckedRadioButtonId()==(-1)){
                gd="";
            }else{
                RadioButton rb = (RadioButton) findViewById(gender.getCheckedRadioButtonId());
                gd = rb.getText().toString();
            }
            //validation here
            String msg="";
            user=new USERBandori(0,email,pass,gd,DOB,phone);
            boolean flagem=true;
            //validate email
            flagem=user.validateEmail(this);
            if(!flagem){
                msg+="wrong email format or email already used\n";
            };
            boolean flagG=user.validateGender();
            if(!flagG){
                msg+="gender must be chooser\n";
            };
            boolean flagDOB=user.validateBirthday();
            if(!flagDOB){
                msg+="must choose DOB and at least 13 Years old\n";
            };
            boolean flagPass=user.validatePassword(conPass.getText().toString());
            if(!flagPass){
                msg+="password must be minimal 9 characters and alpha numeric and match with confirm password\n";
            }
            boolean flagPhone=user.validatePhone();
            if(!flagPhone){
                msg+="maximum digit is 13 and must start with '62'\n";
            }
            boolean trm=terms.isChecked();
            if(!trm){
                msg+="must agree the TOC\n";
            }
            if(flagDOB&&flagem&&flagG&&flagPass&&flagPhone&&trm){
                Intent i = new Intent(RegisterForm.this,phonevalidation.class);
                i.putExtra("phone",phone);
                startActivityForResult(i,LAUNCH_VALIDATION);
            }else{
                Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_VALIDATION) {
            if (resultCode == Activity.RESULT_OK) {
                //finish this activity, get the User ID and go to home page
                MusicDB db = new MusicDB(this);
                ArrayList<USERBandori> usr = new ArrayList<USERBandori>();
                usr.add(user);
                db.userInsert(usr);
                String userphone = data.getStringExtra("phone");
                Intent i = new Intent();
                i.putExtra("DATA",user);
                setResult(Activity.RESULT_OK,i);
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v == dob){
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,0, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    // format : y/m/d
                    DOB=""+year+"/"+month+"/"+dayOfMonth+"";
                }
            },1995,1,1);
            datePickerDialog.show();
        }
    }
}