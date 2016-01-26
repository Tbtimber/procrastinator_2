package com.procrastinator.isen.procrastinator_2;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SearchView;

import com.procrastinator.isen.procrastinator_2.fragments.DetailSearchFragment;
import com.procrastinator.isen.procrastinator_2.fragments.DetailViewFragment;
import com.procrastinator.isen.procrastinator_2.fragments.ResultFragment;
import com.procrastinator.isen.procrastinator_2.fragments.SelectionsFragment;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;
import com.procrastinator.isen.procrastinator_2.interfaces.MainActivityListener;
import com.procrastinator.isen.procrastinator_2.util.Proc_Constants;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityListener {


    private android.support.v4.app.Fragment currentFragment;
    private DetailViewFragment detailViewFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupMenuDrawerAndResearchBar();
        setSelectionsFragment();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(detailViewFragment != null){
            removeDetailViewFragment();
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
        /*if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.search_button) {
            search();
        }*/

        return super.onOptionsItemSelected(item);
    }
    public void search() {
        if(currentFragment instanceof DetailSearchFragment) {
            setDetailSearchFragment();
        } else {
            setResultFragment();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            // Handle the camera action
            if(!(currentFragment instanceof SelectionsFragment)) {
                setSelectionsFragment();
            }
        } else if (id == R.id.nav_detail_search) {
            if(!(currentFragment instanceof DetailSearchFragment)) {
                setDetailSearchFragment();
            }
        } else if (id == R.id.nav_suivi) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void detachCurrentFragment() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.detach(currentFragment).commit();
    }
    public void setResultFragment() {
        if(currentFragment != null) {
            if(currentFragment instanceof ResultFragment) {
                ((ResultFragment) currentFragment).newSearch(((SearchView) findViewById(R.id.test_searchBar)).getQuery().toString());
                return;
            } else {
                detachCurrentFragment();
            }
        }
        if(detailViewFragment != null) {
            removeDetailViewFragment();
        }
        Log.e("Detaching", "Attaching result Fragment");
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();

        String str = ((SearchView) findViewById(R.id.test_searchBar)).getQuery().toString();
        currentFragment = new ResultFragment();
        final Bundle bundle = new Bundle();
        bundle.putString("search_string", str);
        currentFragment.setArguments(bundle);
        transaction.add(R.id.main_container, currentFragment).commit();

    }
    public void setResultFragmentWithDetail() {
        //TODO implement method
    }
    public void removeDetailViewFragment() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.detach(detailViewFragment).commit();
        detailViewFragment = null;
    }
    public void setDetailSearchFragment() {
        if(currentFragment != null) {
            detachCurrentFragment();
        }
        if(detailViewFragment != null) {
            removeDetailViewFragment();
        }
        currentFragment = new DetailSearchFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_container, currentFragment, "selections_fragment");
        transaction.commit();
    }
    public void setSelectionsFragment() {
        if(currentFragment != null) {
            detachCurrentFragment();
        }
        if(detailViewFragment != null) {
            removeDetailViewFragment();
        }
        currentFragment = new SelectionsFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_container, currentFragment, "selections_fragment");
        transaction.commit();
    }
    public void setupMenuDrawerAndResearchBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar abar =  getSupportActionBar();
        abar.setDisplayShowTitleEnabled(false);
        abar.setDisplayUseLogoEnabled(false);
        abar.setDisplayHomeAsUpEnabled(false);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowHomeEnabled(false);

        ActionBar.LayoutParams lp1 = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View customNav = LayoutInflater.from(this).inflate(R.layout.search_bar, null);

        abar.setCustomView(customNav);

        SearchView searchview = (SearchView) findViewById(R.id.test_searchBar);
        if(searchview != null) {
            searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    search();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search();
                    return false;
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void showDetail(SearchResult detail) {
        if(detailViewFragment != null) {
            removeDetailViewFragment();
            return;
        }
        detailViewFragment = new DetailViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Proc_Constants.DETAIL_TITLE, detail.getTitle());
        bundle.putString(Proc_Constants.IMAGE_URL, detail.poster_path);
        bundle.putString(Proc_Constants.OVERVIEW, detail.overview);
        detailViewFragment.setArguments(bundle);

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_container, detailViewFragment, "selections_fragment");
        transaction.commit();
    }
    @Override
    public void dropDetail() {
        if(detailViewFragment != null)
            removeDetailViewFragment();
    }
}
