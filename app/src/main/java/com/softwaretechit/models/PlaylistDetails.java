package com.softwaretechit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaylistDetails {
    @SerializedName("itemCount")
    @Expose
    private int itemCount;

    public PlaylistDetails() {
    }

    public PlaylistDetails(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
