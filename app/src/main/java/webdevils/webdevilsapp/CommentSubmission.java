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
import java.util.List;


/**
 * This class connects to the Concept Vote Xml and allows interaction between pages
 */
public class CommentSubmission extends Fragment {

    List<Comment> commentList = SampleComments.commentList;
    CommentDataSource mDataSource;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment_submission, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        /////////////////////Begin Load of Comments to comment page///////////////////////////////////
        ListView listView1 = (ListView) getView().findViewById(R.id.commentList);


        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(getActivity(),
                android.R.layout.simple_list_item_1, commentList);
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