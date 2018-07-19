package com.example.lenovo.cataloguemoviebintang.main;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.cataloguemoviebintang.R;
import com.example.lenovo.cataloguemoviebintang.adapter.MovieAdapter;
import com.example.lenovo.cataloguemoviebintang.detail.DetailActivity;
import com.example.lenovo.cataloguemoviebintang.models.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.edt_search)
    EditText edtSearch;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    MainPresenter presenter;
    ProgressDialog progressDialog;
    MovieAdapter adapter;
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");

        adapter = new MovieAdapter(movies,this,presenter);
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.setAdapter(adapter);
    }

    @OnClick(R.id.btn_search)
    public void onViewClicked() {
        presenter.loadMovies(edtSearch.getText().toString().trim());
    }

    @Override
    public void showProgressDialog(boolean show) {
        if (show) progressDialog.show();
        else progressDialog.dismiss();
    }

    @Override
    public void refreshList(ArrayList<Movie> movies) {
        adapter.getMovies().clear();
        adapter.getMovies().addAll(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeTitle(String message) {
        String newTitle = "Search : " + message;
    }

    @Override
    public void toDetailMovie(Movie movie) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.MOVIE_EXTRA, movie);
        startActivity(intent);
    }
}
