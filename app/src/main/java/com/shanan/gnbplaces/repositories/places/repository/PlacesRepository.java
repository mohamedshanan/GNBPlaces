package com.shanan.gnbplaces.repositories.places.repository;

/**
 * Created by Shanan on 25/09/2017.
 */

public interface PlacesRepository {
    void getFeaturedPlaces(OnPlacesResponse onPlacesResponse);
    void explorePlaces(int count, int from, OnPlacesResponse onPlacesResponse);
    void getCachedPlaces(int count, int from, OnPlacesResponse onPlacesResponse);
    void getCachedFeaturedPlaces(OnPlacesResponse onPlacesResponse);
    boolean isLoading();
    void unSubscribe();
}
