
package com.satton.activitylifecycle.internal;

import com.satton.activitylifecycle.ActivityLifecycleManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

@SuppressLint("NewApi")
//public class ActivityLifecycle implements ActivityLifecycleCallbacks {
public class ActivityLifecycle {

    /**
     * コンストラクタ
     * 
     * @param mobageApp
     */
    ActivityLifecycle() {
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ActivityLifecycleManager.doNotify(ActivityLifecycleManager.Status.CREATE, activity);
    }

    public void onActivityResumed(Activity activity) {
        ActivityLifecycleManager.doNotify(ActivityLifecycleManager.Status.RESUME, activity);
    }

    public void onActivityStopped(Activity activity) {
        ActivityLifecycleManager.doNotify(ActivityLifecycleManager.Status.STOP, activity);
    }

    public void onActivityDestroyed(Activity activity) {
        ActivityLifecycleManager.doNotify(ActivityLifecycleManager.Status.DESTROY, activity);
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityPaused(Activity activity) {
    }

}
