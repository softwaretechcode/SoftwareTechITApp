package com.softwaretechit.activity;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telecom.ConnectionService;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.softwaretechit.R;
import com.softwaretechit.fragment.HomeFragment;
import com.softwaretechit.fragment.PlaylistFragment;
import com.softwaretechit.fragment.ProfileFragment;
import com.softwaretechit.fragment.SearchFragment;
import com.softwaretechit.fragment.VideoFragment;

public class WebActivity extends AppCompatActivity {
    private HomeFragment homeFragment= new HomeFragment();
    private PlaylistFragment playlistFragment=new PlaylistFragment();
    private SearchFragment searchFragment=new SearchFragment();
    private ProfileFragment profileFragment=new ProfileFragment();
    private VideoFragment videoFragment=new VideoFragment();
    private AdView mAdView;
    private BottomNavigationView base_menuweb;
    WebView webView;
    WebSettings webSettings;
    ConnectionService connectionService;
    Context context;
    FloatingActionButton floatingActionButton;
    String weblink = null;
    FragmentTransaction ft;
//    TextView nav_text;
    private long backPressTime;
    private Toast backToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//            view = inflater.inflate(R.layout.sample_no_network, container, false);
        } else {

//            view = inflater.inflate(R.layout.fragment_webblog, container, false);
            Intent intent = getIntent();
            webView = findViewById(R.id.webview_act);
            floatingActionButton = findViewById(R.id.floatBtn_webact);
            base_menuweb=findViewById(R.id.menu_baseweb);
            webSettings = webView.getSettings();
            weblink= intent.getStringExtra("name");
            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl(weblink);
            webView.setWebViewClient(new WebViewClient());

            base_menuweb.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    if (menuItem.isChecked()) {
                        return true;
                    } else {
                        switch (menuItem.getItemId()) {

                            case R.id.shop_web_menu:

                                Intent intent1=new Intent(getApplicationContext(),WebActivity.class);
                                intent1.putExtra("name", "https://shop.softwaretechit.com");
                                startActivity(intent1);
                                return true;
                            case R.id.website_web_menu:
                                Intent intent2=new Intent(getApplicationContext(),WebActivity.class);
                                intent2.putExtra("name", "https://www.softwaretechit.com");
                                startActivity(intent2);
                                return true;
                        }

                    }
                    return true;
                }
            });


            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    webView.goBack();

                }
            });

            //#### this block containts Banner Ads code start
            mAdView = findViewById(R.id.adView2);
            MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {

                }
            });

            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            //#### this block containts Banner Ads code End
        }
    }

    @Override
    public void onBackPressed() {

        if (backPressTime+2000>System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backToast= Toast.makeText(getBaseContext(),"Press Back Again to Exit",Toast.LENGTH_SHORT);
            backToast.show();


        }
        backPressTime = System.currentTimeMillis();
    }

}