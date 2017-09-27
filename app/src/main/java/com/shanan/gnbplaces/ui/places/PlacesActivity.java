package com.shanan.gnbplaces.ui.places;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.shanan.gnbplaces.R;
import com.shanan.gnbplaces.repositories.places.models.Image;
import com.shanan.gnbplaces.repositories.places.models.Place;
import com.shanan.gnbplaces.ui.base.BaseActivity;
import com.shanan.gnbplaces.ui.places.explore.ExploreFragment;
import com.shanan.gnbplaces.ui.places.featured.FeaturedFragment;

import butterknife.BindView;

public class PlacesActivity extends BaseActivity implements OnPlaceClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        if (savedInstanceState != null) {
            return;
        }

        // Create featured Fragment to be placed in the featured section
        FeaturedFragment featuredFragment = new FeaturedFragment();

        // Add featured fragment to the 'featured_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.featured_container, featuredFragment).commit();

        // Create an explore Fragment to be placed in the activity layout
        ExploreFragment exploreFragment = new ExploreFragment();

        // Add explore fragment to the 'explore_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.explore_container, exploreFragment).commit();
    }

    @Override
    public void onPlaceClick(Place place) {
        Intent intent = PlaceDetailsActivity.buildIntent(this, place);
        startActivity(intent);
    }
}
