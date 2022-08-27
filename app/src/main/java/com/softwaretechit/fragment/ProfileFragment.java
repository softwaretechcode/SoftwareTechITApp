package com.softwaretechit.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.softwaretechit.R;
import com.softwaretechit.models.ChannelList;
import com.softwaretechit.models.ModelChannel;
import com.softwaretechit.network.YouTubeAPI;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {
    private AdView mAdView;

    private ImageView banner,logo;
    private TextView chname,subcount,description,videocount,viewcount,publishat;
    private boolean isScroll=false;
    private int currentItem,totalItem,scrollOutItem;
    private String nextPageToken="";
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;

        ConnectivityManager connectivityManager= (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo==null||!networkInfo.isConnected()||!networkInfo.isAvailable()) {
            Toast.makeText(getContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
            view=inflater.inflate(R.layout.sample_no_network,container,false);
        }else {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_profile, container, false);
            banner = view.findViewById(R.id.banner);
            logo = view.findViewById(R.id.logo);
            chname = view.findViewById(R.id.chname);
            subcount = view.findViewById(R.id.subcount);
            description = view.findViewById(R.id.discription);
            videocount = view.findViewById(R.id.v_count);
            viewcount = view.findViewById(R.id.view_count);
            publishat = view.findViewById(R.id.publish_at);
            getJonAPI();

           //#### this block containts Banner Ads code start
            mAdView = view.findViewById(R.id.adView);
            MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {

                }
            });

            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            //#### this block containts Banner Ads code End

        }
        return view;
    }

    private void getJonAPI() {
        String url= YouTubeAPI.BASE_URL+YouTubeAPI.CH+YouTubeAPI.Key
                +YouTubeAPI.IDC+YouTubeAPI.ch_part+YouTubeAPI.ord;
        Call<ModelChannel> yt=YouTubeAPI.getChannelInfo().getyt(url);
        yt.enqueue(new Callback<ModelChannel>() {
            @Override
            public void onResponse(Call<ModelChannel> call, Response<ModelChannel> response) {
                if(response.errorBody()!=null){
                    Log.e("errorResponce","onResponce :");
                }else {
                    if (response.body()!=null)
                    {
                        ModelChannel mc=response.body();
                        setData(mc.getItems().get(0));
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelChannel> call, Throwable throwable) {
                //loding2.setVisibility(View.VISIBLE);
                //loding1.setVisibility(View.GONE);
                //loding2.setVisibility(View.GONE);
                Toast.makeText(getContext(),"No Internet Connection",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setData(ChannelList c) {
       chname.setText(c.getSnippet().getTitle());
       subcount.setText(c.getStatistics().getSubscribeCount()+" Subscribers");
       description.setText(c.getSnippet().getDescription());
       viewcount.setText("Channel Viewed: "+c.getStatistics().getViewCount());
       videocount.setText("Videos in Channel: "+c.getStatistics().getVideoCount());
       publishat.setText("Publish At: "+c.getSnippet().getPublishedAt());

        Picasso.get()
                .load(c.getSnippet().getThumbnails().getMedium().getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(logo, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e("success","onSuccess");

                    }

                    @Override
                    public void onError(Exception e) {

                        Log.e("error","onError",e);

                    }
                });
            Picasso.get()
                .load(c.getBrandingSettings().getImageBranding().getBannerImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(banner, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e("success","onSuccess");

                    }

                    @Override
                    public void onError(Exception e) {

                        Log.e("error","onError",e);
                    }
                });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
