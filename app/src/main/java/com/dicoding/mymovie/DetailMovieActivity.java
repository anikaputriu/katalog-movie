package com.dicoding.mymovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailMovieActivity extends AppCompatActivity {

    private TextView tvTitle, tvDesc;
    private ImageView imgPoster;

    private String title,desc,urlImg;

    private static final String URL_IMG = "http://image.tmdb.org/t/p/w185";

//
//    intent.putExtra("EXTRA_TITLE",mData.get(position).getTitle());
//                intent.putExtra("EXTRA_POSTER",mData.get(position).getPosterPath());
//                intent.putExtra("EXTRA_DESC",mData.get(position).getOverview());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvTitle = findViewById(R.id.tv_detail_title);
        tvDesc = findViewById(R.id.tv_desc);
        imgPoster = findViewById(R.id.img_poster);

        Intent intent = getIntent();
        title = intent.getStringExtra("EXTRA_TITLE");
        desc = intent.getStringExtra("EXTRA_DESC");
        urlImg = intent.getStringExtra("EXTRA_POSTER");

        tvTitle.setText(title);
        tvDesc.setText(desc);
        Glide.with(this)
                .load(URL_IMG+urlImg)
                .into(imgPoster);
    }
}
