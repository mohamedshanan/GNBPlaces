package com.shanan.gnbplaces.ui.PlacesList;

import com.shanan.gnbplaces.repositories.places.models.Place;

import java.util.List;

/**
 * Created by Shanan on 23/09/2017.
 */

public class PlacesContract {

    interface View {

        void showFeaturedPlaces(List<Place> places);

        void showPlaces(boolean clearing, List<Place> places);

        void showLoader();

        void hideLoader();

        void showLoadMoreProgress();

        void dismissLoadMoreProgress();

        void clearPlaces();

        void showSnackBar(int resId);

        void showTryAgainLayout(String message);

        boolean isConnected();

        void showTryAgainLayout(int resId);
    }

    interface Presenter {

        void getFeaturedPlaces();

        void explorePlaces();
    }
}
