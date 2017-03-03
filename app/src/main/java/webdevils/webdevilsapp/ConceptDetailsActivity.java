package webdevils.webdevilsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import common.Concept;
import server.Services;

public class ConceptDetailsActivity extends Fragment {
    private Concept thisConcept;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ConceptDetails Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_concept_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());

        Button btnDownVote = (Button) getView().findViewById(R.id.btnDownVote);
        btnDownVote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Services.downVoteConcept(thisConcept);
            }
        });

        Button btnUpVote = (Button) getView().findViewById(R.id.btnUpVote);
        btnUpVote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Services.upVoteConcept(thisConcept);
            }
        });

        TextView txtTitle = (TextView) getView().findViewById(R.id.txtTitle);
        txtTitle.setText(thisConcept.getTitle());
        TextView txtBody = (TextView) getView().findViewById(R.id.txtBody);
        TextView txtScore = (TextView) getView().findViewById(R.id.txtVoteScore);
        txtScore.setText("Score: " + thisConcept.getUpvoteStatus());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
