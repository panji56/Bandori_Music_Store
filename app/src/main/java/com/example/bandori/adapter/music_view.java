package com.example.bandori.adapter;

import android.app.AlertDialog;
import android.content.ContentProvider;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bandori.R;
import com.example.bandori.data.MUSICBandori;
import com.example.bandori.data.USERBandori;
import com.example.bandori.data.transactionTBL;
import com.example.bandori.database.MusicDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class music_view extends BaseAdapter {

    private ArrayList<MUSICBandori> data;
    private LayoutInflater inflater;
    private Context context;

    public music_view(ArrayList<MUSICBandori> data, Context context){
        this.data=data;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=inflater.inflate(R.layout.music_view_adapter,null);
        }
        TextView title,price,bandname,release;
        ImageView img;
        img=(ImageView)convertView.findViewById(R.id.image_music);
        title=(TextView)convertView.findViewById(R.id.title_music);
        price=(TextView)convertView.findViewById(R.id.price_music);
        bandname=(TextView)convertView.findViewById(R.id.band_music);
        release=(TextView)convertView.findViewById(R.id.date_music);
        title.setText(data.get(position).getMusicTittle());
        Glide.with(context).load(data.get(position).getMusicCvUrl()).into(img);
        price.setText(""+data.get(position).getMusicPrice());
        bandname.setText(data.get(position).getMusicBandName());
        release.setText(""+data.get(position).getMusicReleaseDate());
        return convertView;
    }
}