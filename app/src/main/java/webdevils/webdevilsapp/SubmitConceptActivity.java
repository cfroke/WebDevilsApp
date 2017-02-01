package webdevils.webdevilsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SubmitConceptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_concept);
        Spinner dropdown = (Spinner)findViewById(R.id.spinnerType);
        String[] items = new String[]{"Select Concept Type", "Product", "Service"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        //Setup for creating variables on concept form
        Button submit = (Button) findViewById(R.id.buttonSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText title = (EditText) findViewById(R.id.titleText);
                EditText description = (EditText) findViewById(R.id.descriptionText);
                String conceptTitle = title.getText().toString();
                String conceptDesc = description.getText().toString();
            }
        });
    }
}
