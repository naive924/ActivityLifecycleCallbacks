
package com.satton.activitylifecycle.internal;

import com.satton.activitylifecycle.ActivityLifecycleManager;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityResult;
import android.content.IntentFilter;

class ActivityLifecycleFor2 extends Instrumentation.ActivityMonitor implements Runnable {

    static ActivityLifecycleFor2 monitor;

    public ActivityLifecycleFor2(IntentFilter which, ActivityResult result, boolean block) {
        super(which, result, block);
        monitor = this;
        Thread t = new Thread(monitor);
        t.start();

    }

    @Override
    public void run() {
        while (true) {
            final Activity activity = monitor.waitForActivityWithTimeout(5 * 60 * 1000);
            if (activity == null) {
                continue;
            }

            if (!activity.isFinishing()) {
                activity.getClass().getName();
                ActivityLifecycleManager.doNotify(ActivityLifecycleManager.Status.RESUME, activity);
            }
        }
    }
}
