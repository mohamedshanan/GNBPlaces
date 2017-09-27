package com.shanan.gnbplaces.ui.places.featured;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shanan.gnbplaces.R;
import com.shanan.gnbplaces.repositories.places.models.Image;
import com.shanan.gnbplaces.repositories.places.models.Place;
import com.shanan.gnbplaces.ui.base.BaseFragment;
import com.shanan.gnbplaces.ui.places.ImageListener;
import com.shanan.gnbplaces.ui.places.OnPlaceClickListener;
import com.shanan.gnbplaces.ui.places.PlaceDetailsActivity;
import com.shanan.gnbplaces.ui.places.explore.PlacesAdapter;
import com.shanan.gnbplaces.utils.Utilities;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnPlaceClickListener} interface
 * to handle interaction events.
 * Use the {@link FeaturedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeaturedFragment extends BaseFragment implements FeaturedContract.View, ImageListener {

    @BindView(R.id.container)
    ViewGroup container;
    @BindView(R.id.recycler_view)
    RecyclerView placesRv;
    @BindView(R.id.progressBar)
    ProgressBar loader;
    @BindView(R.id.no_data_tv)
    TextView noDataView;

    private FeaturedPresenter mPresenter;
    private OnPlaceClickListener mListener;

    public FeaturedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExploreFragment.
     */
    public static FeaturedFragment newInstance() {
        return new FeaturedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = getLayout(R.layout.fragment_featured, inflater, container);
        mPresenter = new FeaturedPresenter(this);
        mPresenter.getFeaturedPlaces();
        return rootView;
    }

    private void setupPlacesRV(List<Place> places) {
        placesRv.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        placesRv.setLayoutManager(layoutManager);

        FeaturedAdapter featuredAdapter = new FeaturedAdapter(places, this, mListener);
        placesRv.setAdapter(featuredAdapter);
    }

    public void onPlaceClicked(Place place) {
        if (mListener != null) {
            mListener.onPlaceClick(place);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPlaceClickListener) {
            mListener = (OnPlaceClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPlaceClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mPresenter.onDestroyView();

    }

    @Override
    public void showFeaturedPlaces(List<Place> places) {
        setupPlacesRV(places);
    }

    @Override
    public void showLoader() {
        loader.setVisibility(View.VISIBLE);
        placesRv.setVisibility(View.GONE);
    }

    @Override
    public void hideLoader() {
        loader.setVisibility(View.GONE);
        placesRv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTryAgainLayout(String message) {
        noDataView.setVisibility(View.VISIBLE);
        noDataView.setText(message);
    }

    @Override
    public boolean isConnected() {
        return Utilities.getNetworkState(getActivity());
    }

    @Override
    public void showTryAgainLayout(int resId) {
        noDataView.setVisibility(View.VISIBLE);
        noDataView.setText(getString(resId));
    }

    @Override
    public void displayImage(Image image, ImageView target) {
        Picasso.with(getActivity())
                .load(image.getUrl())
                .resize(image.getWidth(), image.getHeight())
                .into(target);
    }
}
