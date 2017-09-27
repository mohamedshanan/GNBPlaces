package com.shanan.gnbplaces.repositories.places.repository;

import com.shanan.gnbplaces.repositories.places.models.Place;

import java.util.List;

/**
 * Created by Shanan on 25/09/2017.
 */

public interface OnPlacesResponse {
    void onSuccess(List<Place> savedPlaces);
    void onFailure(String errorMessage);
}
