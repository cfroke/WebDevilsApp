/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp.Employee;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import common.Concept;
import webdevils.webdevilsapp.R;

/**
 * This fragment is used to review a given concept and update the concept as needed.
 *
 * Activities containing this fragment MUST implement the
 * {@link onEmpConceptReviewFragmentInteraction} interface.
 */
public class EmpConceptReviewFragment extends Fragment {

    public static Concept conceptUnderReview = EmpSubmittedConceptRecyclerViewAdapter.conceptUnderReview;

    private onEmpConceptReviewFragmentInteraction mListener;

    /**
     * Mandatory constructor
     */
    public EmpConceptReviewFragment() {
        // Required empty public constructor
    }

    /**
     * Sets the current concept that is under review
     * @param concept Concept
     */
    public static void setConceptUnderReview(Concept concept){
        conceptUnderReview = concept;

    }

    /**
     * Creates this fragment when told to do so by the system
     * @param savedInstanceState Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates the view for this fragment that is used in the main activity (view controller)
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.emp_concept_review_fragment, container, false);
    }

    /**
     * When this fragment is attached to the main activity, this method checks to make sure that
     * the listener for this fragment has been registered with the main activity
     * @param context Context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onEmpConceptReviewFragmentInteraction) {
            mListener = (onEmpConceptReviewFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onEmpConceptReviewFragmentInteraction");
        }
    }

    /**
     * Removes the listener for this fragment from the main activity so it doesn't interfere
     * with other fragments in the main activity
     */
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
        void onEmpConceptReviewFragmentInteraction();
    }
}
