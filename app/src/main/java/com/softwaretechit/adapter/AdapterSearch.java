package com.softwaretechit.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwaretechit.R;
import com.softwaretechit.activity.VideoActivity;
import com.softwaretechit.models.SearchYT;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.YoutubeHolder> {
    private Context context;
    String gettitle,getvideo,getdisciptioon;
    private List<SearchYT> videoList;

    public AdapterSearch(Context context, List<SearchYT> videoList) {
        this.context = context;
        this.videoList = videoList;

    }

    class YoutubeHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView tv_title, tv_description;
        public YoutubeHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.c_tv_title);
            tv_description = itemView.findViewById(R.id.c_tv_date);
            thumbnail=itemView.findViewById(R.id.c_tv_thumbnail);
        }

        public void setData(SearchYT data) {

             gettitle = data.getSnippetYT().getTitle();
            String getigt = data.getSnippetYT().getPublishedAt();
              getvideo =data.getResourceId().getVideoId();
              getdisciptioon=data.getSnippetYT().getDescription();
            String getthub=data.getSnippetYT().getThumbnails().getMedium().getUrl();
            tv_title.setText(gettitle);
            tv_description.setText(getigt);
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
                            Log.e("onError", "Thumbnail not load successfully ",e );
                        }
                    });
        }



    }
    @NonNull
    @Override
    public YoutubeHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.row_item_card_home,parent,false);

        return new YoutubeHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull YoutubeHolder viewHolder, final int position) {
     final SearchYT videoYT= videoList.get(position);
     YoutubeHolder yth= viewHolder;
     yth.setData(videoYT);
        yth.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdapterSearch.this.context, VideoActivity.class);
                intent.putExtra("videoid",videoYT.getResourceId().getVideoId());
                intent.putExtra("title",videoYT.getSnippetYT().getTitle());
                intent.putExtra("description",videoYT.getSnippetYT().getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return videoList.size();
    }



}
