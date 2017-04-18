/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import common.Concept;
import server.Services;

public class CommentSubmission extends Fragment {
    Concept concept;
    Services services = new Services();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment_submission, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Show list of comments
        /////////////////////Begin Load of Comments to comment page///////////////////////////////////
        ListView listView1 = (ListView) getView().findViewById(R.id.commentList);

        /*LinkedList<Concept> allComments = concept.getComments(concept);
        LinkedList<String> commentList = new LinkedList<String>();
        for( Concept concept : allComments ) {
            commentList.add(concept.getComments());
        }*/

        String[] listComments = new String[] { "bob: I like the idea you have, nice thinking!!",
                "jane: I really like the direction you went!!", "casey: Glad you chose that idea, hope they use it!!" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, listComments);
        listView1.setAdapter(adapter);
        /////////////////////End Load of Comments to comment page/////////////////////////////////////

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