package com.kkrawczyk.podstawyandroidaprojekt.utilities;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by kkrawczyk on 10/8/2018.
 */
public final class NumberFormatUtils {

    private static DecimalFormat decimalFormat = new DecimalFormat("#.###");
    private static NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());

    public static String getFormattedDoubleAsString(Double value) {
            return decimalFormat.format(value);
    }

    public static double formatDouble(Double value) {
        try {
            String formattedDimension = decimalFormat.format(value);
            return numberFormat.parse(formattedDimension).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public static double convertStringToDouble(String value) {
        try {
            return numberFormat.parse(value).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
