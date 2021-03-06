
package com.satton.activitylifecycle.internal;

import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;

public class LifecycleUtil {

    @SuppressLint("NewApi")
    public static void registerActivityLifecycle(Application app) {
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                app.registerActivityLifecycleCallbacks(new ActivityLifecycle());
                return;
            } else {
                Class<?> atClass = Class.forName("android.app.ActivityThread");
                Method m = atClass.getMethod("currentActivityThread", null);
                Object obj = m.invoke(m, null);

                Method m2 = atClass.getMethod("getInstrumentation", null);

                Instrumentation instrumentation = (Instrumentation) m2.invoke(obj, null);
                IntentFilter filter = new IntentFilter(Intent.ACTION_MAIN);
                filter.addCategory("android.intent.category.LAUNCHER");

                ActivityLifecycleFor2 monitor = new ActivityLifecycleFor2(filter, null, false);
                instrumentation.addMonitor(monitor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
