package com.softwaretechit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaylistSnippet {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("thumbnails")
    @Expose
    private ThumbnailsYT thumbnails;

    public PlaylistSnippet() {
    }

    public PlaylistSnippet(String title, ThumbnailsYT thumbnails) {
        this.title = title;
        this.thumbnails = thumbnails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ThumbnailsYT getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ThumbnailsYT thumbnails) {
        this.thumbnails = thumbnails;
    }
}
