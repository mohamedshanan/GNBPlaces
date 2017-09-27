package com.shanan.gnbplaces.ui.places.featured;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shanan.gnbplaces.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shanan on 26/09/2017.
 */

public class FeaturedViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.card_view)
    CardView item;

    @BindView(R.id.place_image)
    ImageView placeImage;

    @BindView(R.id.price)
    TextView price;

    public FeaturedViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
