package com.zaenal.jsonzaenal.api;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static <Retrofit, HttpLoggingInterceptor> Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://haveibeenpwned.com/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static BreachService getBreachService(){
        BreachService breachService = getRetrofit().create(BreachService.class);

        return breachService;
    }
}