package webdevils.webdevilsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by Kyle3 on 1/23/2017.
 */

public class LandingActvity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Button myConceptButton = (Button) findViewById(R.id.buttonMy);
        Button featureConceptButton = (Button) findViewById(R.id.buttonFeatured);
        Button feedbackButton = (Button) findViewById(R.id.buttonFeedback);

    }
}
