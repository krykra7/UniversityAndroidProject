package com.kkrawczyk.podstawyandroidaprojekt.model;

import java.io.Serializable;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public abstract class Shape implements Serializable {

    protected double feature;
    protected double area;

    public abstract double getCalculatedArea();

    public abstract double getCalculatedFeature();

    public abstract int getImageRes();

    public abstract String getFeatureName();

    public double getFeature() {
        return feature;
    }

    public void setFeature(double feature) {
        this.feature = feature;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
