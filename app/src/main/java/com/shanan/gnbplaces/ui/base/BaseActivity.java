package com.shanan.gnbplaces.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shanan.gnbplaces.App;
import com.shanan.gnbplaces.utils.Utilities;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Shanan on 25/09/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(this).getAppComponent().inject(this);
        checkNetWork();
    }


    private void checkNetWork() {
        if (!Utilities.getNetworkState(this)) {
            showNoConnection();
        }
    }

    protected abstract void showNoConnection();


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
