package com.example.bandori.data;

public class transactionTBL {
    private int TransactionID;
    private int UserID;
    private int MusicID;
    private String transactionDate;

    public transactionTBL(int transactionID, int userID, int musicID, String transactionDate) {
        TransactionID = transactionID;
        UserID = userID;
        MusicID = musicID;
        this.transactionDate = transactionDate;
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(int transactionID) {
        TransactionID = transactionID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getMusicID() {
        return MusicID;
    }

    public void setMusicID(int musicID) {
        MusicID = musicID;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
