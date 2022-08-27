package com.softwaretechit.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telecom.ConnectionService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.softwaretechit.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WebBlog extends Fragment  {
    private AdView mAdView;

    WebView webView;
    WebSettings webSettings;
    ConnectionService connectionService;
    Context context;
    FloatingActionButton floatingActionButton;
    String weblink;

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        ConnectivityManager connectivityManager= (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo==null||!networkInfo.isConnected()||!networkInfo.isAvailable()) {
            Toast.makeText(getContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
            view=inflater.inflate(R.layout.sample_no_network,container,false);
        }else {
            view=inflater.inflate(R.layout.fragment_webblog,container,false);
            webView=view.findViewById(R.id.webview);
            floatingActionButton=view.findViewById(R.id.floatingActionButton);
            webSettings = webView.getSettings();

            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl(getWeblink());
            webView.setWebViewClient(new WebViewClient());

            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    webView.goBack();

                }
            });

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
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
