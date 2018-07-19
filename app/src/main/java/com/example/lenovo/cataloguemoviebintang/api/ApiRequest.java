package com.example.lenovo.cataloguemoviebintang.api;

import com.example.lenovo.cataloguemoviebintang.models.ResponseRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiRequest {
    @Headers({"Accept: applicatio/json"})
    @GET("3/search/movie")
    Call<ResponseRequest> getMovies(@Query("api_key") String api_key,
                                    @Query("language") String language,
                                    @Query("query") String query);
}
