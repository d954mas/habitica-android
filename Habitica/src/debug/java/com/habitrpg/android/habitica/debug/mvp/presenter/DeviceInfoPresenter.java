package com.habitrpg.android.habitica.debug.mvp.presenter;


import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.habitrpg.android.habitica.debug.mvp.view.DeviceInfoView;

@InjectViewState
public class DeviceInfoPresenter extends MvpPresenter<DeviceInfoView> {
    private static final int MAX_STRING_LENGHT = 20;

    public void calculateValues(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        String densityBucket = getDensityString(dm);
        getViewState().setMake(truncateAt(Build.MANUFACTURER, MAX_STRING_LENGHT));
        getViewState().setModel(truncateAt(Build.MODEL, MAX_STRING_LENGHT));
        getViewState().setResolution(dm.heightPixels + "x" + dm.widthPixels);
        getViewState().setDensity(dm.densityDpi + "dpi (" + densityBucket + ")");
        getViewState().setRelease(Build.VERSION.RELEASE);
        getViewState().setApi(String.valueOf(Build.VERSION.SDK_INT));
    }

    private static String getDensityString(DisplayMetrics displayMetrics) {
        switch (displayMetrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                return "ldpi";
            case DisplayMetrics.DENSITY_MEDIUM:
                return "mdpi";
            case DisplayMetrics.DENSITY_HIGH:
                return "hdpi";
            case DisplayMetrics.DENSITY_XHIGH:
                return "xhdpi";
            case DisplayMetrics.DENSITY_XXHIGH:
                return "xxhdpi";
            case DisplayMetrics.DENSITY_XXXHIGH:
                return "xxxhdpi";
            case DisplayMetrics.DENSITY_TV:
                return "tvdpi";
            default:
                return "unknown";
        }
    }

    @SuppressWarnings("checkstyle:avoidinlineconditionals")
    private static String truncateAt(String string, int length) {
        return string.length() > length ? string.substring(0, length) : string;
    }

}
