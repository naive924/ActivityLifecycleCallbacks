
package example;

import com.satton.aclifecycle.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_1 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        ((TextView) findViewById(R.id.text)).setText(this.getClass().getSimpleName());

    }

}
