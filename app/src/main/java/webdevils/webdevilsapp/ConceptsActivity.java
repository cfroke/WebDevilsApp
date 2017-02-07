package webdevils.webdevilsapp;

/**
 * Created by caseyfroke on 1/29/17.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ConceptsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concepts);

        Button submitNewConcept = (Button) findViewById(R.id.button2);

        submitNewConcept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SubmitConceptActivity.class);
                startActivity(i);
            }
        });

    }
}
