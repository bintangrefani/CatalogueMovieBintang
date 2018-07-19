package com.example.lenovo.cataloguemoviebintang.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static final String BASE_URL = "http://api.themoviedb.org/";
    private static Retrofit retrofit = null;

    private static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static ApiRequest getRequestService(){
        return getClient().create(ApiRequest.class);
    }
}
