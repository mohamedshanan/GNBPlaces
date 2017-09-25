package com.shanan.gnbplaces.rest;

import com.shanan.gnbplaces.repositories.places.models.Place;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Shanan on 25/09/2017.
 */

public interface ApiEndPointInterface {

    @GET(ApiConstants.FEATURED_PLACES)
    Single<List<Place>> getFeaturedPlaces();

    @GET(ApiConstants.EXPLORE_PLACES)
    Single<List<Place>> explorePlaces(@Query(ApiConstants.PARAM_COUNT) int count, @Query(ApiConstants.PARAM_FROM) int from);
}
