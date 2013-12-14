
package com.satton.activitylifecycle.internal;

import java.lang.reflect.Method;

import com.satton.activitylifecycle.ActivityLifecycleManager;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityResult;
import android.content.IntentFilter;

class ActivityLifecycleFor2 extends Instrumentation.ActivityMonitor implements Runnable {

    static ActivityLifecycleFor2 monitor;
    static Method isResumedM = null;

    public ActivityLifecycleFor2(IntentFilter which, ActivityResult result, boolean block) {
        super(which, result, block);
        try {
            isResumedM = Activity.class.getMethod("isResumed", null);
            monitor = this;
            Thread t = new Thread(monitor);
            t.start();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            final Activity activity = monitor.waitForActivityWithTimeout(5 * 60 * 1000);
            if (activity == null) {
                continue;
            }
            if (activity.isFinishing()) {
                continue;
            }
            try {
                Boolean b = (Boolean) isResumedM.invoke(activity, null);
                if (!b) {
                    ActivityLifecycleManager.doNotify(ActivityLifecycleManager.Status.CREATE, activity);
                } else {
                    ActivityLifecycleManager.doNotify(ActivityLifecycleManager.Status.RESUME, activity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
