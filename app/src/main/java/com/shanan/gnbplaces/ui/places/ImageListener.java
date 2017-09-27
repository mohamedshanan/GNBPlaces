package com.shanan.gnbplaces.ui.places;

import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;
import com.shanan.gnbplaces.repositories.places.models.Image;
import com.shanan.gnbplaces.repositories.places.models.Place;

/**
 * Created by shanan on 26/09/2017.
 */

public interface ImageListener {
    void displayImage(Image image, ImageView target);
}
