package com.kkrawczyk.podstawyandroidaprojekt.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.text.Html;
import android.widget.Toast;

import com.kkrawczyk.podstawyandroidaprojekt.R;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.prefs_settings);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount();

        for (int i = 0; i < count; i++) {
            Preference pref = preferenceScreen.getPreference(i);
            String value = sharedPreferences.getString(pref.getKey(), "");
            pref.setSummary(Html.fromHtml("<font color='#ffffff'>" + value + "</font>"));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        findPreference(getString(R.string.prefs_settings_count_key)).setOnPreferenceChangeListener(this);
        findPreference(getString(R.string.prefs_settings_min_length_key)).setOnPreferenceChangeListener(this);
        findPreference(getString(R.string.prefs_settings_max_length_key)).setOnPreferenceChangeListener(this);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        String newValue = sharedPreferences.getString(key, "");

        if (preference != null) {
            preference.setSummary(Html.fromHtml("<font color='#ffffff'>" + newValue + "</font>"));
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Preference minSideLengthPref = findPreference(getString(R.string.prefs_settings_min_length_key));
        Preference maxSideLengthPref = findPreference(getString(R.string.prefs_settings_max_length_key));

        if (newValue.toString().equals("")) {
            return false;
        }

        if (preference == minSideLengthPref) {
            Double newMinLength = Double.valueOf(newValue.toString());
            Double maxSideLength = Double.valueOf(maxSideLengthPref.getSummary().toString());

            return showToastInformation(newMinLength < maxSideLength);
        } else if (preference == maxSideLengthPref) {
            Double newMaxLength = Double.valueOf(newValue.toString());
            Double minSideLength = Double.valueOf(minSideLengthPref.getSummary().toString());

            return showToastInformation(newMaxLength > minSideLength);
        }

        return true;
    }

    private boolean showToastInformation(boolean isCorrectValue) {
        if (isCorrectValue) {
            return true;
        } else {
            Toast.makeText(getActivity(), "Minimalna długośc boku musi być mniejsza od maksymalnej",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
