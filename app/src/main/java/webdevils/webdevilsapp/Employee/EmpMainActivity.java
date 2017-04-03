package webdevils.webdevilsapp.Employee;

import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import common.Concept;
import common.User;
import server.Services;
import webdevils.webdevilsapp.R;

import static webdevils.webdevilsapp.R.menu.main;

public class EmpMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        MemberNewsUpdaterFragment.OnFragmentInteractionListener ,
        EmpConceptReviewFragment.onEmpConceptReviewFragmentInteraction ,
        EmpSubmittedConceptListFragment.OnSubmittedListFragmentInteractionListener,
        EmpApprovedConceptListFragment.OnApprovedListFragmentInteractionListener {

    public static User currentUser;
    Services services = new Services();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        currentUser = (User) getIntent().getSerializableExtra("userObject");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.emp_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_emp_main,
                        new EmpSubmittedConceptListFragment()).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fragmentManager = getFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.content_emp_main);
            if (fragment instanceof EmpConceptReviewFragment) {
                fragmentManager.beginTransaction()
                        .replace(R.id.content_emp_main,
                                new EmpSubmittedConceptListFragment()).commit();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.emp_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_unreviewed_concepts) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_emp_main,
                            new EmpSubmittedConceptListFragment()).commit();

        } else if (id == R.id.nav_update_member_news) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_emp_main,
                            new MemberNewsUpdaterFragment()).commit();

        } else if (id == R.id.manage_concepts) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_emp_main,
                            new EmpApprovedConceptListFragment()).commit();

        } else if (id == R.id.nav_connect_with_member) {

        } else if (id == R.id.nav_email_member) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMemberNewsUpdaterFragmentInteraction(Uri uri) {
        //TODO interact with Member news Updater Fragment
    }


    @Override
    public void onEmpConceptReviewFragmentInteraction(Uri uri) {
        //General Interaction with Review Concept Fragment
    }

    public void onApproved(View view) {
        EditText feedback = (EditText) findViewById(R.id.feedback_text);

        Concept conceptUnderReview = EmpConceptReviewFragment.conceptUnderReview;
        conceptUnderReview.setStatusToApproved();
        conceptUnderReview.setFeedback(feedback.getText().toString());
        services.saveConcept(conceptUnderReview);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_emp_main,
                        new EmpSubmittedConceptListFragment()).commit();
    }

    public void onRejected(View view){
        EditText feedback = (EditText) findViewById(R.id.feedback_text);

        Concept conceptUnderReview = EmpConceptReviewFragment.conceptUnderReview;
        conceptUnderReview.setStatusToRejected();
        conceptUnderReview.setFeedback(feedback.getText().toString());
        services.saveConcept(conceptUnderReview);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_emp_main,
                        new EmpSubmittedConceptListFragment()).commit();
    }

    @Override
    public void onSubmittedConceptListFragmentInteraction(Concept concept) {
        EmpConceptReviewFragment.setConceptUnderReview(concept);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_emp_main,
                        new EmpConceptReviewFragment()).commit();

    }

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
