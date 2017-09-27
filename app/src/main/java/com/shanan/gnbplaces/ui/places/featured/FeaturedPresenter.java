package com.shanan.gnbplaces.ui.places.featured;

import com.shanan.gnbplaces.R;
import com.shanan.gnbplaces.repositories.places.models.Place;
import com.shanan.gnbplaces.repositories.places.repository.OnPlacesResponse;
import com.shanan.gnbplaces.repositories.places.repository.PlacesRepository;
import com.shanan.gnbplaces.repositories.places.repository.ProdPlacesRepository;
import com.shanan.gnbplaces.ui.places.explore.ExploreContract;

import java.util.List;

/**
 * Created by Shanan on 23/09/2017.
 */

public class FeaturedPresenter implements FeaturedContract.Presenter {

    private static final String TAG = FeaturedPresenter.class.getSimpleName();

    private final FeaturedContract.View view;
    private final PlacesRepository mPlacesRepository = new ProdPlacesRepository();

    public FeaturedPresenter(FeaturedContract.View view) {
        this.view = view;
    }

    @Override
    public void getFeaturedPlaces() {

        if (view.isConnected()) {

            mPlacesRepository.getFeaturedPlaces(new OnPlacesResponse() {
                @Override
                public void onSuccess(List<Place> featuredPlaces) {
                    onDataLoaded(featuredPlaces);
                }

                @Override
                public void onFailure(String errorMessage) {
                    onError(errorMessage);
                }
            });
        } else {
            mPlacesRepository.getCachedFeaturedPlaces(new OnPlacesResponse() {
                @Override
                public void onSuccess(List<Place> featuredPlaces) {
                    onDataLoaded(featuredPlaces);
                }

                @Override
                public void onFailure(String errorMessage) {
                    onError(errorMessage);
                }
            });
        }

    }

    private void onError(String errorMessage) {
        view.hideLoader();
        view.showTryAgainLayout(errorMessage);
    }

    private void onDataLoaded(List<Place> featuredPlaces) {
        view.hideLoader();

        if (featuredPlaces == null || featuredPlaces.isEmpty()) {
            view.showTryAgainLayout(R.string.no_featured_places);
            return;
        }
        view.showFeaturedPlaces(featuredPlaces);
    }

    @Override
    public void onDestroyView() {
        if (mPlacesRepository != null) {
            mPlacesRepository.unSubscribe();
        }
    }
}
