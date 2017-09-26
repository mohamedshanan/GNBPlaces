package com.shanan.gnbplaces.repositories.places.repository;

import android.util.Log;

import com.shanan.gnbplaces.rest.ApiEndPointInterface;
import com.shanan.gnbplaces.rest.ServiceGenerator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shanan on 25/09/2017.
 */

public class ProdPlacesRepository implements PlacesRepository {

    private static final String TAG = ProdPlacesRepository.class.getSimpleName();
    private boolean isLoading = false;
    private Disposable disposable;

    public ProdPlacesRepository() {
    }

    @Override
    public void getFeaturedPlaces(OnPlacesResponse onPlacesResponse) {

        ApiEndPointInterface apiEndPointInterface = ServiceGenerator.getLocalPlacesEndPointInterface();

        disposable = apiEndPointInterface.getFeaturedPlaces()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe((places, throwable) -> {
                    isLoading = false;

                    if (throwable == null) {
                        Log.d(TAG, "success");
                        onPlacesResponse.onSuccess(places);

                    } else {
                        Log.d(TAG, "Failure");
                        onPlacesResponse.onFailure(throwable.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void explorePlaces(int count, int from, OnPlacesResponse onPlacesResponse) {

        if (isLoading) {
            Log.d(TAG, "Loading.. please wait.");
            // Reject the order.
            return;
        }

        isLoading = true;

        ApiEndPointInterface apiEndPointInterface = ServiceGenerator.getLocalPlacesEndPointInterface();

        disposable = apiEndPointInterface.explorePlaces(count, from)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe((places, throwable) -> {
                    isLoading = false;

                    if (throwable == null) {
                        Log.d(TAG, "success");
                        onPlacesResponse.onSuccess(places);

                    } else {
                        Log.d(TAG, "Failure");
                        onPlacesResponse.onFailure(throwable.getLocalizedMessage());
                    }
                });
    }

    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void unSubscribe() {
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
