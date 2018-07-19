package com.example.lenovo.cataloguemoviebintang.main;

import com.example.lenovo.cataloguemoviebintang.models.Movie;

import java.util.ArrayList;

public class MainContract {
    interface View{
        void showProgressDialog(boolean show);

        void refreshList(ArrayList<Movie> movies);

        void onSuccess(String message);

        void onFailure(String message);

        void changeTitle(String message);

        void toDetailMovie(Movie movie);
    }
    interface Presenter{
        void loadMovies(String query);

        void toDetailMovie(Movie movie);
    }
}

