/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp.Employee;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import common.Concept;
import webdevils.webdevilsapp.Employee.ListContent.ConceptListContent;
import webdevils.webdevilsapp.R;

/**
 * This fragment is used to list all concepts that have been approved and allows an employee to
 * make a concept sticky (or un-sticky it)
 *
 * Activities containing this fragment MUST implement the
 * {@link OnApprovedListFragmentInteractionListener} interface.
 */
public class EmpApprovedConceptListFragment extends Fragment {

    private OnApprovedListFragmentInteractionListener mListener;

    /**
     * Mandatory constructor
     */
    public EmpApprovedConceptListFragment() {
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
     * Also, links data to the view using {@link EmpApprovedConceptRecyclerViewAdapter}
     *
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.emp_list_approved, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new EmpApprovedConceptRecyclerViewAdapter(
                    ConceptListContent.APPROVED_ITEMS, mListener));
        }
        return view;
    }

    /**
     * When this fragment is attached to the main activity, this method checks to make sure that
     * the listener for this fragment has been registered with the main activity
     * @param context Context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnApprovedListFragmentInteractionListener) {
            mListener = (OnApprovedListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRejectedListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnApprovedListFragmentInteractionListener {
        void onApprovedConceptListFragmentInteraction(Concept concept);
    }
}
