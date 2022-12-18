package com.example.bandori.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bandori.data.MUSICBandori;
import com.example.bandori.data.USERBandori;
import com.example.bandori.data.transactionTBL;

import java.util.ArrayList;

public class MusicDB {
    private Context context;
    private DBHelper dbHelper;
    public MusicDB(Context context){
        this.context=context;
        dbHelper=new DBHelper(this.context);
    };

    //INSERT

    public void userInsert(ArrayList<USERBandori> user){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //Cursor cursor = db.update();
        for(USERBandori v:user){
            ContentValues cv=new ContentValues();
            cv.put("UserEmail",v.getEmail());
            cv.put("UserPassword",v.getPassword());
            cv.put("UserBirthday",v.getBirthday());
            cv.put("UserPhoneNumber",v.getPhoneNumber());
            db.insert(dbHelper.userTable,null,cv);
        };
        db.close();
    };
    public void musicInsert(ArrayList<MUSICBandori> music){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //Cursor cursor = db.update();
        for(MUSICBandori v:music){
            ContentValues cv=new ContentValues();
            cv.put("MusicTitle",v.getMusicTittle());
            cv.put("MusicPrice",v.getMusicPrice());
            cv.put("MusicBandName",v.getMusicBandName());
            cv.put("MusicReleaseDate",v.getMusicReleaseDate());
            cv.put("MusicCvURL",v.getMusicCvUrl());
            db.insert(dbHelper.musicTable,null,cv);
        };
        db.close();
    };
    public void transactionInsert(ArrayList<transactionTBL> transaction){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        for(transactionTBL ta:transaction){
            ContentValues cv=new ContentValues();
            cv.put("UserID",ta.getUserID());
            cv.put("MusicID",ta.getMusicID());
            cv.put("TransactionDate",ta.getTransactionDate());
            db.insert(dbHelper.transactionTable,null,cv);
        };
        db.close();
    };

    //READ AREA

    public int musicCount(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<USERBandori> user=new ArrayList<USERBandori>();
        Cursor cursor = db.query(dbHelper.musicTable,
                null,
                null,
                null,
                null,
                null,
                null);
        int count=cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }
    public ArrayList<USERBandori> userRead(String Selection,String[] SelectionArgs){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<USERBandori> user=new ArrayList<USERBandori>();
        Cursor cursor = db.query(dbHelper.userTable,
                null,
                Selection,
                SelectionArgs,
                null,
                null,
                null);
        while(cursor.moveToNext()){
            USERBandori usr=new USERBandori(
                    cursor.getInt(cursor.getColumnIndex("UserId")),
                    cursor.getString(cursor.getColumnIndex("UserEmail")),
                    cursor.getString(cursor.getColumnIndex("UserPassword")),
                    cursor.getString(cursor.getColumnIndex("UserGender")),
                    cursor.getString(cursor.getColumnIndex("UserBirthday")),
                    cursor.getString(cursor.getColumnIndex("UserPhoneNumber"))
            );
            user.add(usr);
        }
        cursor.close();
        db.close();
        return user;
    };
    public ArrayList<MUSICBandori> musicRead(String Selection,String[] SelectionArgs){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //String selection="MusicId=?";
        //String[] selectionArgs={""+musicID};
        ArrayList<MUSICBandori> music=new ArrayList<MUSICBandori>();
        Cursor cursor = db.query(dbHelper.musicTable,
                null,
                Selection,
                SelectionArgs,
                null,
                null,
                null);
        while(cursor.moveToNext()){
            MUSICBandori msc=new MUSICBandori(
                    cursor.getInt(cursor.getColumnIndex("MusicId")),
                    cursor.getString(cursor.getColumnIndex("MusicTitle")),
                    cursor.getFloat(cursor.getColumnIndex("MusicPrice")),
                    cursor.getString(cursor.getColumnIndex("MusicBandName")),
                    cursor.getString(cursor.getColumnIndex("MusicReleaseDate")),
                    cursor.getString(cursor.getColumnIndex("MusicCvURL"))
            );
            music.add(msc);
        };
        cursor.close();
        db.close();
        return music;
    };
    public ArrayList<transactionTBL> transactionRead(String Selection,String[] SelectionArgs){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //String selection="MusicId=?";
        //String[] selectionArgs={""+musicID};
        ArrayList<transactionTBL> tra=new ArrayList<transactionTBL>();
        Cursor cursor = db.query(dbHelper.transactionTable,
                null,
                Selection,
                SelectionArgs,
                null,
                null,
                null);
        while(cursor.moveToNext()){
            transactionTBL tr=new transactionTBL(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3)
            );
            tra.add(tr);
        };
        cursor.close();
        db.close();
        return tra;
    };
    public ArrayList<MUSICBandori> transactionReadJoinMusic(String userID){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //String selection="MusicId=?";
        //String[] selectionArgs={""+musicID};
        ArrayList<MUSICBandori> music=new ArrayList<MUSICBandori>();
        String raw=
                "SELECT mt.MusicId,mt.MusicTitle,mt.MusicPrice,mt.MusicBandName,mt.MusicReleaseDate,mt.MusicCvURL FROM "
                +dbHelper.transactionTable
                +" AS tb INNER JOIN "
                +dbHelper.musicTable
                +" AS mt ON "
                +" tb.MusicID = mt.MusicID "
                +"WHERE tb.UserID=?";
        String[] args={userID};
        Cursor cursor = db.rawQuery(raw,args);
        while(cursor.moveToNext()){
            MUSICBandori msc=new MUSICBandori(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            music.add(msc);
        };
        cursor.close();
        db.close();
        return music;
    }


    //UPDATE AREA

    public void userUpdate(ArrayList<USERBandori> user){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //Cursor cursor = db.update();
        for(USERBandori v:user){
            ContentValues cv=new ContentValues();
            String Selection="UserId=?";
            String[] SelectionArgs={""+v.getUserID()};
            cv.put("UserEmail",v.getEmail());
            cv.put("UserPassword",v.getPassword());
            cv.put("UserBirthday",v.getBirthday());
            cv.put("UserPhoneNumber",v.getPhoneNumber());
            db.update(dbHelper.userTable,cv,Selection,SelectionArgs);
        };
        db.close();
    };
    public void musicUpdate(ArrayList<MUSICBandori> music){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //Cursor cursor = db.update();
        for(MUSICBandori v:music){
            ContentValues cv=new ContentValues();
            String Selection="MusicId=?";
            String[] SelectionArgs={""+v.getMusicID()};
            cv.put("MusicTitle",v.getMusicTittle());
            cv.put("MusicPrice",v.getMusicPrice());
            cv.put("MusicBandName",v.getMusicBandName());
            cv.put("MusicReleaseDate",v.getMusicReleaseDate());
            cv.put("MusicCvURL",v.getMusicCvUrl());
            db.update(dbHelper.musicTable,cv,Selection,SelectionArgs);
        };
        db.close();
    };
    public void transactionUpdate(ArrayList<transactionTBL> transaction){

    };

    //DELETE AREA

    public void userDelete(String Selection,String[] SelectionArgs){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(dbHelper.userTable,Selection,SelectionArgs);
        db.close();
    };
    public void musicDelete(String Selection,String[] SelectionArgs){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(dbHelper.musicTable,Selection,SelectionArgs);
        db.close();
    };
    public void transactionDelete(String Selection,String[] SelectionArgs){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(dbHelper.transactionTable,Selection,SelectionArgs);
        db.close();
    };
}
