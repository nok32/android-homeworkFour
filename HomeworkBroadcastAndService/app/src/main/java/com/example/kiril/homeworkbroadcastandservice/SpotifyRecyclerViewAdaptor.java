package com.example.kiril.homeworkbroadcastandservice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kiril on 22/09/2016.
 */
public class SpotifyRecyclerViewAdaptor extends RecyclerView.Adapter<SpotifyRecyclerViewAdaptor.VideoHolder>{
    private ArrayList<Song> mAdapterData;
    private IElementClick mListener;


    public SpotifyRecyclerViewAdaptor(ArrayList<Song> data, IElementClick listener){
        mAdapterData = data;
        mListener = listener;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_layout, parent, false);

        VideoHolder vh = new VideoHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        if (holder != null) {
            holder.mSongTitle.setText(this.mAdapterData.get(position).getName());
            holder.mSongInfo.setText(this.mAdapterData.get(position).getsongInformation());
            holder.setItemPosition(position);
        }
    }

    @Override
    public int getItemCount() {
        return this.mAdapterData.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder{

        TextView mSongInfo;
        TextView mSongTitle;
        int position;

        public void setItemPosition(int position) {
            this.position = position;
        }

        public VideoHolder(View itemView) {
            super(itemView);

            this.mSongInfo = (TextView) itemView.findViewById(R.id.songInformation);
            this.mSongTitle = (TextView) itemView.findViewById(R.id.songTitle);
            // TODO Remove this this.mLayout = (LinearLayout) itemView.findViewById(R.id.taskThirdLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.elementClick(position);
                }
            });
        }
    }
}
