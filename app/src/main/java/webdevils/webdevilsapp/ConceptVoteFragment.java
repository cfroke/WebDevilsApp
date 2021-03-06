/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import common.Concept;
import server.Services;

/**
 * When a user clicks on a concept in the Featured Concepts list
 * they are brought to this fragment, where they can vote, and / or
 * make a comment on the concept.
 */
public class ConceptVoteFragment extends Fragment {
    private Concept thisConcept;
    private TextView cScore;
    private final Services services = new Services();
    private Comment comment;
    EditText getComments;
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        String title = this.getArguments().getString("title");
        View myInflatedView = inflater.inflate(R.layout.fragment_concept_vote, container,false);
        thisConcept = services.getConceptByTitle(title);

        TextView cTitle = (TextView) myInflatedView.findViewById(R.id.txtTitle);
        TextView cDescription = (TextView) myInflatedView.findViewById(R.id.txtBody);
        cScore = (TextView) myInflatedView.findViewById(R.id.txtVoteScore);
        cTitle.setText(thisConcept.getTitle());
        cDescription.setText(thisConcept.getDescription());
        cScore.setText("Score: " + String.valueOf(thisConcept.getStarCount()));

        return myInflatedView;
    }

    @Override
    public void onStart() {
        super.onStart();

        final Spinner stars = (Spinner) getView().findViewById(R.id.spinnerVote);
        String[] items = new String[]{"Give Concept Stars!",
                "★",
                "★★",
                "★★★",
                "★★★★",
                "★★★★★"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, items);
        stars.setAdapter(adapter);

        cScore.setText("Score: " + String.valueOf(thisConcept.getStarCount()));

        Button btnUpVote = (Button) getView().findViewById(R.id.btnUpVote);
        btnUpVote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (stars.getSelectedItem().toString().equals("Give Concept Stars!")) {

                    //No Stars =(

                } else if(stars.getSelectedItem().toString().equals("★")){

                    services.giveConceptStars( thisConcept , 1 );

                } else if(stars.getSelectedItem().toString().equals("★★")){

                    services.giveConceptStars( thisConcept , 2 );

                } else if(stars.getSelectedItem().toString().equals("★★★")){

                    services.giveConceptStars( thisConcept , 3 );

                } else if(stars.getSelectedItem().toString().equals("★★★★")){

                    services.giveConceptStars( thisConcept , 4 );

                } else if(stars.getSelectedItem().toString().equals("★★★★★")){

                    services.giveConceptStars( thisConcept , 5 );

                } else {
                    System.out.print("What the! ... huh?");
                }

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame, new FeaturedFragment()).commit();

            }
        });

        Button commentSubmit = (Button) getView().findViewById(R.id.commentSubmit);
        commentSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getComments = (EditText) getView().findViewById(R.id.commentText);
                String commented = getComments.getText().toString();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame, new CommentSubmission()).commit();
            }
        });
    }

}
