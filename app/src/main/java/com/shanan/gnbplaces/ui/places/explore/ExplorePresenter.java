package com.shanan.gnbplaces.ui.places.explore;

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

public class ExplorePresenter implements ExploreContract.Presenter {

    private static final String TAG = ExplorePresenter.class.getSimpleName();
    private final static int FIRST_PLACE = 0;
    private final static int COUNT = 6;

    private final ExploreContract.View view;
    private final PlacesRepository mPlacesRepository = new ProdPlacesRepository();

    private int lastPlaceIndex = 0;
    private boolean isNoMoreData;

    public ExplorePresenter(ExploreContract.View view) {
        this.view = view;
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

    @Override
    public void onDestroyView() {
        if (mPlacesRepository != null) {
            mPlacesRepository.unSubscribe();
        }
    }


    private void explore() {
        Log.d("Endless", "from: " + lastPlaceIndex);
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
