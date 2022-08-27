package com.softwaretechit.activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.softwaretechit.R;
import com.softwaretechit.fragment.HomeFragment;
import com.softwaretechit.fragment.PlaylistFragment;
import com.softwaretechit.fragment.ProfileFragment;
import com.softwaretechit.fragment.SearchFragment;
import com.softwaretechit.fragment.VideoFragment;
import com.softwaretechit.fragment.WebBlog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  {
    private Toolbar toolbar;
    private long backPressTime;
    private Toast backToast;
    private HomeFragment homeFragment= new HomeFragment();
    private PlaylistFragment playlistFragment=new PlaylistFragment();
    private SearchFragment searchFragment=new SearchFragment();
    private ProfileFragment profileFragment=new ProfileFragment();
    private VideoFragment videoFragment=new VideoFragment();
    private WebBlog webBlog=new WebBlog();
    private BottomNavigationView menuBase;
    NavigationView navigationView;
    FragmentTransaction ft;
    TextView nav_text;
    ImageView rightIcon,leftIcon;
    DrawerLayout drawerLayout;
    Intent intent;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_drawer);
        menuBase = findViewById(R.id.menu_base);
        toolbar = (Toolbar) findViewById(R.id.mytool);
        nav_text = findViewById(R.id.nav_text);
        drawerLayout = findViewById(R.id.drawer_layout);
        leftIcon = findViewById(R.id.leftmenuicon);
        rightIcon = findViewById(R.id.rightmenuicon);
        setFragment(homeFragment);
        nav_text.setText("Home");
        navigationView =findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(searchFragment);
                nav_text.setText("Search");
            }
        });

        menuBase.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.isChecked()) {

                    return true;
                } else {

                    switch (menuItem.getItemId()) {
                        case R.id.menu_home:
                            setFragment(homeFragment);
                            nav_text.setText("Home");
                            ft.addToBackStack(null);
                            return true;


                        case R.id.menu_playlist:
                            setFragment(playlistFragment);
                            nav_text.setText("Playlist");
                            ft.addToBackStack(null);
                            return true;

                        case R.id.menu_video:
                            setFragment(videoFragment);
                            nav_text.setText("Video");
                            ft.addToBackStack(null);
                            return true;

//                        case R.id.menu_search:
//                            setFragment(searchFragment);
//                            ft.addToBackStack(null);
//                            nav_text.setText("Search");
//                            return true;
                        case R.id.menu_shop:
                            webBlog.setWeblink("https://shop.softwaretechit.com");
                            setFragment(webBlog);
                            ft.addToBackStack(null);
                            nav_text.setText("Shop");
                            return true;


                        case R.id.menu_blog:
                            webBlog.setWeblink("https://www.softwaretechit.com");
                            setFragment(webBlog);
                            ft.addToBackStack(null);
                            nav_text.setText("Website");
                            return true;

                        default:
                            setFragment(homeFragment);
                            nav_text.setText("Home");
                            ft.addToBackStack(null);
                            return true;
                    }
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                        Toast.makeText(getApplicationContext(),"Home ",Toast.LENGTH_SHORT).show();
                        nav_text.setText("Home");
                        setFragment(homeFragment);
                        break;
                    case R.id.nav_video:
                        Toast.makeText(getApplicationContext(),"Videos ",Toast.LENGTH_SHORT).show();
                        nav_text.setText("Video");
                        setFragment(videoFragment);
                        break;
                    case R.id.nav_playlist:
                        Toast.makeText(getApplicationContext(),"Playlist ",Toast.LENGTH_SHORT).show();
                        nav_text.setText("Playlist");
                        setFragment(playlistFragment);
                        break;
                    case R.id.nav_profile:
                        Toast.makeText(getApplicationContext(),"Profile ",Toast.LENGTH_SHORT).show();
                        nav_text.setText("Profile");
                        setFragment(profileFragment);
                        break;
                    case R.id.nav_softwaretechit:
                        Toast.makeText(getApplicationContext(),"SoftwareTechIT YouTube Channel ",Toast.LENGTH_SHORT).show();
                         uri=Uri.parse("https://www.youtube.com/results?search_query=softwaretechit");
                         intent=new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                        break;
                    case R.id.nav_traveling:
                        Toast.makeText(getApplicationContext(),"Entertening  Video",Toast.LENGTH_SHORT).show();
                         uri=Uri.parse("https://www.youtube.com/results?search_query=madforyou");
                         intent=new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                        break;
                    case R.id.nav_trading:
                        Toast.makeText(getApplicationContext(),"AlgBlog Video ",Toast.LENGTH_SHORT).show();
                        uri=Uri.parse("https://www.youtube.com/results?search_query=algblog");
                        intent=new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                        break;
                    case R.id.nav_link:
                        Toast.makeText(getApplicationContext(),"Website Links ",Toast.LENGTH_SHORT).show();
                        uri=Uri.parse("https://home.softwaretechit.com");
                        intent=new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                        break;
                    case R.id.nav_rate:
                        Toast.makeText(getApplicationContext(),"Rating Our App ",Toast.LENGTH_SHORT).show();
                        uri=Uri.parse("https://blog.softwaretechit.com/p/download.html");
                        intent=new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

    private void setFragment(Fragment fragment) {
        ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frag,fragment).commit();
        ft.addToBackStack(null);

    }

    @Override
    public void onBackPressed() {

        if (backPressTime+2000>System.currentTimeMillis()) {
           backToast.cancel();
            super.onBackPressed();
            return;
        }else {
           backToast= Toast.makeText(getBaseContext(),"Press Back Again to Exit",Toast.LENGTH_SHORT);
           backToast.show();


        }
        backPressTime = System.currentTimeMillis();
    }


}
