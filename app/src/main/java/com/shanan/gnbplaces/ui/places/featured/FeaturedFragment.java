package com.shanan.gnbplaces.ui.places.featured;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanan.gnbplaces.R;
import com.shanan.gnbplaces.repositories.places.models.Place;
import com.shanan.gnbplaces.ui.base.BaseFragment;
import com.shanan.gnbplaces.ui.places.OnPlaceClickListener;
import com.shanan.gnbplaces.utils.Utilities;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnPlaceClickListener} interface
 * to handle interaction events.
 * Use the {@link FeaturedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeaturedFragment extends BaseFragment implements FeaturedContract.View {

    // the fragment initialization parameters, ARG_FEATURED_LIST
    private static final String ARG_FEATURED_LIST = "featured_places_list";

    private List<Place> mFeaturedPlaces;

    private OnPlaceClickListener mListener;
    private FeaturedContract.Presenter mPresenter;

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
        return rootView;
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
                    + " must implement OnExploreFragmentListener");
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

    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void showSnackBar(int resId) {

    }

    @Override
    public void showTryAgainLayout(String message) {

    }

    @Override
    public boolean isConnected() {
        return Utilities.getNetworkState(getActivity());
    }

    @Override
    public void showTryAgainLayout(int resId) {

    }
}
