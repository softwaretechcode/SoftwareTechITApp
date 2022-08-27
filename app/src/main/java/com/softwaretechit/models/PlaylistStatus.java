package com.softwaretechit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaylistStatus {
    @SerializedName("privacyStatus")
    @Expose
   private String PrivacyStatus;

    public PlaylistStatus() {
    }

    public PlaylistStatus(String privacyStatus) {
        PrivacyStatus = privacyStatus;
    }

    public String getPrivacyStatus() {
        return PrivacyStatus;
    }

    public void setPrivacyStatus(String privacyStatus) {
        PrivacyStatus = privacyStatus;
    }
}
