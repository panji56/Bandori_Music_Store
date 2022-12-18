package com.example.bandori.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME="BandoriDB";
    private static final int DB_version=1;
    public String userTable="UserTable";
    public String transactionTable="TransactionTable";
    public String musicTable="MusicTable";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUser="CREATE TABLE IF NOT EXISTS "
                +userTable
                +"("
                +"UserId INTEGER PRIMARY KEY AUTOINCREMENT"
                +",UserEmail TEXT"
                +",UserPassword TEXT"
                +",UserGender TEXT"
                +",UserBirthday TEXT"
                +",UserPhoneNumber TEXT)";
        String createMusic="CREATE TABLE IF NOT EXISTS "
                +musicTable
                +"("
                +"MusicId INTEGER PRIMARY KEY AUTOINCREMENT"
                +",MusicTitle TEXT"
                +",MusicPrice REAL"
                +",MusicBandName TEXT"
                +",MusicReleaseDate TEXT"
                +",MusicCvURL TEXT)";
        String createTransact="CREATE TABLE IF NOT EXISTS "
                +transactionTable
                +"("
                +"TransactionID INTEGER PRIMARY KEY AUTOINCREMENT"
                +",UserId INTEGER NOT NULL"
                +",MusicId INTEGER NOT NULL"
                +",TransactionDate TEXT"
                +",FOREIGN KEY(UserId) REFERENCES "+userTable+"(UserId)"
                +",FOREIGN KEY(MusicId) REFERENCES "+musicTable+"(MusicId)"
                +")";
        db.execSQL(createUser);
        db.execSQL(createMusic);
        db.execSQL(createTransact);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String q1="DROP TABLE IF EXISTS "+transactionTable;
        String q2="DROP TABLE IF EXISTS "+musicTable;
        String q3="DROP TABLE IF EXISTS "+userTable;
        db.execSQL(q1);
        db.execSQL(q2);
        db.execSQL(q3);
        onCreate(db);
    }
}
