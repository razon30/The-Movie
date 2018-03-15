package saddam.razon.themovie.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import saddam.razon.themovie.R;
import saddam.razon.themovie.dataParser.Parser;
import saddam.razon.themovie.utils.MyApplication;
import saddam.razon.themovie.view.fragments.mainActivity.NewReleaseFragment;
import saddam.razon.themovie.view.fragments.mainActivity.TopRatedFragment;
import saddam.razon.themovie.view.fragments.mainActivity.UpcomingFragment;

/**
 * Created by RAZON on 20-Jan-18.
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    Parser parser;
    @Inject
    NewReleaseFragment newReleaseFragment;
    @Inject
    TopRatedFragment topRatedFragment;
    @Inject
    UpcomingFragment upcomingFragment;

    ViewPager viewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getMyApplication(this).getComponent().inject(this);
        setContentView(R.layout.activity_main);

        initialization();
        worksOnTabViewPager();

    }

    private void worksOnTabViewPager() {



        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);

    }

    private void initialization() {

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }


    private class TabPagerAdapter extends FragmentPagerAdapter {
        TabPagerAdapter(FragmentManager fragmentManager) {

            super(fragmentManager);

        }

        @Override
        public Fragment getItem(int position) {


            switch (position) {
                case 0:
                    return upcomingFragment;
                case 1:
                    return newReleaseFragment;
                case 2:
                    return topRatedFragment;
                default:
                    return upcomingFragment;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "COMING SOON";
                case 1:
                    return "NEW RELEASE";
                case 2:
                    return "TOP RATED";
                default:
                    return "COMING SOON";
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

    }


}
