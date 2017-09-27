package com.shanan.gnbplaces.di.component;

import com.shanan.gnbplaces.di.modules.AppModule;
import com.shanan.gnbplaces.ui.base.BaseActivity;
import com.shanan.gnbplaces.ui.base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shanan on 25/09/2017.
 */

@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(BaseActivity baseActivity);
    void inject(BaseFragment baseFragment);
}
