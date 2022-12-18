package com.example.bandori.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JSONMusicService {
    @GET("/")
    Call<String> getMusics();
}
