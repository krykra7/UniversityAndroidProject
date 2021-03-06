package com.kkrawczyk.podstawyandroidaprojekt.model;

import java.io.Serializable;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public abstract class Shape implements Serializable {

    public static final String SQUARE = "KWADRAT";
    public static final String TRIANGLE = "TRINAGLE";
    public static final String CIRCLE = "CIRCLE";

    protected double feature;
    protected double area;

    public abstract double getCalculatedArea();

    public abstract double getCalculatedFeature();

    public abstract int getImageRes();

    public abstract String getFeatureName();

    public double getFeature() {
        return feature;
    }

    public double getArea() {
        return area;
    }
}
