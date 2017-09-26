package com.shanan.gnbplaces.ui.places;

import android.content.Intent;
import android.os.Bundle;
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

import butterknife.BindView;

public class PlacesActivity extends BaseActivity implements OnPlaceClickListener {

    private static final String TAG = PlacesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        if (savedInstanceState != null) {
            return;
        }

        // Create a new Fragment to be placed in the activity layout
        ExploreFragment firstFragment = new ExploreFragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        firstFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();
    }

    @Override
    protected void showNoConnection() {
        Toast.makeText(this, "No Internet Connection !!", Toast.LENGTH_SHORT).show();
    }

    private void reload() {
        Intent intent = getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
    }

    @Override
    public void onPlaceClick(Place place) {

    }
}
