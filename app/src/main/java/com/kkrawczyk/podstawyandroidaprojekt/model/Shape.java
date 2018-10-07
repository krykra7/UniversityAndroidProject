package com.kkrawczyk.podstawyandroidaprojekt.model;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public abstract class Shape implements Serializable {

    static DecimalFormat decimalFormat = new DecimalFormat(".###");

    public abstract double getCalculatedArea();

    public abstract double getCalculatedFeature();

    public abstract int getImageRes();

    public abstract String getFeatureName();
}
