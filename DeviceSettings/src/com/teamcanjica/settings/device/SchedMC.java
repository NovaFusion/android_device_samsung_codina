package com.teamcanjica.settings.device;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.AttributeSet;

public class SchedMC extends ListPreference implements OnPreferenceChangeListener {

	public SchedMC(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnPreferenceChangeListener(this);
	}

	private static final String FILE = "/sys/devices/system/cpu/sched_mc_power_savings";

	public static boolean isSupported() {
		return Utils.fileExists(FILE);
	}

	/**
	 * Restore SchedMC mode from SharedPreferences.
	 * 
	 * @param context
	 *            The context to read the SharedPreferences from
	 */
	public static void restore(Context context) {
		if (!isSupported()) {
			return;
		}

		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		Utils.writeValue(FILE,
				sharedPrefs.getString(DeviceSettings.KEY_SCHED_MC, "0"));
	}

	public boolean onPreferenceChange(Preference preference, Object newValue) {
		Utils.writeValue(FILE, (String) newValue);
		return true;
	}

}