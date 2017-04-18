/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import common.Concept;
import server.Services;

/**
 * This fragment shows the Featured Concepts (approved)
 * on the Featured Concepts page. From here a user
 * can select a concept to read more about it and rate
 * and/or comment on it.
 */
public class FeaturedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_featured, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        /////////////////Begin Load of Featured(approved) Titles to Featured Concepts///////////
        final ListView listView1 = (ListView) getView().findViewById(R.id.list);

        LinkedList<Concept> conceptList = Services.getApprovedConcepts();
        //LinkedList<Concept> stickiedList = services.getApprovedConcepts();
        List<Map<String, String>> titleList = new ArrayList<>();

        // sort concepts by vote count descending
        Collections.sort(conceptList, new Comparator<Concept>(){
            public int compare(Concept c1, Concept c2){
                if(c1.getStarCount() == c2.getStarCount())
                    return 0;
                return c1.getStarCount() > c2.getStarCount() ? -1 : 1;
            }
        });

        // gets stickied concepts first and loads them
        for (Concept concept : conceptList) {
            Map<String, String> conceptData = new HashMap<>(2);
            if (concept.isSticky()) {
                conceptData.put("title", concept.getTitle().toUpperCase());
                // add submitter and collaborator (if present)
                if (concept.getCollaborator().equals("")) {
                    conceptData.put("extra", "Submitted by: " +
                            concept.getUserThatCreatedThisConcept().getUserName());
                } else {
                    conceptData.put("extra", "Submitted by: " +
                            concept.getUserThatCreatedThisConcept().getUserName() +
                            " & " + concept.getCollaborator());
                }
                conceptData.put("votes", "" + concept.getStarCount());
                conceptData.put("sticky", "true"); // hidden asset
                titleList.add(conceptData);
            }
        }

        // after stickied, the rest of the concepts get loaded
        for (Concept concept : conceptList) {
            Map<String, String> conceptData = new HashMap<>(2);
            if (!concept.isSticky()) {
                conceptData.put("title", concept.getTitle().toUpperCase());
                // add submitter and collaborator (if present)
                if (concept.getCollaborator().equals("")) {
                    conceptData.put("extra", "Submitted by: " +
                            concept.getUserThatCreatedThisConcept().getUserName());
                } else {
                    conceptData.put("extra", "Submitted by: " +
                            concept.getUserThatCreatedThisConcept().getUserName() +
                            " & " + concept.getCollaborator());
                }
                // add vote count, and comment count(to-do)
                conceptData.put("sticky", "false"); // hidden asset
                conceptData.put("votes", "" + concept.getStarCount());

                titleList.add(conceptData);
            }

        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), titleList,
                R.layout.featured_layout_new, new String[]{"title", "extra", "votes", "sticky"},
                new int[]{R.id.text01, R.id.text02, R.id.myImageViewText, R.id.text04}) {
            //this override of getView changes the status color text
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                // adds a pinned icon for stickied concepts
                TextView sticky = (TextView) view.findViewById(R.id.text04); // hidden asset
                if (sticky.getText().toString() == "true") {
                    TextView textView = (TextView) view.findViewById(R.id.text01);
                    Drawable myImage = getResources().getDrawable(R.mipmap.feature_pin);
                    myImage.setBounds(0,0,textView.getLineHeight(),textView.getLineHeight());
                    textView.setCompoundDrawables(myImage, null, null, null);
                }

                return view;
            }
        };
        listView1.setAdapter(adapter);

        /////////////////////End Load of Titles to Featured Concepts////////////////////////////

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                //Get title name to display expanded concept
                TextView selectedConcept = (TextView) v.findViewById(R.id.text01);
                String selectedTitle = selectedConcept.getText().toString();
                ((MainActivity)getActivity()).openConcept(selectedTitle);
            }
        });

        Button submitNewConcept = (Button) getView().findViewById(R.id.Submit_new_Concept);

        submitNewConcept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame, new SubmitConceptFragment()).commit();
            }
        });

    }

}