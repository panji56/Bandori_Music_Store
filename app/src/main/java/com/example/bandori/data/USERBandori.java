package com.example.bandori.data;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeUnit;
import android.util.Log;

import com.example.bandori.database.MusicDB;

import java.io.Serializable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class USERBandori  implements Serializable{
    private int userID;
    private String Email;
    private String password;
    private String gender;
    private String birthday;
    private String phoneNumber;

    public USERBandori(){
        this.userID=-9999;
        this.Email = "";
        this.password = "";
        this.gender = "";
        this.birthday = "";
        this.phoneNumber = "";
    }

    public USERBandori(int userID, String email, String password, String gender, String birthday, String phoneNumber)
    {
        this.userID = userID;
        Email = email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean validateEmail(Context context){
        if(Email.matches("^[^@ ]+@[^@ ]+.[^@ ]+$")){
            //check if user already in database
            MusicDB db = new MusicDB(context);
            String Selection="UserEmail=?";
            String[] SelectionArgs={Email};
            ArrayList<USERBandori> usr=db.userRead(Selection,SelectionArgs);
            int count=usr.size();
            usr.clear();
            if(count>0){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public boolean validateGender(){
        if(gender.equals("")){
            return false;
        }
        return true;
    }

    public boolean validateBirthday(){
        if(birthday.equals("")){
            return false;
        }else{
            //format y/m/d
            Date cur=Calendar.getInstance().getTime();
            String[] spl=birthday.split("/");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR,Integer.parseInt(spl[0]));
            cal.set(Calendar.MONTH,Integer.parseInt(spl[1]));
            cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(spl[2]));
            Date bir = cal.getTime();
            long diff=Math.abs(cur.getTime() - cal.getTime().getTime());
            long ratiomiltoyear=Long.parseLong("31536000000");
            long yeardiff=diff/(ratiomiltoyear);
            if(yeardiff<13){
                return false;
            }
            return true;
        }
    }

    public boolean validatePassword(String confirmPass){
        if(password.length()<9){
            return false;
        };
        if(!password.matches("^\\p{Alnum}+$")){
            return false;
        };
        if(!password.equals(confirmPass)){
            return false;
        }
        return true;
    }

    public boolean validatePhone(){
        if(phoneNumber.length()>13){
            return false;
        };
        if(!phoneNumber.matches("^62\\p{N}+$")){
           return false;
        }
        return true;
    };

}
