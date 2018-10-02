package com.dicoding.mymovie;

import android.util.Log;

import org.json.JSONObject;

public class MovieItems {

    int id;
    String title;
    String overview;
    String releaseDate;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    String posterPath;

    String TAG = MovieItems.class.getSimpleName();

    public MovieItems(JSONObject object){
        try{
            int id = object.getInt("id");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String releaseDate = object.getString("release_date");
            String posterPath = object.getString("poster_path");
            this.id = id;
            this.title = title;
            this.overview = overview;
            this.releaseDate = releaseDate;
            this.posterPath = posterPath;
            Log.e(TAG, "MovieItems: Try");
        } catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "MovieItems: Error");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}