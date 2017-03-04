package webdevils.webdevilsapp;

/**
 * Created by caseyfroke on 1/29/17.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.LinkedList;

import common.Concept;
import static server.Storage.conceptList;

public class ConceptsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_concepts, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        /////////////////////Begin Load of Titles to MyConcepts///////////////////////////////////
        ListView listView1 = (ListView) getView().findViewById(R.id.list);

        LinkedList<String> titleList = new LinkedList<String>();
        for(Concept concept:conceptList) {
            titleList.add(concept.getTitle());
        }

        String[] listConcept = titleList.toArray(new String[titleList.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, listConcept);
        listView1.setAdapter(adapter);
        /////////////////////End Load of Titles to MyConcepts/////////////////////////////////////

        Button submitNewConcept = (Button) getView().findViewById(R.id.Submit_new_Concept);

        submitNewConcept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new SubmitConceptFragment()).commit();
            }
        });

    }
}
