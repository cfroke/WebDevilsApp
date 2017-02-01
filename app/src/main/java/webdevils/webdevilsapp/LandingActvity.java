package webdevils.webdevilsapp;

import android.content.Intent;
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
        //passes username from login to display on banner Welcome
        Intent p = getIntent();
        final String name = p.getStringExtra("userName");
        setTitle("Welcome " + name);
        //buttons to use for navigation
        Button myConceptButton = (Button) findViewById(R.id.buttonMy);
        Button featureConceptButton = (Button) findViewById(R.id.buttonFeatured);
        Button feedbackButton = (Button) findViewById(R.id.buttonFeedback);

    }
}
