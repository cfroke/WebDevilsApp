package webdevils.webdevilsapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FeedbackFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Button submit = (Button) getView().findViewById(R.id.button3);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TO DO
            }
        });
    }
}
