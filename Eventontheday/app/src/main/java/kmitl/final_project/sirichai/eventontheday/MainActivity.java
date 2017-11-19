package kmitl.final_project.sirichai.eventontheday;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.ListDate;
import kmitl.final_project.sirichai.eventontheday.notification.Message;
import kmitl.final_project.sirichai.eventontheday.notification.MessageReceiver;
import kmitl.final_project.sirichai.eventontheday.notification.TimerService;
import kmitl.final_project.sirichai.eventontheday.view.AddEventActivity;
import kmitl.final_project.sirichai.eventontheday.view.AddPresetActivity;
import kmitl.final_project.sirichai.eventontheday.view.Calendar_fragment;
import kmitl.final_project.sirichai.eventontheday.view.Event_fragment;
import kmitl.final_project.sirichai.eventontheday.view.Preset_fragment;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private CalendarView mCalendarView;
    private DatabaseAdapter databaseAdapter;
    List<ListDate> listAllDates = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        MessageReceiver receiver = new MessageReceiver(new Message());

        Intent intent = new Intent(this, TimerService.class);
        intent.putExtra("time", true);
        intent.putExtra("receiver", receiver);
        startService(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addEvent) {
            return true;
        }
        if(id==R.id.addPreset){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickAddEventListenet(MenuItem item) {
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
    }

    public void onClickAddPresetListenet(MenuItem item) {
        Intent intent = new Intent(this, AddPresetActivity.class);
        startActivity(intent);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Calendar_fragment calendarFragment = new Calendar_fragment();
                    return calendarFragment;
                case 1:
                    Event_fragment eventFragment = new Event_fragment();
                    return eventFragment;
                case 2:
                    Preset_fragment presetFragment = new Preset_fragment();
                    return presetFragment;
                default: return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "calendar";
                case 1:
                    return "preset";
                case 2:
                    return "event";
                default: return null;
            }
        }
    }
}
