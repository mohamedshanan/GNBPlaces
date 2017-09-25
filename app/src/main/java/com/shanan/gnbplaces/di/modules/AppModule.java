package com.shanan.gnbplaces.di.modules;

import android.support.annotation.NonNull;

import com.shanan.gnbplaces.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shanan on 25/09/2017.
 */

@Module
public class AppModule {

    @NonNull
    private final App app;

    public AppModule(@NonNull App app) {
        this.app = app;
    }

    @NonNull @Singleton
    @Provides
    public App providesApp() {
        return app;
    }
}