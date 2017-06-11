package com.example.aavgeensingh.moviac;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;

import static com.example.aavgeensingh.moviac.R.styleable.TabLayout;


public class HomePage extends AppCompatActivity implements topRated.OnFragmentInteractionListener {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setPageTransformer(true, new RotateUpTransformer()); //CHANGE

        TabLayout tablayout=(TabLayout)findViewById(R.id.tabs) ;
        tablayout.setupWithViewPager(mViewPager);
        tablayout.getTabAt(0).setIcon(R.drawable.ic_date_range_black_24dp);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i=new Intent(HomePage.this,SearchActivity.class);
                startActivity(i);
            }
        });

    }


//
//    Picasso.with(context)
//            .load(url)
//    .placeholder(R.drawable.user_placeholder)
//    .error(R.drawable.user_placeholder_error)
//    .into(imageView);



    @Override
    public void onFragmentInteraction(Uri uri) {
        //IMPORTANT TO SEE WHIHC MOVIE IS CLICKED AND WHAT ACTION TO BE DONE.


    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position==1)
                return topRated.newInstance("topRated"); //Should be topRAted by working lke this only.So, deal with it.
            else
                return topRated.newInstance("popular");

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Popular";
                case 1:
                    return "Top Rated";
                case 2:
                    return "News/Gossip";
            }
            return null;
        }
    }
}




// CTRL + Q for Quick Documentation on the item which has cursor on it.
// CTRL + SHIFT + ID_NUMBER to set or remove a bookmark, CTRL + '+' + ID_NUMBER to go to that bookmark.
// /** + ENTER to generate javadoc for a function.
// SHIFT +F6 on a declaration and give new name to CHANGE THE NAME OF AN ELEMENT EVERYWHERE.
// Pressing TAB instead of ENTER when seeing suggestion using SPACE + CTRL, replaces the old method too.
// ALT + DIRECTION to change the tab or file above.
// CTRL + DIRECTION TO naviage through word instead of one alphabet.