package webdevils.webdevilsapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import common.Concept;
import server.Services;

/**
 * Created by caseyfroke on 1/29/17. Update 3/29/17
 */

public class ViewEditFragment extends Fragment {
    private Concept thisConcept;
    Services services = new Services();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        String title = this.getArguments().getString("title");
        View myInflatedView = inflater.inflate(R.layout.fragment_view_edit, container,false);
        thisConcept = services.getConceptByTitle(title);
        TextView cTitle = (TextView) myInflatedView.findViewById(R.id.txtTitle);
        TextView cDescription = (TextView) myInflatedView.findViewById(R.id.txtBody);
        cTitle.setText(thisConcept.getTitle());
        cDescription.setText(thisConcept.getDescription());
        return myInflatedView;
    }

    @Override
    public void onStart() {
        super.onStart();

       /* Button btnDelete = (Button) getView().findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                services.deleteConcept(thisConcept);
            }
        });

        Button btnEdit = (Button) getView().findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                services.editConcept(thisConcept);
            }
        });  */

        ImageButton btnClose = (ImageButton) getView().findViewById(R.id.closeButton);
        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new ConceptsFragment()).commit();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

    }

}