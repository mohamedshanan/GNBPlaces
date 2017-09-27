package com.shanan.gnbplaces.ui.places.featured;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.shanan.gnbplaces.R;
import com.shanan.gnbplaces.repositories.places.models.Place;
import com.shanan.gnbplaces.ui.places.ImageListener;
import com.shanan.gnbplaces.ui.places.OnPlaceClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shanan on 26/09/2017.
 */

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedViewHolder> {

    private List<Place> mPlaces;
    private ImageListener mImageListener;
    private OnPlaceClickListener mOnPlaceClickListener;

    public FeaturedAdapter(List<Place> places, ImageListener imageListener, OnPlaceClickListener onPlaceClickListener) {
        mPlaces = places;
        mImageListener = imageListener;
        mOnPlaceClickListener = onPlaceClickListener;
    }

    @Override
    public FeaturedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_item, null, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(layoutView);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(FeaturedViewHolder holder, int position) {

        Place place = mPlaces.get(position);
        if (place != null) {
            holder.price.setText(place.getPrice() != null ? "$ " + place.getPrice() : "");
            if (place.getImage() != null && mImageListener != null) {
                mImageListener.displayImage(place.getImage(), holder.placeImage);
            }
            holder.item.setOnClickListener(view -> {
                mOnPlaceClickListener.onPlaceClick(place);
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }
}
