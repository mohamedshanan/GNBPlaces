package com.shanan.gnbplaces.repositories.places.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Shanan on 25/09/2017.
 */

public class Place {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("placeDescription")
    @Expose
    private String placeDescription;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("price")
    @Expose
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
