package com.kkrawczyk.podstawyandroidaprojekt.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.kkrawczyk.podstawyandroidaprojekt.R;

/**
 * Created by kkrawczyk on 10/5/2018.
 */
public final class ShapePreferencesManager {

    public static int getShapesCountAsInt(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String shapesCountKey = context.getString(R.string.prefs_settings_count_key);

        String shapesCountValue = sp.getString(shapesCountKey, "");

        return Integer.valueOf(shapesCountValue);
    }

    public static double getMaxSideLength(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String sideMaxLengthKey = context.getString(R.string.prefs_settings_max_length_key);

        String sideMaxLengthValue = sp.getString(sideMaxLengthKey, "");

        return Double.valueOf(sideMaxLengthValue);
    }

    public static double getMinSideLength(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String sideMinLengthKey = context.getString(R.string.prefs_settings_min_length_key);

        String sideMinLengthValue = sp.getString(sideMinLengthKey, "");

        return Double.valueOf(sideMinLengthValue);
    }
}
