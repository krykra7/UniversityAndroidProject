package com.kkrawczyk.podstawyandroidaprojekt.model;

import com.kkrawczyk.podstawyandroidaprojekt.R;

import java.io.Serializable;

import static java.lang.Math.sqrt;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class Square extends Shape implements Serializable {

    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getCalculatedArea() {
        return Double.parseDouble(decimalFormat.format(side * side));
    }

    @Override
    public double getCalculatedFeature() {
        return Double.parseDouble((decimalFormat.format(side * sqrt(2))));
    }

    @Override
    public int getImageRes() {
        return R.drawable.square;
    }

    @Override
    public String getFeatureName() {
        return "Przekątna";
    }

    @Override
    public String toString() {
        return "KWADRAT o polu " + getCalculatedArea() + " " + " i przekatnej " + getCalculatedFeature();
    }
}
