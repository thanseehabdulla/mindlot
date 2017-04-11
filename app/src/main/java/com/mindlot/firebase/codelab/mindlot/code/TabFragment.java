package com.mindlot.firebase.codelab.mindlot.code;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Ratan on 7/27/2015.
 */
public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;
    Button button1,button2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
            View x =  inflater.inflate(com.mindlot.firebase.codelab.mindlot.R.layout.tab_layout,null);
            tabLayout = (TabLayout) x.findViewById(com.mindlot.firebase.codelab.mindlot.R.id.tabs);
            viewPager = (ViewPager) x.findViewById(com.mindlot.firebase.codelab.mindlot.R.id.view_pager);


//        FragmentManager manager = getFragmentManager();
//        MyAdapter adapter = new MyAdapter(manager);
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setTabsFromPagerAdapter(adapter);

        /**
         *Set an Apater for the View Pager
         */
       viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                    tabLayout.setupWithViewPager(viewPager);
                   }
        });
        button1 = (Button) x.findViewById(com.mindlot.firebase.codelab.mindlot.R.id.a);
        button2 = (Button) x.findViewById(com.mindlot.firebase.codelab.mindlot.R.id.b);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0, true);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1, true);

            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(1>position)
                {
                    button1.setTextColor(Color.parseColor("#000000"));
                    button2.setTextColor(Color.parseColor("#ccb2b2"));
                    button1.setBackgroundResource(com.mindlot.firebase.codelab.mindlot.R.drawable.button_bottom_color);
                    button2.setBackgroundColor(Color.parseColor("#ffffff"));
                }else
                {
                    button1.setTextColor(Color.parseColor("#ccb2b2"));
                    button2.setTextColor(Color.parseColor("#000000"));
                    button2.setBackgroundResource(com.mindlot.firebase.codelab.mindlot.R.drawable.button_bottom_color);
                    button1.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {


            }
        });
        return x;

    }


    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */
        String recent="RECENT("+4+"NEW)";

        @Override
        public Fragment getItem(int position)
        {

            Fragment frag=null;
            switch (position){
                case 0:
                    frag=new FavoriteFragment();
                    break;
                case 1:
                    frag=new RecentFragment();
                    break;

            }
            return frag;
        }

        @Override
        public int getCount() {

            return 2;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {
            String title="";
            switch (position){
                case 0:
                    title="M Home";
                    break;
                case 1:
                    title="M Chat";
                    break;
            }

            return title;
        }
    }
}
