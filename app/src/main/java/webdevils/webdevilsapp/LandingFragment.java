package webdevils.webdevilsapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Kyle3 on 1/23/2017.
 */


public class LandingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_landing, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //buttons to use for navigation
        Button myConceptButton = (Button) getView().findViewById(R.id.buttonMy);
        Button featureConceptButton = (Button) getView().findViewById(R.id.buttonFeatured);
        Button feedbackButton = (Button) getView().findViewById(R.id.buttonFeedback);

        myConceptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new ConceptsFragment()).commit();
            }
        });

        featureConceptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new FeaturedFragment()).commit();
            }
        });

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new FeedbackFragment()).commit();
            }
        });

    }

}
