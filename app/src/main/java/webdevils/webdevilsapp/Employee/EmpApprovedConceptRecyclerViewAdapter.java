package webdevils.webdevilsapp.Employee;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import common.Concept;
import webdevils.webdevilsapp.R;

/**
 * Created by Kevin 04/01/2017
 */
public class EmpApprovedConceptRecyclerViewAdapter extends
        RecyclerView.Adapter<EmpApprovedConceptRecyclerViewAdapter.ViewHolder> {

    private final LinkedList<Concept> mValues;
    private final EmpApprovedConceptListFragment.OnApprovedListFragmentInteractionListener mListener;
    public static Concept conceptUnderReview;
    View view;

    public EmpApprovedConceptRecyclerViewAdapter(LinkedList<Concept> items,
                                                 EmpApprovedConceptListFragment.
                                                         OnApprovedListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.emp_list_approved_item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        conceptUnderReview = holder.mItem;
        holder.mCreatedByView.setText("Concpet Created By: " + mValues.get(position).
                getUserThatCreatedThisConcept().getUserName());
        holder.mTitleView.setText(" Title: " + mValues.get(position).getTitle());
        holder.mEmpReviewedStatus.setText("Status: " + mValues.get(position).getStatus());
        holder.mConceptType.setText("Concept Type: " + mValues.get(position).getType());

        if(mValues.get(position).isSticky()){
            holder.mImageView.setImageResource(R.mipmap.note_pinned);
        }else{
            holder.mImageView.setImageResource(R.mipmap.blank_note_pinned);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onApprovedConceptListFragmentInteraction(holder.mItem);
                }
            }
        });

        for (int i = 0; i < getItemCount(); i++) {
            animate(view, i );
        }

    }

    private void animate(View view, final int pos) {
        view.animate().cancel();
        view.setTranslationY(100);
        view.setAlpha(0);
        view.animate()
                .alpha(1.0f)
                .translationY(2)
                .setDuration(600)
                .setStartDelay(pos * 100);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCreatedByView;
        public final TextView mTitleView;
        public final TextView mEmpReviewedStatus;
        public final TextView mConceptType;
        public final ImageView mImageView;
        public Concept mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mCreatedByView = (TextView) view.findViewById(R.id.concept_created_by);
            mTitleView = (TextView) view.findViewById(R.id.concept_title);
            mEmpReviewedStatus = (TextView) view.findViewById(R.id.concept_employee_reviewed_status);
            mConceptType = (TextView) view.findViewById(R.id.concept_type);
            mImageView = (ImageView) view.findViewById(R.id.sticky);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}
