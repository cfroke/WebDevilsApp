/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp.Employee;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import common.Concept;
import common.User;
import server.Services;
import webdevils.webdevilsapp.R;

/**
 * Main Activity (View Controller) for Employee views. All view fragments have listeners registered
 * with this class in order to link all view fragments to the main activity.
 */
public class EmpMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        EmpConceptReviewFragment.onEmpConceptReviewFragmentInteraction ,
        EmpSubmittedConceptListFragment.OnSubmittedListFragmentInteractionListener,
        EmpApprovedConceptListFragment.OnApprovedListFragmentInteractionListener {

    public static User currentUser;
    Services services = new Services();

    /**
     * Created initial view when this activity is first created
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // gather user object from login activity
        currentUser = (User) getIntent().getSerializableExtra("userObject");

        //set up initial view
        setContentView(R.layout.emp_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set up nav drawer menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Place initial fragment into view for the user
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_emp_main,
                        new EmpSubmittedConceptListFragment()).commit();

        //Register nav drawer listener
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * If the system back button is pressed then the following overridden method will be used
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //if the current view is a nested fragment go back to a top level fragment
            FragmentManager fragmentManager = getFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.content_emp_main);
            if (fragment instanceof EmpConceptReviewFragment) {
                fragmentManager.beginTransaction()
                        .replace(R.id.content_emp_main,
                                new EmpSubmittedConceptListFragment()).commit();
            } else {
                //default back button operation
                super.onBackPressed();
            }
        }
    }

    /**
     * When using the nav drawer, the user selects an item from a list in the nav drawer view that
     * is presented. This method replaces the fragment in view with a view fragment that is selected
     * from this list.
     * @param item MenuItem
     * @return boolean
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_unreviewed_concepts) {
            //Go to the list of itemts that were recently submitted.
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_emp_main,
                            new EmpSubmittedConceptListFragment()).commit();
        } else if (id == R.id.nav_update_member_news) {

            //Stretch goal. This menu item could be used to send news updates to the member users

        } else if (id == R.id.manage_concepts) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_emp_main,
                            new EmpApprovedConceptListFragment()).commit();

        } else if (id == R.id.nav_connect_with_member) {

            //Stretch goal. This is where a social media fragment could be used to connect employees
            //with users or other employees

        } else if (id == R.id.nav_email_member) {

            //Stretch goal. This is where a user of the app could email other users of the app

        }

        //close the nav drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Registering this lister here binds the Employee Concept Review Fragment to the main view
     * controller. This will prevent this fragment from being unconnected to the application.
     */
    @Override
    public void onEmpConceptReviewFragmentInteraction() {
        //do nothing
        //can be used for general interaction with the concept review fragment
    }

    /**
     * Handles the onClick event from the Approve button in the {@link EmpConceptReviewFragment}
     * @param view View
     */
    public void onApproved(View view) {
        //gather feedback text
        EditText feedback = (EditText) findViewById(R.id.feedback_text);

        //update concept status and updated the concept object on the server
        Concept conceptUnderReview = EmpConceptReviewFragment.conceptUnderReview;
        conceptUnderReview.setStatusToApproved();
        conceptUnderReview.setFeedback(feedback.getText().toString());
        services.saveConcept(conceptUnderReview);

        //Go back to the list of recently submitted concepts
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_emp_main,
                        new EmpSubmittedConceptListFragment()).commit();
    }

    /**
     * Handles the onClick event from the Reject button in the {@link EmpConceptReviewFragment}
     * @param view View
     */
    public void onRejected(View view){
        //gather feedback text
        EditText feedback = (EditText) findViewById(R.id.feedback_text);

        //update concept status and update concept object on the server
        Concept conceptUnderReview = EmpConceptReviewFragment.conceptUnderReview;
        conceptUnderReview.setStatusToRejected();
        conceptUnderReview.setFeedback(feedback.getText().toString());
        services.saveConcept(conceptUnderReview);

        //Go back to the list of recently submitted concepts
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_emp_main,
                        new EmpSubmittedConceptListFragment()).commit();
    }

    /**
     * Registering this lister here binds the Submitted Concept List Fragment to the main view
     * controller. It listens for interactions with a given item on the card list for this fragment.
     * @param concept {@link common.Concept}
     */
    @Override
    public void onSubmittedConceptListFragmentInteraction(Concept concept) {
        EmpConceptReviewFragment.setConceptUnderReview(concept);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_emp_main,
                        new EmpConceptReviewFragment()).commit();

    }

    /**
     * Registering this lister here binds the Approved Concept List Fragment to the main view
     * controller. It listens for interactions with a given item on the card list for this fragment.
     * Also, it will make a concept sticky or un-sticky it.
     * @param concept {@link common.Concept}
     */
    @Override
    public void onApprovedConceptListFragmentInteraction(Concept concept) {
        if(concept.isSticky()){
            services.makeConceptSlippery(concept);
        }else{
            services.makeConceptSticky(concept);
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_emp_main,
                        new EmpApprovedConceptListFragment()).commit();
    }
}
