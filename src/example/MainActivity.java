
package example;

import com.satton.aclifecycle.R;
import com.satton.activitylifecycle.ActivityLifecycleManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        addView();

        ActivityLifecycleManager.registerLifecycle(getApplication());
        ActivityLifecycleManager.addCallback(new ActivityLifecycleManager.Callbacks() {
            @Override
            public void onActivityResumed(Activity activity) {
                String str = String.format("onResumed:%s", activity.getClass().getSimpleName());
                showToast(str);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                String str = String.format("onDestroyed:%s", activity.getClass().getSimpleName());
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
                System.out.println(str);
                toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void addView() {
        @SuppressWarnings("rawtypes")
        Class[] as = {
                Activity_1.class,
                Activity_2.class,
                Activity_3.class,
        };
        for (final Class<Activity> c : as) {
            Button b = new Button(this);
            b.setText(c.getSimpleName());
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, c));
                }
            });

            LinearLayout v = (LinearLayout) findViewById(R.id.lay);
            v.addView(b);
        }
    }
}
