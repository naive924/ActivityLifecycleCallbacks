
package example;

import com.satton.aclifecycle.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        ((TextView) findViewById(R.id.text)).setText(this.getClass().getSimpleName());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
