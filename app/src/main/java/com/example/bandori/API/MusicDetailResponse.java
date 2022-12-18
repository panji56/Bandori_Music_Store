package com.example.bandori.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicDetailResponse {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("bandName")
    @Expose
    public String bandName;
    @SerializedName("releaseYear")
    @Expose
    public Integer releaseYear;
    @SerializedName("coverUrl")
    @Expose
    public String coverUrl;
}
