package webdevils.webdevilsapp.Employee;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import common.Concept;
import webdevils.webdevilsapp.R;

/**
 *  Kevin 03/12/2017
 *
 * Activities that contain this fragment must implement the
 * {@link onEmpConceptReviewFragmentInteraction} interface
 * to handle interaction events.
 */
public class EmpConceptReviewFragment extends Fragment {

    public static Concept conceptUnderReview = EmpConceptRecyclerViewAdapter.conceptUnderReview;

    private onEmpConceptReviewFragmentInteraction mListener;

    public EmpConceptReviewFragment() {
        // Required empty public constructor
    }

    public static void setConceptUnderReview(Concept concept){
        conceptUnderReview = concept;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.emp_concept_review_fragment, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onEmpConceptReviewFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof onEmpConceptReviewFragmentInteraction) {
            mListener = (onEmpConceptReviewFragmentInteraction) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement onEmpConceptReviewFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface onEmpConceptReviewFragmentInteraction {
        void onEmpConceptReviewFragmentInteraction(Uri uri);
    }
}
