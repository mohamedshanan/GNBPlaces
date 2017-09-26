package com.shanan.gnbplaces.ui.places.explore;

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

public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),
                "Clicked Position = " + getAdapterPosition(), Toast.LENGTH_SHORT)
                .show();
    }
}
