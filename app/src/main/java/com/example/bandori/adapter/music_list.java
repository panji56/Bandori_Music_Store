package com.example.bandori.adapter;

import android.app.AlertDialog;
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

public class music_list extends BaseAdapter {

    private ArrayList<MUSICBandori> data;
    private USERBandori user;
    private Context context;
    private LayoutInflater inflater;

    public music_list(ArrayList<MUSICBandori> data, USERBandori user, Context context){
        this.data=data;
        this.user=user;
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
            convertView=inflater.inflate(R.layout.music_list_adapter,null);
        }
        TextView title,price,bandname,release;
        ImageView img;
        Button buy;
        img=(ImageView)convertView.findViewById(R.id.image_music);
        title=(TextView)convertView.findViewById(R.id.title_music);
        price=(TextView)convertView.findViewById(R.id.price_music);
        bandname=(TextView)convertView.findViewById(R.id.band_music);
        release=(TextView)convertView.findViewById(R.id.date_music);
        title.setText(data.get(position).getMusicTittle());
        price.setText(""+data.get(position).getMusicPrice());
        bandname.setText(data.get(position).getMusicBandName());
        release.setText(""+data.get(position).getMusicReleaseDate());
        buy=(Button)convertView.findViewById(R.id.buy_music);
        Glide.with(context).load(data.get(position).getMusicCvUrl()).into(img);
        final View cv=convertView;
        buy.setOnClickListener(v->{
            //show alert dialog and check if music already bought
            AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(context);
            alertdialogbuilder.setMessage("Buy Music "+data.get(position).getMusicTittle()+" ? ")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MusicDB db = new MusicDB(context);
                            int count=0;
                            String Selection="UserId=? AND MusicId=?";
                            String[] SelectionArgs={""+user.getUserID(),""+data.get(position).getMusicID()};
                            ArrayList<transactionTBL> trans = db.transactionRead(Selection,SelectionArgs);
                            count=trans.size();
                            if(count>0){
                                //user already bought
                                Toast.makeText(context,"Music Already Bought",Toast.LENGTH_LONG).show();
                            }else{
                                //add to transaction table with date
                                ArrayList<transactionTBL> ins = new ArrayList<transactionTBL>();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                                String purchase = dateFormat.format(Calendar.getInstance().getTime());
                                transactionTBL d = new transactionTBL(0,user.getUserID(),data.get(position).getMusicID(),purchase);
                                ins.add(d);
                                db.transactionInsert(ins);
                                ins.clear();
                            }
                            trans.clear();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog alert= alertdialogbuilder.create();
            alert.show();
        });
        return convertView;
    }
}
