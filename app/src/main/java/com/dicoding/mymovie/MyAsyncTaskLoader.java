package com.dicoding.mymovie;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {

    ArrayList<MovieItems> mData;
    boolean mHashRestult = false;
    String TAG = MyAsyncTaskLoader.class.getSimpleName();

    String mListMovie;
    private static final String API_KEY = "d493dc12751ee1c430c9fe295866dbda";
    private static final String URL = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language=en-US&query=";

    public MyAsyncTaskLoader(final Context context, String listMovie) {
        super(context);

        onContentChanged();
        this.mListMovie = listMovie;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHashRestult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<MovieItems> data) {
        mData = data;
        mHashRestult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHashRestult) {
            mData = null;
            mHashRestult = false;
        }
    }

//    private static final String API_KEY = "d493dc12751ee1c430c9fe295866dbda";

    @Override
    public ArrayList<MovieItems> loadInBackground() {
        final ArrayList<MovieItems> movieItemes = new ArrayList<>();
        SyncHttpClient client = new SyncHttpClient();
        client.get(URL + mListMovie, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    Log.e(TAG, "onSuccess: success" );
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++){
                        JSONObject movie = list.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        movieItemes.add(movieItems);
                        Log.e(TAG, "onSuccess: loop");
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //do nothing
                Log.e(TAG, "onFailure: failed" +error);
            }
        });

        return movieItemes;
    }
}