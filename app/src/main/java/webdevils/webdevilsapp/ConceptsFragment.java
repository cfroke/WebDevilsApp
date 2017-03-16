package webdevils.webdevilsapp;

/**
 * Created by caseyfroke on 1/29/17.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
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

import static webdevils.webdevilsapp.R.id.parent;

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
        ListView listView1 = (ListView) getView().findViewById(R.id.list);

        LinkedList<Concept> userConceptList = services.getConceptsByUser(currentUser);
        List<Map<String,String>> titleList = new ArrayList<>();
        for( Concept concept : userConceptList ) {
            Map<String, String> conceptData = new HashMap<>(2);
            conceptData.put("title", concept.getTitle().toUpperCase());
            conceptData.put("description", concept.getDescription());
            conceptData.put("status", concept.getStatus());
            titleList.add(conceptData);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),titleList,
                R.layout.concept_layout_new, new String[] {"title","description", "status"},
                new int[] {R.id.text01, R.id.text02, R.id.text03}) {
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

                return view;
            }
        };
        listView1.setAdapter(adapter);

        /////////////////////End Load of Titles to MyConcepts/////////////////////////////////////

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
