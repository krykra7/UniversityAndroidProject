package com.kkrawczyk.podstawyandroidaprojekt.model;

import com.kkrawczyk.podstawyandroidaprojekt.R;
import com.kkrawczyk.podstawyandroidaprojekt.utilities.NumberFormatUtils;

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
        area = NumberFormatUtils.formatDouble(side * side);
        return area;
    }

    @Override
    public double getCalculatedFeature() {
        feature = NumberFormatUtils.formatDouble(side * sqrt(2));
        return feature;
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
        return "KWADRAT";
    }
}
