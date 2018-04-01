package com.reid.cocoon.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.reid.cocoon.R;
import com.reid.cocoon.adapter.PhotoPagerAdapter;
import com.reid.cocoon.common.utils.DoubleTapHelper;
import com.reid.cocoon.common.utils.IntentHelper;
import com.reid.cocoon.ui.activity.AboutActivity;
import com.reid.cocoon.ui.fragment.NewFragment;
import com.reid.cocoon.ui.fragment.PopularFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PhotoPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        mViewPager = findViewById(R.id.viewPager);
        mPagerAdapter = new PhotoPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(NewFragment.newInstance(), "NEW");
        mPagerAdapter.addFragment(PopularFragment.newInstance(), "POPULAR");
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_home:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.nav_popular:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.nav_setting:
                IntentHelper.goSettings(this);
                break;
            case R.id.nav_about:
                IntentHelper.goAbout(this);
                break;
            default:
                break;
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
