package com.softwaretechit.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.softwaretechit.R;


public class HomeFragment extends Fragment {
LinearLayout l_video,l_website,l_social;
    CardView card_Banner,card_blog,card_store,card_algblog,card_crazypyar,card_business,card_earn,card_news,card_playstore,card_program,card_product_review,card_songlyrics,
            card_techblog,card_gift,card_tips,card_links,card_imgeditor,card_bookmark,card_ide,card_checklist,card_video,card_hashtags,card_seotools,card_apkdownload,card_encript,card_playlist;
CardView card_more;
    WebBlog webBlog=new WebBlog();


    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        card_Banner=view.findViewById(R.id.banner_card);
        card_more=view.findViewById(R.id.more_card);
        l_video=view.findViewById(R.id.l_video);
        l_website=view.findViewById(R.id.l_website);
        l_social=view.findViewById(R.id.l_social);
        //menu
        card_blog=view.findViewById(R.id.sc_c1);
        card_program=view.findViewById(R.id.sc_c3);
        card_ide=view.findViewById(R.id.sc_c2);
        card_playlist=view.findViewById(R.id.sc_c4);
        card_store=view.findViewById(R.id.sc2_c1);
        card_algblog=view.findViewById(R.id.sc2_c3);
        card_crazypyar=view.findViewById(R.id.sc2_c2);
        card_business=view.findViewById(R.id.sc2_c4);
        card_earn=view.findViewById(R.id.c_earn);
        card_news=view.findViewById(R.id.sc2_c6);
        card_playstore=view.findViewById(R.id.c_play_store);
        card_product_review=view.findViewById(R.id.sc2_c5);
        card_songlyrics=view.findViewById(R.id.sc2_c8);
        card_techblog=view.findViewById(R.id.sc2_c7);
        card_gift=view.findViewById(R.id.c_gift);
        card_tips=view.findViewById(R.id.c_tips);
        card_links=view.findViewById(R.id.c_link);
        card_imgeditor=view.findViewById(R.id.c_image_editor);
        card_bookmark=view.findViewById(R.id.c_bookmark);
        card_checklist=view.findViewById(R.id.c_check_list);
        card_video=view.findViewById(R.id.sc_c6);
        card_hashtags=view.findViewById(R.id.c_hashtag);
        card_seotools=view.findViewById(R.id.c_seotools);
        card_apkdownload=view.findViewById(R.id.c_apkdownload);
        card_encript=view.findViewById(R.id.c_encript);
//*****First Block Menu Dashboard*****
        l_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                VideoFragment videoFragment=new VideoFragment();
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, videoFragment).addToBackStack(null).commit();

            }
        });
        l_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://home.softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });

        l_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://home.softwaretechit.com/follow-on");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
//Dashboard Banner
        card_Banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
//Menu Cards Starts
        card_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://blog.softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                PlaylistFragment playlistFragment=new PlaylistFragment();
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, playlistFragment).addToBackStack(null).commit();

            }
        });
        card_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://shop.softwaretechit.com");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_algblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://algblog.softwaretechit.com");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_crazypyar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://crazypyar.softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://golbar.net/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://news.softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_playstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Uri uri= Uri.parse("https://play.google.com/store/apps/details?id=com.softwaretechit");
                Intent intent=new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        card_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://programadecode.softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_product_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://productsellermarket.softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_songlyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://songlyricsword-a2z.softwaretechit.com");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_techblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("http://techblog.softwaretechit.com");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://toolword360.softwaretechit.com");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://algblog.softwaretechit.com");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://home.softwaretechit.com");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_imgeditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("http://minipaint.softwaretechit.com");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://tools.softwaretechit.com");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_ide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://compiler.softwaretechit.com");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://softwaretechit.com");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                VideoFragment videoFragment=new VideoFragment();
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, videoFragment).addToBackStack(null).commit();

            }
        });
        card_hashtags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://hashtags.softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_seotools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://seotools.softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_apkdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://freeapkdownload.softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });
        card_encript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("http://encryptdecrypt.softwaretechit.com/");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });

        //########################
        card_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                webBlog.setWeblink("https://home.softwaretechit.com/open-account");
                activity.getSupportFragmentManager().beginTransaction().add(R.id.main_frag, webBlog).addToBackStack(null).commit();

            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}