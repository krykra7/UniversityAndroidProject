package com.kkrawczyk.podstawyandroidaprojekt.utilities;

import android.content.Context;

import com.kkrawczyk.podstawyandroidaprojekt.model.Shape;
import com.kkrawczyk.podstawyandroidaprojekt.model.factory.ShapeFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class ShapeGenerator {

    private final Context mContext;
    private final Random random = new Random();
    private final ShapeFactory factory = new ShapeFactory();

    public ShapeGenerator(Context context) {
        this.mContext = context;
    }

    public Shape getRandomShape() {
        return factory.getShape(getRandomEnumValue(ShapeFactory.shapes.class), getShapeDimension());
    }

    public ArrayList<Shape> getShapeList(int length) {
        ArrayList<Shape> shapes = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            shapes.add(getRandomShape());
        }

        return shapes;
    }

    private double getShapeDimension() {
        double minLength = ShapePreferencesManager.getMinSideLength(mContext);
        double maxLength = ShapePreferencesManager.getMaxSideLength(mContext);

        double randomInRange = minLength + (maxLength - minLength) * Math.random();

        return NumberFormatUtils.formatDouble(randomInRange);
    }

    private <T extends Enum<?>> T getRandomEnumValue(Class<T> enumType) {
        int index = random.nextInt(enumType.getEnumConstants().length);
        return enumType.getEnumConstants()[index];
    }
}
