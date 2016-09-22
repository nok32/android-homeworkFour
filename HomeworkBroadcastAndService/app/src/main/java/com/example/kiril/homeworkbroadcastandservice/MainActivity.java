package com.example.kiril.homeworkbroadcastandservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IElementClick{

    private RecyclerView mRecyclerView;
    private SpotifyRecyclerViewAdaptor mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Song> data;
    private ArrayList<String> songsName;
    private ArrayList<String> songsInfo;
    private MusikPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songsName = new ArrayList<String>();
        songsName.add("Sin Ella Ofc");
        songsName.add("Le Professionel");
        songsName.add("Un Amor");
        songsName.add("Le Vent Le Cri");
        songsName.add("Poruchik Galicin");
        songsName.add("On The Turning Away");
        songsName.add("Hotel California(Spanish version)");
        songsName.add("Trava u Doma");
        songsName.add("El choclo");

        songsInfo = new ArrayList<String>();
        songsInfo.add("Gipsy Kings • Sin Ella Ofc");
        songsInfo.add("Enio Morricone • Le Professionel");
        songsInfo.add("Gipsy Kings • Un Amor");
        songsInfo.add("Ennio Moricone • Le Vent Le Cri");
        songsInfo.add("Aleksandar Malinov • Poruchik Galicin");
        songsInfo.add("Pink Floyd • On The Turning Away");
        songsInfo.add("Gipsy Kings • Hotel California Spanish Mix");
        songsInfo.add("Zemlqni • Trava u Doma");
        songsInfo.add("Julio Iglesias • El choclo");

        data = new ArrayList<Song>();

        for (int index = 0; index < songsInfo.size(); index++) {
            Song a = new Song(songsName.get(index), songsInfo.get(index));
            data.add(a);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.spotifyRecyclerView);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SpotifyRecyclerViewAdaptor(data, this);

        mRecyclerView.setAdapter(mAdapter);

        player = new MusikPlayer();
    }

    @Override
    public void elementClick(int position) {
        Song song = data.get(position);

        if (player.isAdded() == false)
        {
            player.setSong(song);
            this.mAttachFragment(player);
        }
    }

    private void mAttachFragment(MusikPlayer player){

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.musikPlayerContainer, player)
                .commit();

        getSupportFragmentManager().executePendingTransactions();
    }

}
