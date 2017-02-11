package webdevils.webdevilsapp;

/**
 * Created by caseyfroke on 1/29/17.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import common.User;

public class ConceptsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concepts);
        // gets user object passed from previous pages. Can use to get users concepts from storage
        final User user = (User) getIntent().getSerializableExtra("userObject");

        Button submitNewConcept = (Button) findViewById(R.id.button2);

        submitNewConcept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SubmitConceptActivity.class);
                i.putExtra("userObject", user); // temp passing of obj
                startActivity(i);
            }
        });

    }
}
