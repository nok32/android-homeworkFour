package com.example.kiril.homeworkbroadcastandservice;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

/**
 * Created by Kiril on 22/09/2016.
 */
public class MusikPlayer extends android.support.v4.app.Fragment{
    Button close;
    Button start;
    Button pause;
    Button fastForward;
    Button reverse;
    Intent musikServiceIntent;
    Song song;


    public void setSong(Song song){
        this.song = song;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.musik_player_fragment, container, false);

        close = (Button) view.findViewById(R.id.closeBtn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });

        start = (Button) view.findViewById(R.id.startBtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        pause = (Button) view.findViewById(R.id.pauseBtn);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });

        fastForward = (Button) view.findViewById(R.id.fastForwardBtn);
        fastForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fastForward();
            }
        });

        reverse = (Button) view.findViewById(R.id.reverseBtn);
        reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverse();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        musikServiceIntent = new Intent(this.getContext(), ServiceMusikPlayer.class);
    }

    public void close(){
        getActivity().stopService(musikServiceIntent);

        this.getActivity().getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();

        getActivity().getSupportFragmentManager().executePendingTransactions();
    }


    public void play(){
        musikServiceIntent.removeExtra("Command");
        musikServiceIntent.putExtra("Command", "play");
        musikServiceIntent.putExtra("Song",song);

        getActivity().startService(musikServiceIntent);
    }

    public void pause(){
        musikServiceIntent.removeExtra("Command");
        musikServiceIntent.putExtra("Command", "pause");

        getActivity().startService(musikServiceIntent);
    }

    public void fastForward(){
        musikServiceIntent.removeExtra("Command");
        musikServiceIntent.putExtra("Command", "fastForward");

        getActivity().startService(musikServiceIntent);
    }

    public void reverse(){
        musikServiceIntent.removeExtra("Command");
        musikServiceIntent.putExtra("Command", "reverse");

        getActivity().startService(musikServiceIntent);
    }
}
