package com.softwaretechit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelPlaylist {
    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;

    @SerializedName("items")
    @Expose
    private List<PlaylistItems>items;

    public ModelPlaylist() {
    }

    public ModelPlaylist(List<PlaylistItems> items) {

        this.items = items;
    }

    public List<PlaylistItems> getItems() {
        return items;
    }

    public void setItems(List<PlaylistItems> items) {
        this.items = items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

}
