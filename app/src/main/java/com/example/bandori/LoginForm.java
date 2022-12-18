package com.example.bandori;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bandori.API.CallAPI;
import com.example.bandori.API.JSONMusicService;
import com.example.bandori.API.MusicDetailResponse;
import com.example.bandori.API.MusicResponList;
import com.example.bandori.data.MUSICBandori;
import com.example.bandori.data.USERBandori;
import com.example.bandori.database.DBHelper;
import com.example.bandori.database.MusicDB;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginForm extends AppCompatActivity {
    private final int LAUNCH_REGISTER=1;

    private void loadDB(){
        //load data from web only once
        //check DB first for data
        //create DB then load from web
        MusicDB db=new MusicDB(this);
        Log.i("SIZE",""+db.musicCount());
        if(db.musicCount()<=0){
//            Retrofit retrofit = CallAPI.getRetrofit();
//            JSONMusicService service = retrofit.create(JSONMusicService.class);
//            Call<String> call = service.getMusics();
//            call.enqueue(new Callback<String>() {
//                @Override
//                public void onResponse(Call<String> call, Response<String> response) {
//                    if(response.isSuccessful()){
//                        Log.i("Result of Text, not JSON","it works");
//                        Log.i("Result of Text, not JSON",response.body());
//
//                    }
//                };
//                @Override
//                public void onFailure(Call<String> call, Throwable t) {
//                    //Log.i("TAG", "onFailure: failed to connect");;
//                    Log.i("TAG","failed");
//                    t.printStackTrace();
//                }
//            }
                    ArrayList<MUSICBandori> music=new ArrayList<MUSICBandori>();
                    music.add(new MUSICBandori( 1,
                    "KIZUNA MUSIC?",
                     41,
                    "Poppin'Party",
                     "2018",
                    "https://static.wikia.nocookie.net/bandori/images/f/f5/Poppin%27Party_12th_Single_Blu-ray_Cover.jpg/revision/latest/scale-to-width-down/350?cb=20181106065426"));
                    music.add(new MUSICBandori(
                            2,
                    "FIRE BIRD",
                    28,
                    "Roselia",
                    "2019",
                    "https://static.wikia.nocookie.net/bandori/images/4/42/Roselia_9th_Single_Blu-Ray_Edition_Cover.jpg/revision/latest/scale-to-width-down/350?cb=20190609110057"
                    ));
                    music.add(new MUSICBandori(3,
                    "EXPOSE 'Burn out!!!'",
                    32,
                    "RAISE A SUILEN",
                    "2019",
                    "https://static.wikia.nocookie.net/bandori/images/8/86/EXPOSE_%27Burn_out%21%21%21%27_Game_Cover.png/revision/latest/scale-to-width-down/350?cb=20200602150206"));
                    music.add(new MUSICBandori(
                            4,
                    "flame of hope",
                    38,
                    "Morfonica",
                    "2021",
                    "https://static.wikia.nocookie.net/bandori/images/4/4e/Flame_of_hope_Game_Cover.png/revision/latest/scale-to-width-down/350?cb=20200711021918"
                    ));
                    music.add(new MUSICBandori(
                             5,
                    "Hey-day Capriccio",
                    23,
                     "Afterglow",
                     "2018",
                     "https://static.wikia.nocookie.net/bandori/images/e/e3/Hey-day_Capriccio.png/revision/latest/scale-to-width-down/350?cb=20201124110123"
                    ));
                    music.add(new MUSICBandori(
                            6,
                    "Hanamaru?Andante",
                     25,
                     "Pastel?Palettes",
                     "2018",
                     "https://static.wikia.nocookie.net/bandori/images/1/19/Hanamaru%E2%97%8EAndante_cover.png/revision/latest/scale-to-width-down/350?cb=20201124105426"
                    ));
                    music.add(new MUSICBandori(
                            7,
                     "Happiness! Happy Magical?",
                    25,
                     "Hello, Happy World!",
                     "2017",
                     "https://static.wikia.nocookie.net/bandori/images/0/01/Happiness%21_Happy_Magical%E2%99%AA.jpg/revision/latest/scale-to-width-down/350?cb=20201124105553"
                    ));
                    music.add(new MUSICBandori(
                             8,
                    "Kiseki",
                     35,
                     "Roselia",
                     "2018",
                    "https://static.wikia.nocookie.net/bandori/images/d/d1/Kiseki.jpg/revision/latest/scale-to-width-down/350?cb=20180331104416"
                    ));
                    music.add(new MUSICBandori(
                            9,
                    "DRIVE US CRAZY",
                    40,
                     "RAISE A SUILEN",
                     "2020", "https://static.wikia.nocookie.net/bandori/images/4/49/RAISE_A_SUILEN_4th_Single_Regular_Edition.jpg/revision/latest/scale-to-width-down/350?cb=20191211092223"
                    ));
                    db.musicInsert(music);
                    music.clear();
        };
//        Log.i("SIZE",""+db.musicCount());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        Button loginbutton = (Button) findViewById(R.id.login_button);
        TextView registerbutton = (TextView) findViewById(R.id.register_button);
        EditText loginEmail = (EditText) findViewById(R.id.login_user);
        EditText loginPass = (EditText) findViewById(R.id.login_pass);
        registerbutton.setOnClickListener(v->{
            Intent i = new Intent(LoginForm.this,RegisterForm.class);
            startActivityForResult(i,LAUNCH_REGISTER);
        });
        loginbutton.setOnClickListener(v->{
            //temporary to show list without ID
            USERBandori user=new USERBandori();
            String msg="";
            boolean flag=true;
            String email=loginEmail.getText().toString();
            String pass=loginPass.getText().toString();
            if(email.equals("")){
                msg+="email must be filled\n";
                flag=false;
            };
            if(pass.equals("")){
                msg+="password must be filled\n";
                flag=false;
            };
            if(!pass.equals("")&&!email.equals("")){
                MusicDB db = new MusicDB(this);
                String Selection="UserEmail=?";
                String[] SelectionArgs={email};
                ArrayList<USERBandori> usr=db.userRead(Selection,SelectionArgs);
                int count=usr.size();
                if(count>0){
                    if(usr.get(0).getPassword().equals(pass)){
                        user=usr.get(0);
                    }else{
                        flag=false;
                        msg+="Password not match\n";
                    }
                }else{
                    flag=false;
                    msg+="Email not registered\n";
                };
                usr.clear();
            };
            if(flag){
                Intent i = new Intent(this,HomePage.class);
                i.putExtra("DATA",user);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_REGISTER) {
            if (resultCode == Activity.RESULT_OK) {
                //finish this activity, get the User ID and go to home page
                USERBandori DATA = (USERBandori) data.getSerializableExtra("DATA");
                Intent i = new Intent(this,HomePage.class);
                MusicDB db = new MusicDB(this);
                String Selection="UserEmail=?";
                assert DATA != null;
                String[] SelectionArgs={DATA.getEmail()};
                ArrayList<USERBandori> usr=db.userRead(Selection,SelectionArgs);
                i.putExtra("DATA",usr.get(0));
                startActivity(i);
                finish();
            }
        }
    }

}