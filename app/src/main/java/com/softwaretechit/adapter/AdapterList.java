package com.softwaretechit.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwaretechit.R;
import com.softwaretechit.activity.VideoActivity;
import com.softwaretechit.models.VideoYT;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter<AdapterList.YoutubeHolder> {
    private Context context;
    String gettitle,getvideo;
    private List<VideoYT> videoList;

    public AdapterList(Context context, List<VideoYT> videoList) {
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

        public void setData(VideoYT data) {

             gettitle = data.getSnippetYT().getTitle();
            String getigt = data.getSnippetYT().getPublishedAt();
              getvideo = data.getSnippetYT().getResourceId().getVideoId();
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
     final VideoYT videoYT= videoList.get(position);
     YoutubeHolder yth= viewHolder;
     yth.setData(videoYT);
     yth.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent =new Intent(AdapterList.this.context, VideoActivity.class);
             intent.putExtra("videoid",videoYT.getSnippetYT().getResourceId().getVideoId());
             intent.putExtra("title",videoYT.getSnippetYT().getTitle());
             intent.putExtra("discription",videoYT.getSnippetYT().getDescription());
             context.startActivity(intent);
             Toast.makeText(AdapterList.this.context,"Video Playing : "+position,Toast.LENGTH_LONG).show();
         }
     });
    }

    @Override
    public int getItemCount() {

        return videoList.size();
    }



}
