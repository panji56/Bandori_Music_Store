package com.example.bandori.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bandori.R;
import com.example.bandori.data.USERBandori;
import com.example.bandori.database.MusicDB;
import com.example.bandori.userUpdate;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private userUpdate us;
    private String DOB="";
    private USERBandori user;
    private Context context;
    private EditText email,phone,oldpassword,newpass,connewpass;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button dob,update;
    private RadioGroup gen;
    private RadioButton rb;
    private View vw;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public ProfileFragment(userUpdate us, USERBandori user,Context context){
        this.us=us;
        this.context=context;
        this.user=user;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email=(EditText) view.findViewById(R.id.register_user);
        //phone=(EditText) view.findViewById(R.id.register_phone);
        oldpassword=(EditText) view.findViewById(R.id.old_password);
        newpass=(EditText) view.findViewById(R.id.register_password);
        connewpass=(EditText) view.findViewById(R.id.register_con_password);
        email.setHint(user.getEmail());
        //phone.setHint(user.getPhoneNumber());
        gen=(RadioGroup) view.findViewById(R.id.register_gender);
        dob=(Button) view.findViewById(R.id.register_dob);
        update=(Button) view.findViewById(R.id.update_button);
        vw=view;
        dob.setOnClickListener(v->{
            String[] dobsplit=user.getBirthday().split("/");
            DatePickerDialog datePickerDialog = new DatePickerDialog(context,0, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    // format : y/m/d
                    DOB=""+year+"/"+month+"/"+dayOfMonth+"";
                }
            },Integer.parseInt(dobsplit[0]),Integer.parseInt(dobsplit[1]),Integer.parseInt(dobsplit[2]));
            datePickerDialog.show();
        });
        update.setOnClickListener(v->{
            MusicDB db = new MusicDB(getContext());
            ArrayList<USERBandori> a=new ArrayList<USERBandori>();
            String msg="";
            //validation with old pass;
            String G="";
            if(gen.getCheckedRadioButtonId()==(-1)){
                G="";
            }else{
                RadioButton rb = (RadioButton) view.findViewById(gen.getCheckedRadioButtonId());
                G = rb.getText().toString();
            }
            user.setGender(G);
            USERBandori upuser = new USERBandori(
                    user.getUserID(),
                    email.getText().toString(),
                    newpass.getText().toString(),
                    G,
                    DOB,
                    user.getPhoneNumber()
                    );
            //validation here
            //validate email
            boolean flagem=true;
            flagem=upuser.validateEmail(context);
            if(!flagem){
                msg+="wrong email format or email already used\n";
            };
            boolean flagg=true;
            flagg=upuser.validateGender();
            if(!flagg){
                msg+="gender must be chooser\n";
            };
            boolean flagpass=true;
            flagpass=upuser.validatePassword(connewpass.getText().toString());
            if(!flagpass){
                msg+="password must be minimal 9 characters and alpha numeric and match with confirm password\n";
            }
            boolean oldpass=true;
            if(!user.getPassword().equals(oldpassword.getText().toString())){
                msg+="not match with current password\n";
                oldpass=false;
            };
            boolean flagP;
            flagP=upuser.validatePhone();
            if(!flagP){
                msg+="maximum digit is 13 and must start with '62'\n";
            };
            boolean flagDOB;
            flagDOB=upuser.validateBirthday();
            if(!flagDOB){
                msg+="must choose DOB and at least 13 Years old\n";
            };
            if(flagem&&flagDOB&&flagg&&flagP&&flagpass&&oldpass){
                a.add(upuser);
                db.userUpdate(a);
                us.update(upuser);
            }else {
                Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
            };
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}