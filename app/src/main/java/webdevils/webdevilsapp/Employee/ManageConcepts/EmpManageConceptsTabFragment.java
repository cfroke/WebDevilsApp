package webdevils.webdevilsapp.Employee.ManageConcepts;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import webdevils.webdevilsapp.Employee.ManageConcepts.ApprovedTab.EmpApprovedConceptListFragment;
import webdevils.webdevilsapp.Employee.ManageConcepts.RejectedTab.EmpRejectedConceptListFragment;
import webdevils.webdevilsapp.Employee.ManageConcepts.SubmittedTab.EmpSubmittedConceptListFragment;
import webdevils.webdevilsapp.R;

/**
 * Created by Kevin on 3/28/2017.
 */
public class EmpManageConceptsTabFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;

    public EmpManageConceptsTabFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.emp_manage_concepts_tabs_fragment,null);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return view;
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch (position){
                case 0 :
                    return new EmpApprovedConceptListFragment();
                case 1 :
                    return new EmpRejectedConceptListFragment();
                case 2 :
                    return new EmpSubmittedConceptListFragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

//        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Approved";
                case 1 :
                    return "Rejected";
                case 2 :
                    return "Submitted";
            }
            return null;
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onManageConceptsFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onManageConceptsFragmentInteraction(Uri uri);
    }
}
