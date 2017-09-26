package com.shanan.gnbplaces.ui.places.explore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.shanan.gnbplaces.R;
import com.shanan.gnbplaces.repositories.places.models.Image;
import com.shanan.gnbplaces.repositories.places.models.Place;
import com.shanan.gnbplaces.ui.places.ImageListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shanan on 26/09/2017.
 */

public class PlacesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_ITEM = 0;
    private final int VIEW_PROG = 1;

    private List<Place> mPlaces;
    private ImageListener mImageListener;

    public PlacesAdapter(List<Place> places, ImageListener imageListener) {
        this.mPlaces = places;
        this.mImageListener = imageListener;
    }

    @Override public int getItemViewType(int position) {
        return mPlaces.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        if (viewType == VIEW_ITEM) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, null, false);
            viewHolder = new PlaceViewHolder(layoutView);
        } else {
            View view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);

            viewHolder = new ProgressViewHolder(view);

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PlaceViewHolder) {
            PlaceViewHolder placeViewHolder = (PlaceViewHolder) holder;
            Place place = mPlaces.get(position);
            if (place != null) {
                placeViewHolder.description.setText(place.getPlaceDescription() != null ? place.getPlaceDescription() : "");
                placeViewHolder.price.setText(place.getPrice() != null ? "$ " + place.getPrice() : "");
                if (place.getImage() != null && mImageListener != null) {
                    mImageListener.displayImage(place.getImage(), placeViewHolder.placeImage);
                }
            }
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public void showLoadMoreProgress() {
        //add progress item
        mPlaces.add(null);
        notifyItemInserted(mPlaces.size());
    }

    public void dismissLoadMoreProgress() {
        //remove progress item
        mPlaces.remove(mPlaces.size() - 1);
        notifyItemRemoved(mPlaces.size());
    }

    public void clear() {
        mPlaces.clear();
        notifyDataSetChanged();
    }

    public void setPlaces(List<Place> places) {
        mPlaces.clear();
        mPlaces.addAll(places);
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addPlaces(List<Place> places) {
        int positionStart = getItemCount();
        mPlaces.addAll(places);
        notifyItemRangeInserted(positionStart, mPlaces.size());
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progressBar) public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }


}
