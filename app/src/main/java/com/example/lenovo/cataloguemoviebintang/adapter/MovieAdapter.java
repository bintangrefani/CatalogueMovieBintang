package com.example.lenovo.cataloguemoviebintang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.cataloguemoviebintang.R;
import com.example.lenovo.cataloguemoviebintang.main.MainPresenter;
import com.example.lenovo.cataloguemoviebintang.models.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    private Context context;
    private MainPresenter presenter;

    public MovieAdapter(ArrayList<Movie> movies, Context context, MainPresenter presenter) {
        this.movies = movies;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.txvJudul.setText(movie.getTitle());
        holder.txvOverview.setText(movie.getOverview());
        holder.txvTanggal.setText(movie.getRelease_date());
        String urlPoster = "http://image.tmdb.org/t/p/w185/" + movie.getPoster_path();
        Glide.with(context)
                .load(urlPoster)
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(holder.imvPoster);
        holder.llMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.toDetailMovie(movie);
            }
        });
    }

    public ArrayList<Movie> getMovies() {
        return this.movies;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_poster)
        ImageView imvPoster;
        @BindView(R.id.txv_judul)
        TextView txvJudul;
        @BindView(R.id.txv_overview)
        TextView txvOverview;
        @BindView(R.id.txv_tanggal)
        TextView txvTanggal;
        @BindView(R.id.ll_movie)
        LinearLayout llMovie;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

