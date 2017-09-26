package com.shanan.gnbplaces.ui.places.featured;

import com.shanan.gnbplaces.repositories.places.models.Place;

import java.util.List;

/**
 * Created by Shanan on 23/09/2017.
 */

public interface FeaturedContract {

    public interface View {

        void showFeaturedPlaces(List<Place> places);

        void showLoader();

        void hideLoader();

        void showSnackBar(int resId);

        void showTryAgainLayout(String message);

        boolean isConnected();

        void showTryAgainLayout(int resId);
    }

    interface Presenter {

        void getFeaturedPlaces();

        void onDestroyView();

    }
}
