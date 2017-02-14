package webdevils.webdevilsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import server.Services;
import common.User;
import common.Concept;

public class SubmitConceptActivity extends AppCompatActivity {
    private String conceptTitle = "";
    private String conceptDesc = "";
    private String conceptType = "";
    Concept concept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_concept);
        Spinner dropdown = (Spinner)findViewById(R.id.spinnerType);
        String[] items = new String[]{"Select Concept Type", "Product", "Service", "Improvement"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        //getting user object that has been passed by other pages
        final User user = (User) getIntent().getSerializableExtra("userObject");
        final Services services = new Services();
        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);

        //Create concept
        Button submit = (Button) findViewById(R.id.buttonSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText title = (EditText) findViewById(R.id.titleText);
                EditText description = (EditText) findViewById(R.id.descriptionText);
                Spinner type = (Spinner) findViewById(R.id.spinnerType);
                if (type.getSelectedItem().toString().equals("Select Concept Type")) {
                    dlgAlert.setMessage("Please select a concept type.");
                    dlgAlert.setTitle("Invalid Selection");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                } else {
                    conceptTitle = title.getText().toString();
                    conceptDesc = description.getText().toString();
                    conceptType = type.getSelectedItem().toString();
                    concept = services.createConcept(user, conceptTitle, conceptDesc, conceptType);
                    Intent i = new Intent(getApplicationContext(), ConceptsActivity.class);
                    i.putExtra("userObject", user); // temp passing of object
                    startActivity(i);
                }

            }
        });
    }
}
