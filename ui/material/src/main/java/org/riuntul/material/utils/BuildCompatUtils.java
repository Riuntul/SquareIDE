package org.riuntul.material.utils;

import android.os.Build.VERSION;

public final class BuildCompatUtils {
    public static boolean isAtLeastS() {
        if (VERSION.SDK_INT < 30) {
            return false;
        }

        return (VERSION.CODENAME.equals("REL") && VERSION.SDK_INT >= 31) || (VERSION.CODENAME.length() == 1 && VERSION.CODENAME.compareTo("S") >= 0 && VERSION.CODENAME.compareTo("Z") <= 0);
    }

    private BuildCompatUtils() {}
}