package com.shanan.gnbplaces.ui.PlacesList;

import android.util.Log;

import com.shanan.gnbplaces.R;
import com.shanan.gnbplaces.repositories.places.models.Place;
import com.shanan.gnbplaces.repositories.places.repository.OnPlacesResponse;
import com.shanan.gnbplaces.repositories.places.repository.PlacesRepository;
import com.shanan.gnbplaces.repositories.places.repository.ProdPlacesRepository;

import java.util.List;

/**
 * Created by Shanan on 23/09/2017.
 */

public class PlacesPresenter implements PlacesContract.Presenter {

    private static final String TAG = PlacesPresenter.class.getSimpleName();
    private final static int FIRST_PLACE = 0;
    private final static int COUNT = 10;

    private final PlacesContract.View view;
    private final PlacesRepository mPlacesRepository = new ProdPlacesRepository();

    private int lastPlaceIndex = 0;
    private boolean isNoMoreData;

    public PlacesPresenter(PlacesContract.View view) {
        this.view = view;
    }

    @Override
    public void getFeaturedPlaces() {

        if (!view.isConnected()) {
            return;
        }

        mPlacesRepository.getFeaturedPlaces(new OnPlacesResponse() {
            @Override
            public void onSuccess(List<Place> featuredPlaces) {
                view.hideLoader();

                if (featuredPlaces == null || featuredPlaces.isEmpty()) {
                    view.showTryAgainLayout(R.string.no_featured_places);
                    return;
                }
                view.showFeaturedPlaces(featuredPlaces);

            }

            @Override
            public void onFailure(String errorMessage) {
                view.hideLoader();
                view.showTryAgainLayout(errorMessage);
            }
        });


    }

    @Override
    public void explorePlaces() {

        if (!view.isConnected()) {
            return;
        }

        if (!isNoMoreData) {
            if (lastPlaceIndex == FIRST_PLACE) {
                view.showLoader();
            }
            explore();
        } else {
            Log.i(TAG, "No more places");
        }
    }


    public void explore() {

        mPlacesRepository.explorePlaces(COUNT, lastPlaceIndex, new OnPlacesResponse() {
            @Override
            public void onSuccess(List<Place> places) {
                view.hideLoader();

                if (places == null || places.isEmpty()) {
                    isNoMoreData = true;
                    if (lastPlaceIndex == 0) {
                        view.showTryAgainLayout(R.string.no_places);
                    }
                    return;
                }

                if (lastPlaceIndex == FIRST_PLACE) {
                    view.showPlaces(true, places);
                } else {
                    view.showPlaces(false, places);
                }

                // prepare lastPlaceIndex for the next page
                lastPlaceIndex += COUNT;
            }

            @Override
            public void onFailure(String errorMessage) {
                view.hideLoader();
                view.showTryAgainLayout(errorMessage);
            }
        });
    }
}
