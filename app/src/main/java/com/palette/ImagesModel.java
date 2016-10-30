package com.palette;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hari Prasad on 10/30/16.
 */

public class ImagesModel {
    private static final String TAG = "ImagesModel";
    @SerializedName("albumId")
    @Expose
    private Integer albumId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;

    /**
     * @return The albumId
     */
    public Integer getAlbumId() {
        return albumId;
    }

    /**
     * @param albumId The albumId
     */
    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The thumbnailUrl
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * @param thumbnailUrl The thumbnailUrl
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public static ImagesModel fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, ImagesModel.class);
    }

    public JSONObject toJson() {
        String jsonRepresentation = new Gson().toJson(this, ImagesModel.class);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonRepresentation);
        } catch (JSONException e) {
            Log.e(TAG, "toJson: " + e.getLocalizedMessage());
        }
        return jsonObject;
    }
}
