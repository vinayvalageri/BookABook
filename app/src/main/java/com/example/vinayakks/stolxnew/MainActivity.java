package com.example.vinayakks.stolxnew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
protected ProgressDialog pd;
    protected  ImageView img;
    protected  List<ParseObject> mlist;
    protected  ListView listview;
    public  String[] values1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                MainActivity.this.pd = ProgressDialog.show(MainActivity.this, null, "loading..");
                Intent i = new Intent(MainActivity.this, UpdatePost.class);
                startActivity(i);
                MainActivity.this.pd.dismiss();

            }
        });
        // values1 = new String[40];
        listview = (ListView)findViewById(R.id.listView);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Post");
        query.include("user");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    mlist = list;
                    MainActivity.this.listview.setAdapter(new CustomAdapter(MainActivity.this, list));


                }

            }
        });
        /*ListView list = (ListView)findViewById(R.id.listView);*/


         /*{ "Android Example ListActivity", "Adapter implementation", "Simple List View With ListActivity",
                "ListActivity Android", "Android Example", "ListActivity Source Code", "ListView ListActivity Array Adapter", "Android Example ListActivity","vinay","vinayak","vinayak","vinayak","vinayak" };*/
      /*  ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values1);
        list.setAdapter(adapter);*/




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //img = (ImageView)navigationView.findViewById(R.id.imageView);

        //img.setImageResource(R.drawable.b);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

      /*  if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else*/
        if (id == R.id.nav_share) {
            ParseUser.logOut();
            Toast.makeText(MainActivity.this, "Logged Out!!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            finish();


        } else if (id == R.id.nav_send) {
            Toast.makeText(MainActivity.this, "My Posts", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
