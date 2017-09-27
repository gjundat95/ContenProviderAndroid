package com.jundat95.contactandroid.repository.local.model;

import android.graphics.Bitmap;

/**
 * Created by tinhngo on 27/09/2017.
 */

public class MusicModel {

    private String title;
    private String path;
    private String artist;
    private String duration;
    private Bitmap photo;

    public MusicModel(String title, String path, String artist, String duration, Bitmap photo) {
        this.title = title;
        this.path = path;
        this.artist = artist;
        this.duration = duration;
        this.photo = photo;
    }

    public MusicModel(String title, String path, String artist, String duration) {
        this.title = title;
        this.path = path;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
