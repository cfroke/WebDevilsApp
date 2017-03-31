package webdevils.webdevilsapp.Employee;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

import common.Concept;
import webdevils.webdevilsapp.R;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Concept} and makes a call to the
 * specified {@link EmpConceptListFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EmpConceptRecyclerViewAdapter extends RecyclerView.Adapter<EmpConceptRecyclerViewAdapter.ViewHolder> {

    private final LinkedList<Concept> mValues;
    private final EmpConceptListFragment.OnListFragmentInteractionListener mListener;
    public static Concept conceptUnderReview;


    public EmpConceptRecyclerViewAdapter(LinkedList<Concept> items, EmpConceptListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.emp_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        conceptUnderReview = holder.mItem;
//        holder.mCreatedByView.setText("Concpet Created By: " + mValues.get(position).getUserThatCreatedThisConcept().getUserName());
        holder.mTitleView.setText("Title: " + mValues.get(position).getTitle());
        holder.mEmpReviewedStatus.setText("Status: " + mValues.get(position).getStatus());
        holder.mConceptType.setText("Concept Type: " + mValues.get(position).getType());
        holder.mConceptDescription.setText("Description: " + mValues.get(position).getDescription());
        holder.mFeedback.setText("Feedback: " + mValues.get(position).getFeedback());
        holder.mReviewConceptBtn.setText("Review " + mValues.get(position).getUserThatCreatedThisConcept().getUserName() + "'s Concept");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onConceptListFragmentInteraction(holder.mItem);
                }

            }
        });

        holder.mReviewConceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onConceptListFragmentInteraction(holder.mItem);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
//        public final TextView mCreatedByView;
        public final TextView mTitleView;
        public final TextView mEmpReviewedStatus;
        public final TextView mConceptType;
        public final TextView mConceptDescription;
        public final TextView mFeedback;
        public final Button mReviewConceptBtn;
        public Concept mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
//            mCreatedByView = (TextView) view.findViewById(R.id.concept_created_by);
            mTitleView = (TextView) view.findViewById(R.id.concept_title);
            mEmpReviewedStatus = (TextView) view.findViewById(R.id.concept_employee_reviewed_status);
            mConceptType = (TextView) view.findViewById(R.id.concept_type);
            mConceptDescription = (TextView) view.findViewById(R.id.concept_description);
            mFeedback = (TextView) view.findViewById(R.id.concept_feedback);
            mReviewConceptBtn = (Button) view.findViewById(R.id.review_concept_button);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}
