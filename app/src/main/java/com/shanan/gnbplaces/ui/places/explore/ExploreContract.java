package com.shanan.gnbplaces.ui.places.explore;

import com.shanan.gnbplaces.repositories.places.models.Place;

import java.util.List;

/**
 * Created by Shanan on 23/09/2017.
 */

public class ExploreContract {

    public interface View {

        void showPlaces(boolean clearing, List<Place> places);

        void showLoadMoreProgress();

        void dismissLoadMoreProgress();

        void showLoader();

        void hideLoader();

        void showSnackBar(int resId);

        void showTryAgainLayout(String message);

        boolean isConnected();

        void showTryAgainLayout(int resId);
    }


    public interface Presenter {

        void explorePlaces();

        void onDestroyView();

    }
}
