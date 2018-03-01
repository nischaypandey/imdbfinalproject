package com.example.user.imdm_final_project;

import android.graphics.Bitmap;

class StoringDataConstants {

    public String storeid;
    public String movieId;
    public String storename;
    public String Releasedate;
    public String popularity;
    public String votecount;
    public String voteAverage;
    public Bitmap storedimage;

    public String  getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getReleasedate() {
        return Releasedate;
    }

    public void setReleasedate(String releasedate) {
        Releasedate = releasedate;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVotecount() {
        return votecount;
    }

    public void setVotecount(String votecount) {
        this.votecount = votecount;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Bitmap getStoredimage() {
        return storedimage;
    }

    public void setStoredimage(Bitmap storedimage) {
        this.storedimage = storedimage;
    }
}
