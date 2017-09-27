package com.shanan.gnbplaces.ui.places;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shanan.gnbplaces.R;
import com.shanan.gnbplaces.repositories.places.models.Image;
import com.shanan.gnbplaces.repositories.places.models.Place;
import com.shanan.gnbplaces.ui.base.BaseActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;

public class PlaceDetailsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolBar;

    @BindView(R.id.place_image)
    ImageView placeImage;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.price)
    TextView price;

    public final static String KEY_PLACE = "place";
    private Place mPlace;

    public static Intent buildIntent(Context context, Place place) {
        Intent intent = new Intent(context, PlaceDetailsActivity.class);
        intent.putExtra(KEY_PLACE, Parcels.wrap(place));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        setupToolBar();

        if (getIntent() != null && getIntent().hasExtra(KEY_PLACE)) {
            mPlace = Parcels.unwrap(getIntent().getParcelableExtra(KEY_PLACE));
            if (mPlace != null) {
                setPlaceData(mPlace);
            } else {
                description.setText(getString(R.string.place_error));
            }
        } else {
            description.setText(getString(R.string.place_error));
        }
    }

    private void setupToolBar() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setPlaceData(Place mPlace) {
        displayImage(mPlace.getImage(), placeImage);
        description.setText(mPlace.getPlaceDescription() != null ? mPlace.getPlaceDescription() : "");
        price.setText(mPlace.getPrice() != null ? "$ " + mPlace.getPrice() : "");
    }

    public void displayImage(Image image, ImageView target) {
        Picasso.with(this)
                .load(image.getUrl())
                .into(target);
    }
}
