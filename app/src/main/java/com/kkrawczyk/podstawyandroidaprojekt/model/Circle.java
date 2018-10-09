package com.kkrawczyk.podstawyandroidaprojekt.model;

import com.kkrawczyk.podstawyandroidaprojekt.R;
import com.kkrawczyk.podstawyandroidaprojekt.utilities.NumberFormatUtils;

import java.io.Serializable;

import static java.lang.Math.pow;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class Circle extends Shape implements Serializable {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getCalculatedArea() {
        area = NumberFormatUtils.formatDouble(Math.PI * pow(radius, 2));
        return area;
    }

    @Override
    public double getCalculatedFeature() {
        feature = NumberFormatUtils.formatDouble(radius * 2);
        return feature;
    }

    @Override
    public int getImageRes() {
        return R.drawable.circle;
    }

    @Override
    public String getFeatureName() {
        return "Średnica";
    }

    @Override
    public String toString() {
        return CIRCLE;
    }
}
