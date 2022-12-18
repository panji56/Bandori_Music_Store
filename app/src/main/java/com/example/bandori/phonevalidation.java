package com.example.bandori;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bandori.database.MusicDB;
import java.util.Random;

public class phonevalidation extends AppCompatActivity {

    private String code="123456";
    private String phone="";
    private static final int SMS_PERMISSION_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_validation);
        Intent dat = getIntent();
        phone = dat.getStringExtra("phone");
        Log.i("user",""+phone);
        Button verify = (Button) findViewById(R.id.verifyphone);        //this part will send resultCode=1
        Button sendCode = (Button) findViewById(R.id.sendcode);
        EditText codeinput = (EditText) findViewById(R.id.phone_code);
        Log.i("Phone code : ", code);
        verify.setOnClickListener(v -> {
            String phonecode = codeinput.getText().toString();
            if (code.equals(phonecode)) {
                Intent i = new Intent();
                i.putExtra("phone", phone);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
        sendCode.setOnClickListener(v -> {
            code =
                    "" + (new Random().nextInt(10)) +
                            "" + (new Random().nextInt(10)) +
                            "" + (new Random().nextInt(10)) +
                            "" + (new Random().nextInt(10)) +
                            "" + (new Random().nextInt(10)) +
                            "" + (new Random().nextInt(10)) + "";
            checkPermission(Manifest.permission.SEND_SMS,SMS_PERMISSION_CODE);
        });
        //just call finish() to close activity
    }

    public void checkPermission(String permission, int requestCode){
        if(ContextCompat.checkSelfPermission(this,permission)== PackageManager.PERMISSION_DENIED){
            //request the permission
            ActivityCompat.requestPermissions(this, new String[] { permission }, requestCode);
        }else{
            //send SMS here
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(phone,null,code,null,null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //send SMS here
                SmsManager smgr = SmsManager.getDefault();
                smgr.sendTextMessage(phone,null,code,null,null);
            }
            else {
                Toast.makeText(this, "SMS Send Permission Denied", Toast.LENGTH_SHORT) .show();
            }
        }

    }

}