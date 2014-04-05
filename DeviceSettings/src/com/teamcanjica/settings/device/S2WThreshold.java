package com.teamcanjica.settings.device;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.AttributeSet;

public class S2WThreshold extends EditTextPreference implements OnPreferenceChangeListener {

	public S2WThreshold(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnPreferenceChangeListener(this);
	}
	
	private static final String FILE = "/sys/kernel/bt404/sweep2wake";
	
	public static boolean isSupported() {
		return Utils.fileExists(FILE);
	}
	
	/**
	 * Restore threshold value from SharedPreferences.
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
		
		Utils.writeValue(FILE, "threshold_x=" + sharedPrefs.getString(
				DeviceSettings.KEY_X_SWEEP2WAKE, "120"));

		Utils.writeValue(FILE, "threshold_y=" + sharedPrefs.getString(
				DeviceSettings.KEY_Y_SWEEP2WAKE, "240"));
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		String key = preference.getKey();
		
		if (key.equals(DeviceSettings.KEY_X_SWEEP2WAKE)) {
		Utils.writeValue(FILE, "threshold_x=" + newValue);
		}
		
		if (key.equals(DeviceSettings.KEY_Y_SWEEP2WAKE)) {
			Utils.writeValue(FILE, "threshold_y=" + newValue);
		}
		return true;
	}

}
