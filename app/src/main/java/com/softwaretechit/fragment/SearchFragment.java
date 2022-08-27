package com.softwaretechit.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwaretechit.R;
import com.softwaretechit.adapter.AdapterSearch;
import com.softwaretechit.models.ModelSearch;
import com.softwaretechit.models.SearchYT;
import com.softwaretechit.network.YouTubeAPI;
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


public class SearchFragment extends Fragment {

    private AdapterSearch adapterSearch;
    private LinearLayoutManager manager;
    private List<SearchYT> videoList=new ArrayList<>();
    SearchView searchbtn;
    ImageView errorimg;
    private AdView mAdView;



    public SearchFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_search, container, false);

         RecyclerView rv = view.findViewById(R.id.searchview);
        errorimg = view.findViewById(R.id.searcherror);
        searchbtn = view.findViewById(R.id.searchbar);

        ConnectivityManager connectivityManager= (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo==null||!networkInfo.isConnected()||!networkInfo.isAvailable()) {
            Toast.makeText(getContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
            errorimg.setVisibility(View.VISIBLE);
        }else {
            errorimg.setVisibility(View.GONE);

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

            adapterSearch = new AdapterSearch(view.getContext(), videoList);
            manager = new LinearLayoutManager(view.getContext());
            rv.setAdapter(adapterSearch);
            rv.setLayoutManager(manager);

            searchbtn.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (!TextUtils.isEmpty(searchbtn.getQuery())) {
                        if (adapterSearch.getItemCount() == 0) {
                            getJson(searchbtn.getQuery().toString());

                        } else {
                            videoList.removeAll(videoList);
                            adapterSearch.notifyItemRangeRemoved(0, adapterSearch.getItemCount());
                            getJson(searchbtn.getQuery().toString());
                        }

                    } else {
                        Toast.makeText(getContext(), "You need to enter text", Toast.LENGTH_LONG).show();
                    }

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    return false;
                }
            });
        }
        return view;
    }

    private void getJson(String query) {
            String url = YouTubeAPI.BASE_URL + YouTubeAPI.sch + YouTubeAPI.Key +YouTubeAPI.chid+ YouTubeAPI.mx + "&sort=date"
                    + YouTubeAPI.part + YouTubeAPI.query + query + YouTubeAPI.type;
           Call<ModelSearch> data = YouTubeAPI.getSearchVideo().getyt(url);
            data.enqueue(new Callback<ModelSearch>() {
                @Override
                public void onResponse(Call<ModelSearch> call, Response<ModelSearch> response) {

                        if (response.errorBody() != null) {
                            Log.w("correct", "onResponse: " + response.errorBody().toString());

                        } else {
                            ModelSearch mh = response.body();

                            if (mh.getItems().size() != 0) {
                                videoList.addAll(mh.getItems());
                                adapterSearch.notifyDataSetChanged();
                            } else {

                                Toast.makeText(getContext(), "No video found", Toast.LENGTH_LONG).show();
                            }
                        }


                }
                @Override
                public void onFailure(Call<ModelSearch> call, Throwable throwable) {
                    Log.e("error", "onFailure: ", throwable);

                        Toast.makeText(getContext(),"Problem to load",Toast.LENGTH_LONG).show();

                    }


            });
        }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
