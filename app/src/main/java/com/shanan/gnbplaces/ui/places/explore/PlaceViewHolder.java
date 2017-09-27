package com.shanan.gnbplaces.ui.places.explore;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shanan.gnbplaces.R;
import com.shanan.gnbplaces.ui.places.OnPlaceClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shanan on 26/09/2017.
 */

public class PlaceViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.card_view)
    CardView item;

    @BindView(R.id.place_image)
    ImageView placeImage;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.price)
    TextView price;

    public PlaceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
