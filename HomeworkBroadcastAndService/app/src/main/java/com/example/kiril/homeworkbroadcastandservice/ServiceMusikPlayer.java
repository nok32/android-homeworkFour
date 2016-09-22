package com.example.kiril.homeworkbroadcastandservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.SeekBar;
import android.widget.Toast;


/**
 * Created by Kiril on 22/09/2016.
 */
public class ServiceMusikPlayer extends Service {

    private MediaPlayer mPlayer;
    IBinder binder = new ServiceMusikPlayerBinder();

    public class ServiceMusikPlayerBinder extends Binder
    {
        ServiceMusikPlayer getService()
        {
            return ServiceMusikPlayer.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mPlayer == null){
            Song song = intent.getParcelableExtra("Song");
            initMediaPlayerPreference(song.getPathToSong());
        }

        String command = intent.getStringExtra("Command");

        switch (command){
            case "play":
                mPlayer.start();
                break;
            case "pause":
                mPlayer.pause();
                break;
            case "fastForward":
                if (mPlayer != null){
                    int curentPostion = mPlayer.getCurrentPosition();
                    curentPostion += 1000;
                    mPlayer.seekTo(curentPostion);
                }
                break;
            case "reverse":
                if (mPlayer != null){
                    int startPosition = 0;
                    mPlayer.seekTo(startPosition);
                }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void initMediaPlayerPreference(String path) {
        try{
            int song = getResources().getIdentifier(path,"raw",getPackageName());
            mPlayer = MediaPlayer.create(getApplicationContext(), song);
        } catch(Exception ex) {
            Toast.makeText(this,"Can not find this song",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPlayer != null)
            mPlayer.stop();
    }
}
