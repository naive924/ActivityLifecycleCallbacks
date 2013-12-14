
package com.satton.activitylifecycle;

import java.util.ArrayList;
import java.util.Iterator;

import com.satton.activitylifecycle.internal.LifecycleUtil;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

/**
 * @author SATTON 2013/12/14
 * @since 2013/12/14
 * @version 2013/12/14
 */
public class ActivityLifecycleManager {

    private static final String TAG = "LifecycleManager";
    private static ArrayList<Callbacks> callbacks = new ArrayList<Callbacks>();

    /**
     * @param app
     */
    public static void registerLifecycle(Application app) {
        LifecycleUtil.registerActivityLifecycle(app);
    }

    /**
     * @param callback
     */
    public static void addCallback(Callbacks callback) {
        Iterator<Callbacks> it = callbacks.iterator();
        while (it.hasNext()) {
            Callbacks installedListener = it.next();
            if (installedListener.getClass().equals(callback.getClass())) {
                it.remove();
                Log.d(TAG, "remove - " + installedListener.getClass().getName());
            }
        }
        callbacks.add(callback);
        Log.d(TAG, "install - " + callback.getClass().getName() + "  all size =" + callbacks.size());
    }

    /**
     * @param callback
     */
    public static void removeCallback(Callbacks callback) {
        callbacks.remove(callback);
        Log.d(TAG, "remove - " + callback.getClass().getName());
    }

    /**
     * @param status
     */
    public static void doNotify(Status status, Activity activity) {
        if (callbacks.isEmpty())
            return;
        for (final Callbacks listener : callbacks) {
            Log.d(TAG, listener.getClass().getName() + " " + status.toString());
            doNotify(status, activity, listener);
        }
    }

    // ----------------------------------------------------------------------
    // private
    // ----------------------------------------------------------------------
    private static void doNotify(Status status, Activity activity, Callbacks listener) {
        switch (status) {
            case CREATE:
                Log.d(TAG, String.format("onActivityCreated:%s", activity.getClass().getSimpleName()));
                listener.onActivityCreated(activity);
                break;
            case RESUME:
                Log.d(TAG, String.format("onActivityResumed:%s", activity.getClass().getSimpleName()));
                listener.onActivityResumed(activity);
                break;
            case STOP:
                Log.d(TAG, String.format("onActivityStopped:%s", activity.getClass().getSimpleName()));
                listener.onActivityStopped(activity);
                break;
            case DESTROY:
                Log.d(TAG, String.format("onActivityDestroyed:%s", activity.getClass().getSimpleName()));
                listener.onActivityDestroyed(activity);
                break;
            default:
                break;
        }
    }

    public static class Callbacks {

        public void onActivityCreated(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

    }

    public enum Status {
        CREATE,
        RESUME,
        PAUSED,
        STOP,
        DESTROY;
    }
}
