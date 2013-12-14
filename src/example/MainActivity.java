
package example;

import com.satton.aclifecycle.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

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
