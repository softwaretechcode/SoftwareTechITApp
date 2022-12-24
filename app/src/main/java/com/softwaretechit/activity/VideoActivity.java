package com.softwaretechit.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.softwaretechit.R;
import com.softwaretechit.network.YouTubeAPI;

public class VideoActivity extends YouTubeBaseActivity {
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    YouTubePlayerView player;
    String video_code="";
    TextView title,disciption;
    Button subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        title= findViewById(R.id.playtitle);
        player= findViewById(R.id.videoplayer);
        disciption= findViewById(R.id.disc);
        subscribe=findViewById(R.id.subscribe);

        //#### this block containts Banner Ads code start
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //#### this block containts Banner Ads code End

        //this block containts Ads code  mInterstitialAd Start
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.InterstitialAd_id));

        AdRequest iadRequest = new AdRequest.Builder().build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(iadRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                prepearAd();
            }
        });
        //this block containts Ads code End
         subscribe.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Uri uri= Uri.parse("https://www.youtube.com/channel/UCLU4EFzGkjsRKrPKD8bhG-A?sub_confirmation=1");
                 Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                 startActivity(intent);
             }
         });

        video_code=getIntent().getStringExtra("videoid");
        title.setText(getIntent().getStringExtra("title"));
        disciption.setText(getIntent().getStringExtra("description"));
        player.initialize(YouTubeAPI.getKeyp(), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b)
                {
                    youTubePlayer.loadVideo(video_code);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(VideoActivity.this,youTubeInitializationResult.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    //this block containts Ads mInterstitialAd code method
    public void prepearAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
    //this block containts Ads mInterstitialAd code end



}
