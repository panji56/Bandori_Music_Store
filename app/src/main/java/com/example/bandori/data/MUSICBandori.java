package com.example.bandori.data;

public class MUSICBandori {
    private int MusicID;
    private String MusicTittle;
    private float MusicPrice;
    private String MusicBandName;
    private String MusicReleaseDate;
    private String MusicCvUrl;

    public String getMusicCvUrl() {
        return MusicCvUrl;
    }

    public void setMusicCvUrl(String musicCvUrl) {
        MusicCvUrl = musicCvUrl;
    }

    public MUSICBandori(int musicID,
                        String musicTittle,
                        float musicPrice,
                        String musicBandName,
                        String musicReleaseDate,
                        String MusicCvUrl) {
        MusicID = musicID;
        MusicTittle = musicTittle;
        MusicPrice = musicPrice;
        MusicBandName = musicBandName;
        MusicReleaseDate = musicReleaseDate;
        this.MusicCvUrl=MusicCvUrl;
    }

    public int getMusicID() {
        return MusicID;
    }

    public void setMusicID(int musicID) {
        MusicID = musicID;
    }

    public String getMusicTittle() {
        return MusicTittle;
    }

    public void setMusicTittle(String musicTittle) {
        MusicTittle = musicTittle;
    }

    public float getMusicPrice() {
        return MusicPrice;
    }

    public void setMusicPrice(float musicPrice) {
        MusicPrice = musicPrice;
    }

    public String getMusicBandName() {
        return MusicBandName;
    }

    public void setMusicBandName(String musicBandName) {
        MusicBandName = musicBandName;
    }

    public String getMusicReleaseDate() {
        return MusicReleaseDate;
    }

    public void setMusicReleaseDate(String musicReleaseDate) {
        MusicReleaseDate = musicReleaseDate;
    }
}
