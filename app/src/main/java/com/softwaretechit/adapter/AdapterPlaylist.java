package com.softwaretechit.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.softwaretechit.R;
import com.softwaretechit.fragment.VideoFragment;
import com.softwaretechit.models.PlaylistItems;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPlaylist extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    String gettitle,getlist;
    private List<PlaylistItems> playList;

    public AdapterPlaylist(Context context, List<PlaylistItems> playList) {
        this.context = context;
        this.playList = playList;

    }

    class YoutubeHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView tv_plst_title, tv_video_count1,tv_video_count2;
        public YoutubeHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail=itemView.findViewById(R.id.tv_plst_thumbnail);
            tv_plst_title = itemView.findViewById(R.id.tv_plst_title);
            tv_video_count1 = itemView.findViewById(R.id.tv_video_count1);

        }

        public void setData(PlaylistItems data) {

                getlist = data.getId();
                gettitle = data.getSnippet().getTitle();
                int getcount = data.getContentDetails().getItemCount();
                String getthub = data.getSnippet().getThumbnails().getMedium().getUrl();
                    tv_plst_title.setText(gettitle);
                    tv_video_count1.setText(String.valueOf(getcount));
                    Picasso.get()
                            .load(getthub)
                            .placeholder(R.mipmap.ic_launcher)
                            .fit()
                            .centerCrop()
                            .into(thumbnail, new Callback() {
                                @Override
                                public void onSuccess() {
                                    Log.d("OnSuccess", "Thumbnail load successfully ");
                                }

                                @Override
                                public void onError(Exception e) {
                                    Log.e("onError", "Thumbnail not load successfully ", e);
                                }
                            });


        }


    }
    @NonNull
    @Override
    public YoutubeHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);

           View view = inflater.inflate(R.layout.row_item_playlist, parent, false);


        return new YoutubeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

            final PlaylistItems listYT = playList.get(position);
            YoutubeHolder yth = (YoutubeHolder) viewHolder;

                yth.setData(listYT);
                yth.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        VideoFragment homeFragment = new VideoFragment();
                        homeFragment.setPlaylistid(listYT.getId());
                        activity.getSupportFragmentManager().beginTransaction().add(R.id.playlistfrag, homeFragment).addToBackStack(null).commit();

                    }
                });


    }


    @Override
    public int getItemCount() {

        return playList.size();
    }


}
