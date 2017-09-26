package com.shanan.gnbplaces.ui.places.explore;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shanan.gnbplaces.R;
import com.shanan.gnbplaces.repositories.places.models.Image;
import com.shanan.gnbplaces.repositories.places.models.Place;
import com.shanan.gnbplaces.ui.base.BaseFragment;
import com.shanan.gnbplaces.ui.places.ImageListener;
import com.shanan.gnbplaces.ui.places.OnPlaceClickListener;
import com.shanan.gnbplaces.utils.EndlessRecyclerViewScrollListener;
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
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends BaseFragment implements ExploreContract.View, ImageListener {

    @BindView(R.id.recycler_view)
    RecyclerView placesRv;

    private ExplorePresenter mPresenter;
    private OnPlaceClickListener mListener;
    private StaggeredGridLayoutManager mLayoutManager;
    private List<Place> mPlaces;
    private PlacesAdapter mPlacesAdapter;

    public ExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExploreFragment.
     */
    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = getLayout(R.layout.fragment_explore, inflater, container);

        mPresenter = new ExplorePresenter(this);
        setupPlacesRV(new ArrayList<>());
        mPresenter.explorePlaces();

        return rootView;
    }

    private void setupPlacesRV(List<Place> places) {
        placesRv.setHasFixedSize(true);

        mLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        placesRv.setLayoutManager(mLayoutManager);

        mPlacesAdapter = new PlacesAdapter(places, this);
        placesRv.setAdapter(mPlacesAdapter);

        placesRv.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore() {
                Log.d("Endless", "load more");
                mPresenter.explorePlaces();
            }
        });
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
    public void showPlaces(boolean clearing, List<Place> places) {
        if (clearing) {
            mPlacesAdapter.setPlaces(places);
            mLayoutManager.scrollToPosition(0);
        } else {
            mPlacesAdapter.addPlaces(places);
        }

    }

    @Override
    public void showLoadMoreProgress() {

    }

    @Override
    public void dismissLoadMoreProgress() {

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

    @Override
    public void displayImage(Image image, ImageView target) {
        Picasso.with(getActivity())
                .load(image.getUrl())
                .resize(image.getWidth(), image.getHeight())
                .into(target);
    }
}
