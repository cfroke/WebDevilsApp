package webdevils.webdevilsapp;

/**
 * Created by caseyfroke on 1/29/17.
 */

// This Fragment shows the My Concepts (all statuses)
// on the My Concepts page. From here the member can
// select a concept to view, edit , or delete its contents.

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import common.Concept;
import common.User;
import server.Services;



public class ConceptsFragment extends Fragment {
    User currentUser = LoginActivity.currentUser;
    Services services = new Services();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_concepts, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        /////////////////////Begin Load of Titles to MyConcepts///////////////////////////////////
        final ListView listView1 = (ListView) getView().findViewById(R.id.list);

        LinkedList<Concept> userConceptList = services.getConceptsByUser(currentUser);
        LinkedList<Concept> allConceptList = services.getAllConcepts();
        List<Map<String,String>> titleList = new ArrayList<>();
        // checks for and adds concepts user has submitted
        for( Concept concept : userConceptList) {
            Map<String, String> conceptData = new HashMap<>(2);
            conceptData.put("title", concept.getTitle().toUpperCase());
            conceptData.put("description", concept.getDescription());
            conceptData.put("status", concept.getStatus());
            conceptData.put("collaborator", concept.getCollaborator());
            titleList.add(conceptData);
        }
        // checks for and adds concepts user is collaborator on
        for( Concept concept : allConceptList) {
            if (concept.getCollaborator().equals(currentUser.getUserName())) {
                Map<String, String> conceptData = new HashMap<>(2);
                conceptData.put("title", concept.getTitle().toUpperCase());
                conceptData.put("description", concept.getDescription());
                conceptData.put("status", concept.getStatus());
                conceptData.put("collaborator", concept.getCollaborator()); // passes for collab info
                titleList.add(conceptData);
            }
        }

        // Builds layout for each concept in the list. Note that collaborator is a hidden field, used
        // only for determining whether listed concept is a collaboration
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),titleList,
                R.layout.concept_layout_new, new String[] {"title","description", "status", "collaborator"},
                new int[] {R.id.text01, R.id.text02, R.id.text03, R.id.text04}) {
            //this override of getView changes the status color text
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView status = (TextView) view.findViewById(R.id.text03);

                if (status.getText().toString() == "Approved") {
                    status.setTextColor(Color.rgb(0,100,0));
                } else if ((status.getText().toString() == "Rejected")){
                    status.setTextColor(Color.RED);
                }
                // adds a handshake icon for collaborated concepts
                TextView collaborator = (TextView) view.findViewById(R.id.text04); // hidden asset
                if (collaborator.getText().toString() != "") {
                    TextView textView = (TextView) view.findViewById(R.id.text01);
                    Drawable myImage = getResources().getDrawable(R.mipmap.collaborate);
                    myImage.setBounds(0,0,textView.getLineHeight(),textView.getLineHeight());
                    textView.setCompoundDrawables(myImage, null, null, null);
                }

                return view;
            }
        };
        listView1.setAdapter(adapter);

        /////////////////////End Load of Titles to MyConcepts/////////////////////////////////////

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                //Get title name to display expanded concept
                TextView selectedConcept = (TextView) v.findViewById(R.id.text01);
                String selectedTitle = selectedConcept.getText().toString();
                ((MainActivity)getActivity()).openMyConcept(selectedTitle);
            }
        });

        Button submitNewConcept = (Button) getView().findViewById(R.id.Submit_new_Concept);

        submitNewConcept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new SubmitConceptFragment()).commit();
            }
        });

    }
}
