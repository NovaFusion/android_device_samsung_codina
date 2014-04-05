package com.teamcanjica.settings.device;

import com.teamcanjica.settings.device.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

public class AnaGain3Control extends CustomSeekBarDialogPreference {
    
    private static final String FILE = " /sys/kernel/abb-codec/anagain3";

	public AnaGain3Control(Context context) {
		this(context, null);
	}

	public static boolean isSupported() {
		return Utils.fileExists(FILE);
	}

	/**
	 * Restore AnaGain3 Control settings from SharedPreferences.
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
		Utils.writeValue(FILE, "gain=" +
				sharedPrefs.getString(DeviceSettings.KEY_ANAGAIN3_CONTROL, "0"));
	}

	public AnaGain3Control(Context context, AttributeSet attrs)
    {
        super(context, attrs);
 
        // get attributes specified in XML
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomSeekBarDialogPreference, 0, 0);
        try
        {
            setMinProgress(a.getInteger(R.styleable.CustomSeekBarDialogPreference_min, DEFAULT_MIN_PROGRESS));
            setMaxProgress(a.getInteger(R.styleable.CustomSeekBarDialogPreference_android_max, DEFAULT_MAX_PROGRESS));
            setProgressTextSuffix(a.getString(R.styleable.CustomSeekBarDialogPreference_progressTextSuffix));
        }
        finally
        {
            a.recycle();
        }
 
        // set layout
        setDialogLayoutResource(R.layout.preference_seek_bar_dialog);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);
        setDialogIcon(null);
    }

	@Override
    protected void onDialogClosed(boolean positiveResult)
    {
        super.onDialogClosed(positiveResult);
 
        // when the user selects "OK", persist the new value
        if (positiveResult)
        {
            int seekBarProgress = mSeekBar.getProgress() + mMinProgress;
            if (callChangeListener(seekBarProgress))
            {
                Utils.writeValue(FILE, "gain=" + seekBarProgress);
                setProgress(seekBarProgress);
            }
        }
    }
}
