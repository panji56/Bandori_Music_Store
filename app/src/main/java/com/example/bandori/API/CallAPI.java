package com.example.bandori.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CallAPI {
    private static final String BASE_URL="https://raw.githubusercontent.com/acad600/JSONRepository/master/MOBI6021/E212-MOBI6021-AL01-00%20Music%20Store.json/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if(retrofit==null){
//            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    };

}
