package webdevils.webdevilsapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import common.Concept;
import common.User;
import server.Services;


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

        // gets scores from all users to list on landing page for top users
        LinkedList<User> users = Services.getAllUsers();
        // sorts user scores
        Collections.sort(users, new Comparator<User>(){
            public int compare(User c1, User c2){
                if(Services.getUserScore(c1) == Services.getUserScore(c2))
                    return 0;
                return Services.getUserScore(c1) > Services.getUserScore(c2) ? -1 : 1;
            }
        });

        // Populate table with top 5 users
        final TextView row1name = (TextView) getView().findViewById(R.id.TextViewTable101);
        final TextView row1score = (TextView) getView().findViewById(R.id.TextViewTable102);
        row1name.setText(users.get(0).getUserName().toString());
        row1score.setText(String.valueOf(Services.getUserScore(users.get(0))));

        final TextView row2name = (TextView) getView().findViewById(R.id.TextViewTable201);
        final TextView row2score = (TextView) getView().findViewById(R.id.TextViewTable202);
        row2name.setText(users.get(1).getUserName().toString());
        row2score.setText(String.valueOf(Services.getUserScore(users.get(1))));

        final TextView row3name = (TextView) getView().findViewById(R.id.TextViewTable301);
        final TextView row3score = (TextView) getView().findViewById(R.id.TextViewTable302);
        row3name.setText(users.get(2).getUserName().toString());
        row3score.setText(String.valueOf(Services.getUserScore(users.get(2))));

        final TextView row4name = (TextView) getView().findViewById(R.id.TextViewTable401);
        final TextView row4score = (TextView) getView().findViewById(R.id.TextViewTable402);
        row4name.setText(users.get(3).getUserName().toString());
        row4score.setText(String.valueOf(Services.getUserScore(users.get(3))));

        final TextView row5name = (TextView) getView().findViewById(R.id.TextViewTable501);
        final TextView row5score = (TextView) getView().findViewById(R.id.TextViewTable502);
        row5name.setText(users.get(4).getUserName().toString());
        row5score.setText(String.valueOf(Services.getUserScore(users.get(4))));

        //buttons to use for navigation
        Button myConceptButton = (Button) getView().findViewById(R.id.buttonMy);
        Button featureConceptButton = (Button) getView().findViewById(R.id.buttonFeatured);
        Button feedbackButton = (Button) getView().findViewById(R.id.buttonFeedback);

        myConceptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame, new ConceptsFragment()).commit();
            }
        });

        featureConceptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame, new FeaturedFragment()).commit();
            }
        });

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame, new FeedbackFragment()).commit();
            }
        });

    }

}
