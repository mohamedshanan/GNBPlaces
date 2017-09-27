package com.shanan.gnbplaces.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.shanan.gnbplaces.rest.ApiConstants.GNP_PLACES_BASE_URL;

/**
 * Created by Shanan on 25/09/2017.
 */

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder localPlacesBuilder = new Retrofit.Builder().baseUrl(GNP_PLACES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private static ApiEndPointInterface mPlacesPointInterface;

    public static <S> S createLocalPlacesService(Class<S> serviceClass) {

        httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);
        Retrofit retrofit = localPlacesBuilder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    public static ApiEndPointInterface getLocalPlacesEndPointInterface() {
        if (mPlacesPointInterface == null) {
            mPlacesPointInterface = ServiceGenerator.createLocalPlacesService(ApiEndPointInterface.class);
        }
        return mPlacesPointInterface;
    }

}
