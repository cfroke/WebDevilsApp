/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp.Employee;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import common.Concept;
import webdevils.webdevilsapp.R;

/**
 * This adapter is used as a bridge between submitted concept data from the server and the
 * "RecyclerView" which is used to display the information in the UI
 */
public class EmpSubmittedConceptRecyclerViewAdapter extends
        RecyclerView.Adapter<EmpSubmittedConceptRecyclerViewAdapter.ViewHolder> {

    private final LinkedList<Concept> mValues;
    private final EmpSubmittedConceptListFragment
            .OnSubmittedListFragmentInteractionListener mListener;
    public static Concept conceptUnderReview;
    private View view;

    /**
     * Constructor for this adapter used to link data and register a listener
     * @param items LinkedList<Concept>
     * @param listener OnSubmittedListFragmentInteractionListener
     */
    public EmpSubmittedConceptRecyclerViewAdapter(
            LinkedList<Concept> items,
                EmpSubmittedConceptListFragment
                        .OnSubmittedListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    /**
     * As the view is being created, each list item is created with this method
     * @param parent ViewGroup
     * @param viewType int
     * @return ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.emp_list_submitted_item_card, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binds data gathered from the constructor to the view item in the UI
     * @param holder ViewHolder
     * @param position int
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        conceptUnderReview = holder.mItem;

        holder.mCreatedByView.setText("Concept Created By: " +
                mValues.get(position).getUserThatCreatedThisConcept().getUserName());
        holder.mTitleView.setText(" Title: " + mValues.get(position).getTitle());
        holder.mEmpReviewedStatus.setText("Status: " + mValues.get(position).getStatus());
        holder.mConceptType.setText("Concept Type: " + mValues.get(position).getType());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface that an item has been selected.
                    mListener.onSubmittedConceptListFragmentInteraction(holder.mItem);
                }
            }
        });

        animate(view);
    }

    /**
     * Animates the list of items to enter the screen in an upward motion
     * @param view View
     */
    private void animate(View view) {
        view.animate().cancel();
        view.setTranslationY(100);
        view.setAlpha(0);
        view.animate()
                .alpha(1.0f)
                .translationY(2)
                .setDuration(200);
    }

    /**
     * Returns the number of items in the list from the constructor
     * @return int
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Sub-class for defining what kind of values that will be displayed in the UI
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mCreatedByView;
        private final TextView mTitleView;
        private final TextView mEmpReviewedStatus;
        private final TextView mConceptType;
        private Concept mItem;

        /**
         * Refers this ViewHolder to the various view IDs that it will use when displaying
         * information
         * @param view View
         */
        private ViewHolder(View view) {
            super(view);
            mView = view;

            mCreatedByView = (TextView) view.findViewById(R.id.concept_created_by);
            mTitleView = (TextView) view.findViewById(R.id.concept_title);
            mEmpReviewedStatus = (TextView) view.findViewById(R.id.concept_employee_reviewed_status);
            mConceptType = (TextView) view.findViewById(R.id.concept_type);
        }
    }
}
