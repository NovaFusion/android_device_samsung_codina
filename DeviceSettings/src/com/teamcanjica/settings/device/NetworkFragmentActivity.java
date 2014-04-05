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

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class NetworkFragmentActivity extends PreferenceFragment {

//	private static final String TAG = "GalaxyAce2_Settings_Network";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.network_preferences);

		getActivity().getActionBar().setTitle(getResources().getString(R.string.network_name));
		getActivity().getActionBar().setIcon(getResources().getDrawable(R.drawable.network_icon));
	}

}
