package com.softwaretechit.network;

import com.softwaretechit.models.ModelChannel;
import com.softwaretechit.models.ModelHome;
import com.softwaretechit.models.ModelPlaylist;
import com.softwaretechit.models.ModelSearch;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class YouTubeAPI {
//    String url= YouTubeAPI.BASE_URL +
//            YouTubeAPI.ply+YouTubeAPI.Key+YouTubeAPI.chid+
//            YouTubeAPI.part_plst+YouTubeAPI.mx;
    //https://www.googleapis.com/youtube/v3/playlists?key=AIzaSyC4b0Bri4yU8nrxU0_Km1clN2sypINOXqs
    // &channelId=UC0hN-IY4VljTmvq-FB6rOYg&part=snippet,contentDetails&maxResults=25

    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    public static final String plstitems = "playlistItems?";
    private static final String keyp="AIzaSyC4b0Bri4yU8nrxU0_Km1clN2sypINOXqs";
    public static final String Key = "key="+keyp;
    public static final String CHANNEL_ID="UCLU4EFzGkjsRKrPKD8bhG-A";
    public static final String chid = "&channelId="+CHANNEL_ID;
    public  static final String playlistid="UULU4EFzGkjsRKrPKD8bhG-A";
    public static final String mx = "&maxResults=25";
    public static final String ord = "&sort=date";
    public static final String part = "&part=snippet";
    public static final String sch="search?";
    public static final String ply="playlists?";
    public static final String part_plst="&part=snippet,contentDetails,status";
    public static final String query="&q=";
    public static final String type="&type=video";
    public static final String NPT="&PageToken=";
    public static final String IDC="&id="+CHANNEL_ID;
    public static final String ch_part="&part=snippet,statistics,brandingSettings";
    public static final String CH="channels?";

    public static String getKeyp() {
        return keyp;
    }


    public interface HomeVideo {
        @GET
        Call<ModelHome> getyt(@Url String url);
    }
    public interface SearchVideo {
        @GET
         Call<ModelSearch> getyt(@Url String url);
    }
    public interface PlaylistVideo{
        @GET
        Call<ModelPlaylist>getyt(@Url String url);
    }
    public interface ChannelInfo{
        @GET
        Call<ModelChannel>getyt(@Url String url);
    }

    private static HomeVideo homeVideo = null;
    private static SearchVideo searchVideo = null;
    private static PlaylistVideo playlist=null;
    private static ChannelInfo  channelInfo=null;

    public static HomeVideo getHomeVideo() {
        if (homeVideo == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            homeVideo = retrofit.create(HomeVideo.class);

        }
        return homeVideo;
    }

    public static SearchVideo getSearchVideo() {
        if (searchVideo == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            searchVideo = retrofit.create(SearchVideo.class);

        }
        return searchVideo;
    }
    public static PlaylistVideo getPlaylist() {
        if (playlist== null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            playlist = retrofit.create(PlaylistVideo.class);

        }
        return playlist;
    }
    public static ChannelInfo getChannelInfo(){
        if (channelInfo==null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            channelInfo = retrofit.create(ChannelInfo.class);
        }
        return channelInfo;
    }

}


