
package example;

import com.satton.activitylifecycle.ActivityLifecycleManager;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ActivityLifecycleManager.registerLifecycle(this);

        ActivityLifecycleManager.addCallback(new ActivityLifecycleManager.Callbacks() {
            @Override
            public void onActivityResumed(Activity activity) {
                String str = String.format("onActivityResumed:%s", activity.getClass().getSimpleName());
                showToast(str);
            }

        });

    }

    Toast toast = null;

    private void showToast(final String str) {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (toast != null)
                    toast.cancel();
                toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
