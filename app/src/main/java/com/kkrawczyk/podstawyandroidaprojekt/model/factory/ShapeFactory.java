package com.kkrawczyk.podstawyandroidaprojekt.model.factory;

import com.kkrawczyk.podstawyandroidaprojekt.model.Circle;
import com.kkrawczyk.podstawyandroidaprojekt.model.Shape;
import com.kkrawczyk.podstawyandroidaprojekt.model.Square;
import com.kkrawczyk.podstawyandroidaprojekt.model.Triangle;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class ShapeFactory {

    public Shape getShape(shapes shapeType, double dimension) {
        switch (shapeType) {
            case CIRCLE:
                return new Circle(dimension);
            case SQUARE:
                return new Square(dimension);
            case TRIANGLE:
                return new Triangle(dimension);
            default:
                throw new IllegalArgumentException("Given type does not exist");
        }
    }

    public enum shapes {
        CIRCLE, SQUARE, TRIANGLE
    }
}
