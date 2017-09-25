package com.shanan.gnbplaces.ui.PlacesList;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shanan.gnbplaces.R;
import com.shanan.gnbplaces.repositories.places.models.Place;
import com.shanan.gnbplaces.ui.base.BaseActivity;
import com.shanan.gnbplaces.utils.Utilities;

import java.util.List;

import butterknife.BindView;

public class PlacesListActivity extends BaseActivity implements PlacesContract.View {

    @BindView(R.id.featured_tv)
    TextView featuredTv;

    @BindView(R.id.explore_tv)
    TextView exploreTv;

    @BindView(R.id.btn)
    Button btn;

    private static final String TAG = PlacesListActivity.class.getSimpleName();
    private PlacesContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);

        mPresenter = new PlacesPresenter(this);
        mPresenter.getFeaturedPlaces();
        mPresenter.explorePlaces();

        btn.setOnClickListener(view -> {
            mPresenter.explorePlaces();
        });
    }

    @Override
    protected void showNoConnection() {
        Toast.makeText(this, "No Internet Connection !!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFeaturedPlaces(List<Place> places) {
        featuredTv.setText("Featured Places \n \n" + places.toString());
    }

    @Override
    public void showPlaces(boolean clearing, List<Place> places) {
        for (Place place : places) {
            exploreTv.append("\n" + place.getPlaceDescription());
        }
    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void showLoadMoreProgress() {

    }

    @Override
    public void dismissLoadMoreProgress() {

    }

    @Override
    public void clearPlaces() {

    }

    @Override
    public void showSnackBar(int resId) {

    }

    @Override
    public void showTryAgainLayout(String message) {

    }

    @Override
    public boolean isConnected() {
        return Utilities.getNetworkState(this);
    }

    @Override
    public void showTryAgainLayout(int resId) {

    }
}
