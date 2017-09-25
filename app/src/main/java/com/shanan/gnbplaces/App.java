package com.shanan.gnbplaces;

import android.app.Application;
import android.content.Context;

import com.shanan.gnbplaces.di.component.AppComponent;
import com.shanan.gnbplaces.di.component.DaggerAppComponent;
import com.shanan.gnbplaces.di.modules.AppModule;

/**
 * Created by Shanan on 25/09/2017.
 */

public class App extends Application {

    AppComponent appComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
