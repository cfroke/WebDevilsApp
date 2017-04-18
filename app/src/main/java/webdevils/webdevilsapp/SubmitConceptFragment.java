/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import common.Concept;
import common.User;
import server.Services;

/**
 * This fragment allows a user to submit a new concept to the
 * server. Users must provide a Title, Type, and Description
 * for a new concept. Optionally they may use the toggle button
 * to add a collaborator (of their choosing),  providing another
 * members user name to make them a co-author of the concept.
 */
public class SubmitConceptFragment extends Fragment {
    private String conceptTitle = "";
    private String conceptDesc = "";
    private String conceptType = "";
    private String collaboratorName = "";
    private final String commented = "";
    private Concept concept;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_submit_concept, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Spinner dropdown = (Spinner) getView().findViewById(R.id.spinnerType);
        String[] items = new String[]{"Select Concept Type", "Product", "Service", "Improvement"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        //getting user object that has been passed by other pages
        //final User user = (User) getIntent().getSerializableExtra("userObject");
        final Services services = new Services();

        final CheckBox toggle = (CheckBox) getView().findViewById(R.id.collabCheckBox);
        final EditText collaborator = (EditText) getView().findViewById(R.id.collaboratorText);
        collaborator.setFocusable(false); // initially set collaborator text box to disabled
        collaborator.setBackgroundColor(Color.LTGRAY);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { // enables collab text field
                    collaborator.setFocusableInTouchMode(true);
                    collaborator.setBackgroundColor(Color.WHITE);
                } else { // clears collab text and disables entry
                    collaborator.setText("");
                    collaborator.setBackgroundColor(Color.LTGRAY);
                    collaborator.setFocusable(false);
                }
            }
        });

        //Create concept
        Button submit = (Button) getView().findViewById(R.id.buttonSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(v.getContext());
                EditText title = (EditText) getView().findViewById(R.id.titleText);
                EditText description = (EditText) getView().findViewById(R.id.descriptionText);
                Spinner type = (Spinner) getView().findViewById(R.id.spinnerType);
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
                } else if (title.getText().toString().equals("")) {
                    dlgAlert.setMessage("Please enter a title.");
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
                } else if (description.getText().toString().equals("")) {
                    dlgAlert.setMessage("Please enter a description.");
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
                } else if (toggle.isChecked() && collaborator.getText().toString().equals("")) {
                    dlgAlert.setMessage("Please enter a collaborator.");
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
                    if (toggle.isChecked() ) {
                        collaboratorName = collaborator.getText().toString();
                    } else {
                        collaboratorName = "";
                    }
                    //Gets user stored in MainActivity
                    final User user = ((MainActivity)getActivity()).getUser();
                    //Submits concept to Storage
                    concept = services.createConcept(user, conceptTitle, conceptDesc, conceptType, collaboratorName);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.content_frame, new ConceptsFragment()).commit();
                }

            }
        });
    }
}
