/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import common.User;

/**
 * This MainActivity is used to display the
 * navigation drawer and fragments for each page.
 * If adding a new page fragment, consider adding
 * it to the navigation_menu and to the page
 * switching logic in onNavigationItemSelected
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame
                        , new LandingFragment()).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

    }

    /**
     * Fragments can call this function to easily get the currently logged in user name
     * @return User
     */
    public User getUser() {
        Intent p = getIntent();
        final String name = p.getStringExtra("userName");
        final User user = (User) getIntent().getSerializableExtra("userObject");
        return user;
    }

    /**
     * Opens concept from Concept list using Title to grab information
     * @param fTitle String
     */
    public void openConcept(String fTitle) {
        ConceptVoteFragment fragObj = new ConceptVoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", fTitle);
        //set ConceptVoteFragment Arguments
        fragObj.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.content_frame, fragObj).commit();
    }

    /**
     * Opens concept from My Concept list using Title to grab information
     * @param fTitle String
     */
    public void openMyConcept(String fTitle) {
        ViewEditFragment fragObj = new ViewEditFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", fTitle);
        //set ConceptVoteFragment Arguments
        fragObj.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.content_frame, fragObj).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fm = getFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStackImmediate();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_landing) {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.content_frame
                    , new LandingFragment()).commit();
        } else if (id == R.id.nav_my_concepts) {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.content_frame
                    , new ConceptsFragment()).commit();
        } else if (id == R.id.nav_featured) {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.content_frame
                            , new FeaturedFragment()).commit();
        } else if (id == R.id.nav_submit_concept) {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.content_frame
                    , new SubmitConceptFragment()).commit();
        } else if (id == R.id.nav_logout) {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
