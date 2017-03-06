package webdevils.webdevilsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import common.Concept;
import server.Services;

public class ConceptDetailsActivity extends Fragment {
    private Concept thisConcept;
    Services services = new Services();
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_concept_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Button btnDownVote = (Button) getView().findViewById(R.id.btnDownVote);
        btnDownVote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                services.downVoteConcept(thisConcept);
            }
        });

        Button btnUpVote = (Button) getView().findViewById(R.id.btnUpVote);
        btnUpVote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                services.upVoteConcept(thisConcept);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
