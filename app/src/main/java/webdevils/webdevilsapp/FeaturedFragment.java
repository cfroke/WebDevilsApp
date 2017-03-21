package webdevils.webdevilsapp;

// This fragment shows the Featured Concepts (approved)
// on the Featured Concepts page. From here a user
// can select a concept to read more about it and rate
// and/or comment on it.

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import common.Concept;
import server.Services;
import webdevils.webdevilsapp.R;
import webdevils.webdevilsapp.SubmitConceptFragment;

public class FeaturedFragment extends Fragment {
    Services services = new Services();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_featured, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        /////////////////Begin Load of Featured(approved) Titles to Featured Concepts///////////
        ListView listView1 = (ListView) getView().findViewById(R.id.list);

        LinkedList<Concept> conceptList = Services.getApprovedConcepts();
        List<Map<String,String>> titleList = new ArrayList<>();
        for( Concept concept : conceptList ) {
            Map<String, String> conceptData = new HashMap<>(2);
            conceptData.put("title", concept.getTitle().toUpperCase());
            // add submitter, vote count, and comment count(to-do)
            conceptData.put("extra", "Submitted by: " +
                    concept.getUserThatCreatedThisConcept().getUserName());
            conceptData.put("votes", ""+concept.getUpvoteStatus());
            titleList.add(conceptData);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),titleList,
                R.layout.featured_layout_new, new String[] {"title", "extra", "votes"},
                new int[] {R.id.text01, R.id.text02, R.id.myImageViewText}) {
        };
        listView1.setAdapter(adapter);

        /////////////////////End Load of Titles to Featured Concepts////////////////////////////

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