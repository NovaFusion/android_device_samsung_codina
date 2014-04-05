/*
 * Copyright (C) 2012 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.teamcanjica.settings.device;

import com.teamcanjica.settings.device.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;

public class PowermgmtFragmentActivity extends PreferenceFragment {

	private static final String TAG = "GalaxyAce2_Settings_Advanced";

	private static final String FILE_WIFI_PM = "/sys/module/dhd/parameters/dhdpm_fast";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.powermgmt_preferences);

		getActivity().getActionBar().setTitle(getResources().getString(R.string.powermgmt_name));
		getActivity().getActionBar().setIcon(getResources().getDrawable(R.drawable.powermgmt_icon));
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {

		String boxValue;
		String key = preference.getKey();

		Log.w(TAG, "key: " + key);

		if (key.equals(DeviceSettings.KEY_USE_WIFIPM_MAX)) {
			boxValue = (((CheckBoxPreference) preference).isChecked() ? "0"
					: "1");
			Utils.writeValue(FILE_WIFI_PM, boxValue);
		}

		return true;
	}

	public static void restore(Context context) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		String wifipmvalue = sharedPrefs.getBoolean(
				DeviceSettings.KEY_USE_WIFIPM_MAX, false) ? "0" : "1";
		Utils.writeValue(FILE_WIFI_PM, wifipmvalue);

	}

}
