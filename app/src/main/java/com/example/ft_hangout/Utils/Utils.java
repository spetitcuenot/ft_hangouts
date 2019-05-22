package com.example.ft_hangout.Utils;

import android.app.Activity;
import android.content.Intent;

import com.example.ft_hangout.R;

public class Utils {
    private static int sTheme;

    public final static int THEME_CUSTOM_ACTION_BAR_THEME_BLUE = 0;
    public final static int THEME_CUSTOM_ACTION_BAR_THEME_ORANGE = 1;

    public static void  changeToTheme(Activity activity, int theme){
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
        activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    public static void  onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case    THEME_CUSTOM_ACTION_BAR_THEME_BLUE:
                activity.setTheme(R.style.Theme_CustomActionBarThemeBlue);
                break;
            case    THEME_CUSTOM_ACTION_BAR_THEME_ORANGE:
                activity.setTheme(R.style.Theme_CustomActionBarThemeOrange);
                break;

        }

    }

}
