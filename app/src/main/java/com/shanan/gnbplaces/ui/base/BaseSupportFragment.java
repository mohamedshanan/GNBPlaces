package com.shanan.gnbplaces.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanan.gnbplaces.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Shanan on 25/09/2017.
 */

public class BaseSupportFragment extends Fragment {

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(getActivity()).getAppComponent().inject(this);
    }


    protected View getLayout(@LayoutRes int layoutResID, LayoutInflater inflater,
                             ViewGroup container) {
        View rootView = inflater.inflate(layoutResID, container, false);

        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

}
