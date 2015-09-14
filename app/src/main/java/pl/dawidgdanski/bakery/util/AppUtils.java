package pl.dawidgdanski.bakery.util;

import android.app.Activity;
import android.os.Looper;

public final class AppUtils {

    private AppUtils() { }

    public static boolean isThisThreadAMainOne() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void runOnUiThread(final Activity activity, final Runnable runnable) {
        if(activity == null || runnable == null) {
            return;
        }

        activity.runOnUiThread(runnable);
    }
}
