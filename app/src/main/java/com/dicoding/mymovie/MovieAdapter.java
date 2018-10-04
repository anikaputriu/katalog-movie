package com.dicoding.mymovie;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.mymovie.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieAdapter extends BaseAdapter {

    ArrayList<MovieItems> mData = new ArrayList<>();
    LayoutInflater mInflater;
    Context context;
    String TAG = MovieAdapter.class.getSimpleName();

    private static final String URL_IMG = "http://image.tmdb.org/t/p/w185";

    public MovieAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems item){
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    @Override
    public MovieItems getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_items, null);
            holder.tvTitle = convertView.findViewById(R.id.tv_movie_title);
            holder.tvDesciption = convertView.findViewById(R.id.tv_description);
            holder.tvReleaseDate = convertView.findViewById(R.id.tv_release_date);
            holder.imgPoster = convertView.findViewById(R.id.iv_poster);
            Log.e(TAG, "getView: GetView");
        }else {
            holder = (ViewHolder) convertView.getTag();
            Log.e(TAG, "getView: else");
        }
        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.tvDesciption.setText(mData.get(position).getOverview());
        String releaseDate =mData.get(position).getReleaseDate();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(releaseDate);
            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String date_of_release = new_date_format.format(date);
            holder.tvReleaseDate.setText(date_of_release);
        } catch (Exception e) {
            e.printStackTrace();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailMovieActivity.class);
                intent.putExtra("EXTRA_TITLE",mData.get(position).getTitle());
                intent.putExtra("EXTRA_POSTER",mData.get(position).getPosterPath());
                intent.putExtra("EXTRA_DESC",mData.get(position).getOverview());
                context.startActivity(intent);
            }
        });

        Glide.with(this.context).load(URL_IMG + mData.get(position).getPosterPath()).into(holder.imgPoster);
        Log.e(TAG, "getView: get "+mData.get(position).getPosterPath());
        return convertView;
    }

    private static class ViewHolder{
        TextView tvTitle, tvDesciption, tvReleaseDate;
        ImageView imgPoster;
    }
}