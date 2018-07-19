package com.example.lenovo.cataloguemoviebintang.main;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.example.lenovo.cataloguemoviebintang.api.ApiRequest;
import com.example.lenovo.cataloguemoviebintang.api.RetroClient;
import com.example.lenovo.cataloguemoviebintang.models.Movie;
import com.example.lenovo.cataloguemoviebintang.models.ResponseRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View mView;
    ApiRequest request;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        request = RetroClient.getRequestService();
    }

    @Override
    public void loadMovies(final String query) {
        if (TextUtils.isEmpty(query)){
            mView.onFailure("Isilah kata kunci pencarian");
            return;
        }else {
            mView.showProgressDialog(true);
            Call<ResponseRequest> loadMovies = request.getMovies("41bdc06c3200c1ccb0e036722e258c1e","en-US",query);
            loadMovies.enqueue(new Callback<ResponseRequest>() {
                @Override
                public void onResponse(@NonNull Call<ResponseRequest> call, @NonNull Response<ResponseRequest> response) {
                    mView.showProgressDialog(false);
                    if (response.code() == 200){
                        mView.onSuccess("Berhasil mengambil data");
                        mView.changeTitle(query);
                        mView.refreshList(response.body().getMovies());
                    }else{
                        mView.onFailure("Gagal mengambil data");
                        Log.d("Load Movies", "onResponse: " + response.message() + " url : " + response.raw().request().url());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseRequest> call, @NonNull Throwable t) {
                    mView.showProgressDialog(false);
                    mView.onFailure("Gagal menghubung ke server");
                    call.cancel();
                    Log.e("Load Movies", "onFailure: " + t.getMessage(), t);
                }
            });
        }
    }

    @Override
    public void toDetailMovie(Movie movie) {
        mView.toDetailMovie(movie);
    }
}
