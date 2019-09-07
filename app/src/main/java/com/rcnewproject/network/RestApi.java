package com.rcnewproject.network;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * This class is used to handling operation for Service
 */
public class RestApi {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
//    static final String API_BASE_URL = "http://rccapi.appinventive.com/";
//
//    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//    private static Retrofit.Builder retrofitBuilder =
//            new Retrofit.Builder()
//                    .baseUrl(API_BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create());
//
//    public static <S> S createService(final Context context, Class<S> aClass) {
//        httpClient.readTimeout(30, TimeUnit.SECONDS);
//        httpClient.connectTimeout(30, TimeUnit.SECONDS);
//        Retrofit retrofit = retrofitBuilder.client(httpClient.build()).build();
//        return retrofit.create(aClass);
//    }

    public static <S> S createService(final Context context, Class<S> aClass) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(aClass);
    }
}
