package com.shanan.gnbplaces.repositories.places.repository;

import android.util.Log;

import com.shanan.gnbplaces.repositories.places.models.Place;
import com.shanan.gnbplaces.rest.ApiEndPointInterface;
import com.shanan.gnbplaces.rest.ServiceGenerator;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

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

                    if (throwable == null && places.size() > 0) {
                        Log.d(TAG, "success");
                        for (Place featuredPlace : places) {
                            featuredPlace.setFeatured(true);
                        }
                        savePlaces(places);
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

                    if (throwable == null && places.size() > 0) {
                        Log.d(TAG, "success");
                        for (Place place : places) {
                            place.setFeatured(false);
                        }
                        savePlaces(places);
                        onPlacesResponse.onSuccess(places);

                    } else {
                        Log.d(TAG, "Failure");
                        onPlacesResponse.onFailure(throwable.getLocalizedMessage());
                    }
                });
    }


    public void savePlaces(List<Place> places) {
        Realm realm = null;
        try { 
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(realm1 -> {
                realm1.insertOrUpdate(places);
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Override
    public void getCachedPlaces(int count, int from, OnPlacesResponse onPlacesResponse) {
        Realm realm = null;
        try { 
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    // get saved places by id
                    RealmResults<Place> savedPlaces = realm.where(Place.class)
                            .greaterThanOrEqualTo("id", from)
                            .lessThan("id", count)
                            .findAllSortedAsync("id", Sort.ASCENDING);

                    // limit results to count to simulate pagination
                    if (!savedPlaces.isEmpty()) {
                        if (savedPlaces.size() > count){
                            onPlacesResponse.onSuccess(savedPlaces.subList(from, count - 1));
                        } else {
                            onPlacesResponse.onSuccess(savedPlaces);
                        }
                    } else {
                        onPlacesResponse.onFailure("No places found.");
                    }

                }
            });
        } catch (Exception ex) {
            onPlacesResponse.onFailure("No places found.");
        } finally {
            if (realm != null) {
                realm.close();
            }
        }

    }

    @Override
    public void getCachedFeaturedPlaces(OnPlacesResponse onPlacesResponse) {

        Realm realm = null;
        try { 
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Place> featuredPlaces = realm.where(Place.class).equalTo("isFeatured", true).findAllAsync();
                    if (!featuredPlaces.isEmpty()) {
                        onPlacesResponse.onSuccess(featuredPlaces);
                    } else {
                        onPlacesResponse.onFailure("No featured places found.");
                    }
                }
            });
        } catch (Exception ex) {
            onPlacesResponse.onFailure("No featured places found.");
        } finally {
            if (realm != null) {
                realm.close();
            }
        }

    }

    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void unSubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
