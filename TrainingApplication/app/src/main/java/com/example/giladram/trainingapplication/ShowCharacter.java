package com.example.giladram.trainingapplication;

/**
 * Created by giladram on 11/11/17.
 */

public class ShowCharacter {

    private String mName;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmPictureId() {
        return mPictureId;
    }

    public void setmPictureId(int mPictureId) {
        this.mPictureId = mPictureId;
    }

    private int mPictureId;

    public ShowCharacter(String iName , int iPictureName){
        mName = iName;
        mPictureId = iPictureName;
    }
}
