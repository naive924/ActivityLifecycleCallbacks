
package com.satton.activitylifecycle.internal;

import com.satton.activitylifecycle.ActivityLifecycleManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

@SuppressLint("NewApi")
public class ActivityLifecycle implements ActivityLifecycleCallbacks {
    //public class ActivityLifecycle {

    ActivityLifecycle() {
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ActivityLifecycleManager.doNotify(ActivityLifecycleManager.Status.CREATE, activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ActivityLifecycleManager.doNotify(ActivityLifecycleManager.Status.RESUME, activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ActivityLifecycleManager.doNotify(ActivityLifecycleManager.Status.STOP, activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivityLifecycleManager.doNotify(ActivityLifecycleManager.Status.DESTROY, activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

}
