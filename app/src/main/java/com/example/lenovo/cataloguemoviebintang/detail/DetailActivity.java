package com.example.lenovo.cataloguemoviebintang.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.cataloguemoviebintang.R;
import com.example.lenovo.cataloguemoviebintang.models.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static String MOVIE_EXTRA = "details movie";
    @BindView(R.id.imv_poster)
    ImageView imvPoster;
    @BindView(R.id.txv_overview)
    TextView txvOverview;
    @BindView(R.id.txv_tanggal)
    TextView txvTanggal;
    @BindView(R.id.txv_language)
    TextView txvLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Movie movie = getIntent().getParcelableExtra(MOVIE_EXTRA);
        String urlPoster = "http://image.tmdb.org/t/p/w185/" + movie.getPoster_path();
        Glide.with(this)
                .load(urlPoster)
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(imvPoster);
        txvOverview.setText(movie.getOverview());
        txvLanguage.setText(": " + movie.getOriginal_language());
        txvTanggal.setText(": " + movie.getRelease_date());
        getSupportActionBar().setTitle(movie.getTitle());
    }
}
