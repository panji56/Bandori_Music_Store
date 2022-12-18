package com.example.bandori;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bandori.data.USERBandori;
import com.example.bandori.fragment.AboutFragement;
import com.example.bandori.fragment.MyMusicFragment;
import com.example.bandori.fragment.ProfileFragment;
import com.example.bandori.fragment.musiclist;


public class HomePage extends AppCompatActivity implements userUpdate{
    private USERBandori ID=new USERBandori();
    @Override
    public void update(USERBandori user) {
        this.ID=user;
        TextView showID = (TextView)findViewById(R.id.MyID);
        showID.setText("Hello, "+ID.getEmail());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Intent data = getIntent();
        ID = (USERBandori) data.getSerializableExtra("DATA");
        TextView showID = (TextView)findViewById(R.id.MyID);
        showID.setText("Hello, "+ID.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bandori_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.music_list:
                musiclist ms = new musiclist(ID);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment,ms)
                        .commit();
                return true;
            case R.id.MyMusic:
                MyMusicFragment musicFragment = new MyMusicFragment(ID);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment,musicFragment)
                        .commit();
                return true;
            case R.id.MyProfile:
                ProfileFragment pf = new ProfileFragment(this,ID,this);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment,pf)
                        .commit();
                return true;
            case R.id.AboutStore:
                AboutFragement ab = new AboutFragement();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment,ab)
                        .commit();
                return true;
            case R.id.logout:
                Intent i = new Intent(this,LoginForm.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}