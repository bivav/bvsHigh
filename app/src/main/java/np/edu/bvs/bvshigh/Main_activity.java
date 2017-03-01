package np.edu.bvs.bvshigh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Main_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // check if the user is not logged in -> get the user in login page
        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), Login_Student.class));
        }

        // Main Homepage activity aka Dashboard
        Home_page fragment = new Home_page();
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_activity, fragment);
        transaction.commit();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Do Something.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//            }
//        });


        // Setting up Side Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Setting up username and email for navigationView Drawer Activity
        View headerView = navigationView.getHeaderView(0);
        TextView student_name = (TextView) headerView.findViewById(R.id.student_name);
        student_name.setText(SharedPrefManager.getInstance(this).getFullName());

        TextView student_email = (TextView) headerView.findViewById(R.id.student_email);
        student_email.setText(getResources().getString(R.string.brihaspati));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "You clicked settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                SharedPrefManager.getInstance(this).isLoggedOut();
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Logging Out...");
                finish();
                progressDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), Select_Category.class));
                break;
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

        // Handle navigation view item clicks here.
        final int id = item.getItemId();

        if (id == R.id.nav_dashboard){
            Home_page fragment = new Home_page();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_activity, fragment);
            transaction.commit();


        } else if (id == R.id.nav_routine) {

            startActivity(new Intent(getApplicationContext(), fragment_routine.class));

        } else if (id == R.id.nav_attendance) {

            fragment_attendance fragment = new fragment_attendance();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_activity, fragment);
            transaction.commit();

        } else if (id == R.id.nav_results) {

            startActivity(new Intent(getApplicationContext(), fragment_result.class));

        } else if (id == R.id.nav_fees) {

            fragment_fees fragment = new fragment_fees();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_activity, fragment);
            transaction.commit();


        } else if (id == R.id.nav_events){

            fragment_events fragment = new fragment_events();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_activity, fragment);
            transaction.commit();

        } else if (id == R.id.nav_alerts){

            fragment_alerts fragment = new fragment_alerts();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_activity, fragment);
            transaction.commit();

        } else if (id == R.id.nav_teacherContact){

            fragment_teachersContact fragment = new fragment_teachersContact();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_activity, fragment);
            transaction.commit();

        } else if (id == R.id.nav_calendar){

            startActivity(new Intent(getApplicationContext(), fragment_calendar.class));

        } else if (id == R.id.nav_send) {

            startActivity(new Intent(getApplicationContext(), fragment_result.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

}
