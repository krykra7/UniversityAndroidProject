package com.kkrawczyk.podstawyandroidaprojekt.model;

import com.kkrawczyk.podstawyandroidaprojekt.R;

import java.io.Serializable;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class Triangle extends Shape implements Serializable {

    private double side;

    public Triangle(double side) {
        this.side = side;
    }

    @Override
    public double getCalculatedArea() {
        return Double.parseDouble(decimalFormat.format((pow(side, 2) * sqrt(3)) / 4));
    }

    @Override
    public double getCalculatedFeature() {
        return Double.parseDouble(decimalFormat.format((side * sqrt(3)) / 2));
    }

    @Override
    public int getImageRes() {
        return R.drawable.triangle;
    }

    @Override
    public String getFeatureName() {
        return "Wysokość";
    }

    @Override
    public String toString() {
        return "TROJKAT o polu " + getCalculatedArea() + " " + " i wysokości " + getCalculatedFeature();
    }
}
