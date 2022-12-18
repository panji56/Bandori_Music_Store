package com.example.bandori.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MusicResponList {

    @SerializedName("resultCount")
    @Expose
    public Integer resultCount;
    @SerializedName("results")
    @Expose
    public ArrayList<MusicDetailResponse> results = null;
}
