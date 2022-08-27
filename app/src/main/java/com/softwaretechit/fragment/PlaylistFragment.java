package com.softwaretechit.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwaretechit.R;
import com.softwaretechit.adapter.AdapterPlaylist;
import com.softwaretechit.models.ModelPlaylist;
import com.softwaretechit.models.PlaylistItems;
import com.softwaretechit.network.YouTubeAPI;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment {
    private AdView mAdView;

    private AdapterPlaylist adapter;
    private LinearLayoutManager manager;
    private List<PlaylistItems> playList=new ArrayList<>();
    private ShimmerFrameLayout loding1,loding2;
    private boolean isScroll=false;
    private int currentItem,totalItem,scrollOutItem;
    private String nextPageToken="";

    public PlaylistFragment() {
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
            view = inflater.inflate(R.layout.fragment_playlist, container, false);
            RecyclerView rv = view.findViewById(R.id.recycle_playlist);
            loding1 = view.findViewById(R.id.shimmer1);
            loding2 = view.findViewById(R.id.shimmer2);
            adapter = new AdapterPlaylist(getContext(), playList);
            manager = new LinearLayoutManager(getContext());
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

            rv.setAdapter(adapter);
            rv.setLayoutManager(manager);
            rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        isScroll = true;
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    currentItem = manager.getChildCount();
                    totalItem = manager.getItemCount();
                    scrollOutItem = manager.findFirstVisibleItemPosition();
                    if (isScroll && (currentItem + scrollOutItem == totalItem)) {
                        isScroll = false;
                    }
                }
            });
            if (playList.size() == 0) {

                getJson();

            }

        }
        return view;

    }


    public void getJson() {
        loding1.setVisibility(View.VISIBLE);

            String url = YouTubeAPI.BASE_URL + YouTubeAPI.ply + YouTubeAPI.Key + YouTubeAPI.chid + YouTubeAPI.part_plst + YouTubeAPI.mx;

      if (nextPageToken!=""){
            url=url+YouTubeAPI.NPT+nextPageToken;
            loding1.setVisibility(View.GONE);
            loding2.setVisibility(View.VISIBLE);
        }
        if (nextPageToken==null)
        {
            return;
        }
        Call<ModelPlaylist>data=YouTubeAPI.getPlaylist().getyt(url);
        data.enqueue(new Callback<ModelPlaylist>() {
            @Override
            public void onResponse(Call<ModelPlaylist> call, Response<ModelPlaylist> response) {
                if (response.errorBody()!=null)
                {
                    Log.d("onResponse", "onResponse: "+response.errorBody());
                    loding1.setVisibility(View.GONE);
                    loding2.setVisibility(View.GONE);
                    Toast.makeText(getContext(),response.errorBody().toString(),Toast.LENGTH_LONG).show();
                }else {
                    ModelPlaylist mp=response.body();
                    playList.addAll(mp.getItems());
                    adapter.notifyDataSetChanged();
                    loding1.setVisibility(View.GONE);
                    loding2.setVisibility(View.GONE);
                    if (mp.getNextPageToken()!=null){
                        nextPageToken=mp.getNextPageToken();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelPlaylist> call, Throwable t) {

                Log.e("error playlist load", "onFailure: Playlist ",t );
                loding2.setVisibility(View.VISIBLE);
                loding1.setVisibility(View.GONE);
                loding2.setVisibility(View.GONE);
                Toast.makeText(getContext(),"No Internet Connection",Toast.LENGTH_LONG).show();

            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
