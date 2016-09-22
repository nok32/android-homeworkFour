package com.example.kiril.homeworkbroadcastandservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kiril on 22/09/2016.
 */
public class Song implements Parcelable {
    private String mName;
    private String mPathToSong;
    private String mSongInformation;

    public Song(String name, String songInfo ){
        this.setName(name);
        this.setSongInformation(songInfo);
        this.mPathToSong = this.getParsedPath(this.getsongInformation());
    }

    public String getName(){
        return this.mName;
    }
    public void setName(String name){
        this.mName = name;
    }

    public String getPathToSong(){
        return this.mPathToSong;
    }
    public void setPathToSong(String pathToSong){
        this.mPathToSong = pathToSong;
    }

    public String getsongInformation(){
        return this.mSongInformation;
    }
    public void setSongInformation(String songInformation){
        this.mSongInformation = songInformation;
    }

    private String getParsedPath(String name){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != '•'){
                if (name.charAt(i) == ' '){
                    sb.append("_");
                }else{
                    if (Character.isUpperCase(name.charAt(i)) == true){
                        sb.append(Character.toLowerCase(name.charAt(i)));
                    }else{
                        sb.append(name.charAt(i));
                    }
                }
            }else{
                sb.replace(i - 1, i, "");
            }
        }
        return sb.toString();
    }

    protected Song(Parcel in) {
        mName = in.readString();
        mPathToSong = in.readString();
        mSongInformation = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mPathToSong);
        dest.writeString(mSongInformation);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}