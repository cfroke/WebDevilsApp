package webdevils.webdevilsapp;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import common.Concept;
import common.User;
import server.Services;

/**
 * Created by caseyfroke on 4/2/17.
 */
public class CommentSubmission extends Fragment {
    Concept concept;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment_submission, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Show list of comments


        ImageButton btnClose = (ImageButton) getView().findViewById(R.id.closeButton);
        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame, new FeaturedFragment()).commit();
            }
        });

    }
}