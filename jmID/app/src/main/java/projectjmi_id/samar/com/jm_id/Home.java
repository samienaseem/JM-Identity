package projectjmi_id.samar.com.jm_id;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentContainer;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //TextView tf;
    static String query1,query2;
    AlertDialog.Builder builder;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        builder=new AlertDialog.Builder(this);
        firebaseAuth=FirebaseAuth.getInstance();
        //FirebaseUser user=firebaseAuth.getCurrentUser();
        //Toast.makeText(getApplicationContext(),user.getEmail()+"0",Toast.LENGTH_SHORT).show();
      //  tabLayout=(TabLayout)findViewById(R.id.)




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        HomePage hm=new HomePage();



        //Dashboard d=new Dashboard();
        fragmentTransaction.add(R.id.fragment,hm);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.commit();


       // tf=(TextView)findViewById(R.id.datehai);



        //tf.setText(date);











        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
            Toast.makeText(getApplicationContext(),"samar",Toast.LENGTH_SHORT).show();
            /*builder.setMessage("Do you want ot go back").setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                     dialog.cancel();
                }
            });
            AlertDialog alert=builder.create();
            alert.setTitle("Confirmation Alert");
            alert.show();*/


        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.barSearch);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
        new viewDetails(searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // Toast.makeText(getApplicationContext(),query+"",Toast.LENGTH_SHORT).show();

                query1=query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               // Toast.makeText(getApplicationContext(),newText+"",Toast.LENGTH_SHORT).show();
                query2=newText;
                return false;
            }
        });
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in Andr oidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id==R.id.Search){

            android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

            searchFragment sf=new searchFragment();
            fragmentTransaction.replace(R.id.fragment,sf).commit();
            fragmentTransaction.addToBackStack(null);




        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            setTitle("Register Student");

            android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

            position3fragment pef=new position3fragment();


            fragmentTransaction.replace(R.id.fragment,pef).commit();
            fragmentTransaction.addToBackStack(null);


        }
        else if (id == R.id.nav_camera1){
            setTitle("Gate wise list");
            android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

            GateWise gw=new GateWise();


            fragmentTransaction.replace(R.id.fragment,gw).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            fragmentTransaction.addToBackStack(null);



        }
        else if (id == R.id.nav_slideshow) {

            setTitle("View details");
            startActivity(new Intent(getApplicationContext(),StudentViewList.class));

            /*android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();


            viewDetails ve=new viewDetails();

            Bundle b=new Bundle();
            b.putString("query",query1);
            ve.setArguments(b);
            fragmentTransaction.replace(R.id.fragment,ve).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            fragmentTransaction.addToBackStack(null);*/

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_logout) {

            //firebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        } else if (id == R.id.nav_exit) {
            finish();
        }
        else if (id==R.id.nav_home){

            setTitle("Home");

            android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

            HomePage hm=new HomePage();
            fragmentTransaction.replace(R.id.fragment,hm).commit();
            fragmentTransaction.addToBackStack(null);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
