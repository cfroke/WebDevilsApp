/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp;


import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;


public class CommentSubmission extends Fragment {

    List<Comment> commentList = SampleComments.commentList;
    CommentDataSource mDataSource;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment_submission, container, false);

        //mDataSource = new CommentDataSource();
        //mDataSource.open();
        //mDataSource.seedDatabase(commentList);

        //List<Comment> listFromDB = mDataSource.getAllComments();

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