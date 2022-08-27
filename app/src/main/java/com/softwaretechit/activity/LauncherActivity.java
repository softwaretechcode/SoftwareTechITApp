package com.softwaretechit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.softwaretechit.R;

public class LauncherActivity extends AppCompatActivity {
    private static int SLASH_SCREEN=3000;

    Animation topAnim,buttomAnim;
    ImageView img;
    TextView logo,slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launcher);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        buttomAnim= AnimationUtils.loadAnimation(this,R.anim.buttom_animation);

        img=findViewById(R.id.lnch_logo);
        logo=findViewById(R.id.lnch_logotxt);
        slogan=findViewById(R.id.lnch_slogn);

        img.setAnimation(topAnim);

        logo.setAnimation(buttomAnim);
        slogan.setAnimation(buttomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent=new Intent(LauncherActivity.this,MainActivity.class);
               startActivity(intent);
               finish();
            }
        },SLASH_SCREEN);

    }
}
