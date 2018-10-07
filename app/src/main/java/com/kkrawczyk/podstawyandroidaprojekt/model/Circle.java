package com.kkrawczyk.podstawyandroidaprojekt.model;

import com.kkrawczyk.podstawyandroidaprojekt.R;

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
        return Double.parseDouble(decimalFormat.format(Math.PI * pow(radius, 2)));
    }

    @Override
    public double getCalculatedFeature() {
        return Double.parseDouble(decimalFormat.format(radius * 2));
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
        return "KOLO o polu " + getCalculatedArea() + " " + " i srednicy " + getCalculatedFeature();
    }
}
