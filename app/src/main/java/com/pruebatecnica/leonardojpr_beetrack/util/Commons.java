package com.pruebatecnica.leonardojpr_beetrack.util;

import android.os.Build;

import com.pruebatecnica.leonardojpr_beetrack.app.App;
/**
 * Created by Leonardojpr on 06/05/18.
 */
public class Commons {

    public static String getString(int string) {
        String result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            result = App.getAppContext().getString(string);
        else
            result = App.getAppContext().getString(string);
        return result;
    }
}
