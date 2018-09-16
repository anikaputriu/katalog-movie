package com.dicoding.mymovie;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>{

    ListView listView;
    MovieAdapter adapter;

    EditText etMovie;
    Button btnSearch;

    public String EXTRAS_MOVIE = "EXTRAS_MOVIE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();

        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

        etMovie = findViewById(R.id.et_movie);
        btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(clickListener);


        String movie = etMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, movie);

        getSupportLoaderManager().initLoader(0, bundle,this);
    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {

        String movie = "";
        if (args != null){
            movie = args.getString(EXTRAS_MOVIE);
        }

        return new MyAsyncTaskLoader(this, movie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String movie = etMovie.getText().toString();

            if (TextUtils.isEmpty(movie))
                return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, movie);
            Toast.makeText(MainActivity.this, ""+movie, Toast.LENGTH_SHORT).show();
            getSupportLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}



