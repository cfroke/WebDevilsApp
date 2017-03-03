package webdevils.webdevilsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import common.Concept;

public class ConceptDetailsActivity extends AppCompatActivity {
    private Concept thisConcept;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public ConceptDetailsActivity(Concept thisConcept) {
        this.thisConcept = thisConcept;
        TextView title = (TextView) findViewById(R.id.txtTitle);
        title.setText(thisConcept.getTitle());
        TextView desc = (TextView) findViewById(R.id.txtBody);
        desc.setText(thisConcept.getDescription());
        TextView score = (TextView) findViewById(R.id.txtVoteScore);
        score.setText("Score: " + thisConcept.getUpvoteStatus());
    }

    Button btnDownVote = (Button) findViewById(R.id.btnDownVote);
    btnDownVote.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            thisConcept.updateUpvoteStatus(false);
        }
    });

    Button btnUpVote = (Button) findViewById(R.id.btnUpVote);
    btnDownVote.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            thisConcept.updateUpvoteStatus(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concept_details);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
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
