package com.kkrawczyk.podstawyandroidaprojekt.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.text.Html;

import com.kkrawczyk.podstawyandroidaprojekt.R;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener {

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
}
