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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwaretechit.R;
import com.softwaretechit.adapter.AdapterHome;
import com.softwaretechit.models.ModelHome;
import com.softwaretechit.models.VideoYT;
import com.softwaretechit.network.YouTubeAPI;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideoFragment extends Fragment  {
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

   private AdapterHome adapterHome;
   private LinearLayoutManager manager;
   private List<VideoYT> videoYTList=new ArrayList<>();
   public String playlistid=YouTubeAPI.playlistid;
   private ShimmerFrameLayout loding1,loding2;
   private boolean isScroll=false;
   private int currentItem,totalItem,scrollOutItem;
   private String nextPageToken="";
    RecyclerView rv;
    ImageView errorimg;

   public VideoFragment() {

   }
    public String getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(String playlistid) {
        this.playlistid = playlistid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
            errorimg=view.findViewById(R.id.neterrorimg);
            loding1 = view.findViewById(R.id.shimmer1);
            loding2 = view.findViewById(R.id.shimmer2);
            rv = view.findViewById(R.id.recyclehome);

        ConnectivityManager connectivityManager= (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo==null||!networkInfo.isConnected()||!networkInfo.isAvailable()) {
            Toast.makeText(getContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
            errorimg.setVisibility(View.VISIBLE);
        }else {

            //this block containts Ads code  mInterstitialAd Start
            mInterstitialAd = new InterstitialAd(getContext());
            mInterstitialAd.setAdUnitId(getString(R.string.InterstitialAd_id));

            AdRequest iadRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(iadRequest);
            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    prepearAd();
                }
            });
            //this block containts Ads code End

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


            errorimg.setVisibility(View.GONE);
            adapterHome = new AdapterHome(view.getContext(), videoYTList);
            manager = new LinearLayoutManager(view.getContext());
        }
       return view;
    }


  private void data()
    {
        rv.setAdapter(adapterHome);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));


        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    isScroll=true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem=manager.getChildCount();
                totalItem=manager.getItemCount();
                scrollOutItem=manager.findFirstVisibleItemPosition();

                if (isScroll&&(currentItem+scrollOutItem==totalItem)){
                    isScroll=false;

                }

            }
        });
        if(videoYTList.size()==0){
            getJson();
        }


    }


    //this block containts Ads mInterstitialAd code method
    public void prepearAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
    //this block containts Ads mInterstitialAd code end

    private void getJson() {
      loding1.setVisibility(View.VISIBLE);

     String url= YouTubeAPI.BASE_URL+YouTubeAPI.plstitems+YouTubeAPI.Key+"&playlistId="+getPlaylistid()+YouTubeAPI.mx+YouTubeAPI.ord+YouTubeAPI.part;
        if (nextPageToken!=""){
            url=url+YouTubeAPI.NPT+nextPageToken;
            loding1.setVisibility(View.GONE);
            loding2.setVisibility(View.VISIBLE);
        }
        if (nextPageToken==null)
        {
            return;
        }
        Call<ModelHome> data=YouTubeAPI.getHomeVideo().getyt(url);
        data.enqueue(new Callback<ModelHome>() {
            @Override
            public void onResponse(Call<ModelHome> call, Response<ModelHome> response) {
                if (response.errorBody()!=null){
                    Log.w("correct", "onResponse: "+response.errorBody() );
                    loding1.setVisibility(View.GONE);
                    loding2.setVisibility(View.GONE);
                    Toast.makeText(getContext(),response.errorBody().toString(),Toast.LENGTH_LONG).show();
                }else{
                    ModelHome mh=response.body();
                    videoYTList.addAll(mh.getItems());
                    adapterHome.notifyDataSetChanged();
                    loding1.setVisibility(View.GONE);
                    loding2.setVisibility(View.GONE);
                    if (mh.getNextPageToken()!=null){
                        nextPageToken=mh.getNextPageToken();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelHome> call, Throwable throwable) {
                Log.e("error", "onFailure: ",throwable );
                //loding2.setVisibility(View.VISIBLE);
                //Toast.makeText(getContext(),"Problem to load",Toast.LENGTH_LONG).show();



            }
        });

   }


    @Override
    public void onResume() {
        super.onResume();
        data();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
