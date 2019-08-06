package com.example.tjuhi.infotsav;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tjuhi.infotsav.Events.CoverFlowActivity;
import com.example.tjuhi.infotsav.Login.LoginActivity;
import com.example.tjuhi.infotsav.Notification.NotifMain;
import com.example.tjuhi.infotsav.Organisers.OrganiserActivity;
import com.example.tjuhi.infotsav.Sponsors.SponsorsActivity;
import com.example.tjuhi.infotsav.Workshop.Workshop;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tjuhi.infotsav.R.id.drawer_layout;

public class MainActivity extends DrawerActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        setTitle("");
        ButterKnife.bind(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0: {
                        return Frag.newInstance();

                    }
                    //case 1:
                    //    return RecyclerViewFragment.newInstance();
                    //case 2:
                    //   return WebViewFragment.newInstance();
                    default:
                        return Frag.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Selection";
                    case 1:
                        return "Actualités";
                    case 2:
                        return "Professionnel";
                    case 3:
                        return "Divertissement";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorAndDrawable(

                                getResources().getColor(R.color.green),
                                getResources().getDrawable(R.drawable.test));
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
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
        if (id == R.id.action_login) {
           Intent intent = new Intent(MainActivity.this, LoginActivity.class);
           startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
// Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_events) {
            startActivity(new Intent(MainActivity.this, CoverFlowActivity.class));
            MainActivity.this.overridePendingTransition(R.anim.left_in,R.anim.left_out);

        } else if (id == R.id.nav_notifications) {
            startActivity(new Intent(MainActivity.this, NotifMain.class));

        } else if (id == R.id.nav_workshops) {
            startActivity(new Intent(MainActivity.this, Workshop.class));
            MainActivity.this.overridePendingTransition(R.anim.bottom_in,R.anim.top_out);

        } else if (id == R.id.nav_sponsors) {
            startActivity(new Intent(MainActivity.this, SponsorsActivity.class));

        } else if (id == R.id.nav_contact) {
            startActivity(new Intent(MainActivity.this, OrganiserActivity.class));
            MainActivity.this.overridePendingTransition(R.anim.left_in,R.anim.left_out);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
