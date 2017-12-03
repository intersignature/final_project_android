package kmitl.final_project.sirichai.eventontheday;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import kmitl.final_project.sirichai.eventontheday.controller.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by atomiz on 2/12/2560.
 */

public class DeleteEventTest {

    private DatabaseAdapter databaseAdapter;

    @Before
    public void beforeTest(){
        mActivityTestRule.launchActivity(new Intent());
        databaseAdapter = new DatabaseAdapter(InstrumentationRegistry.getTargetContext());
        databaseAdapter.clearDataEvent();
        addDataForTest();
    }

    private void addDataForTest(){
        databaseAdapter.insertDataEvent("title1", "location1", "02/12/2017", "03/12/2017",
                "05:17", "06:17", "02/12/2017", "05:15","detail1");
        databaseAdapter.insertDataEvent("title2", "location2", "02/12/2017", "03/12/2017",
                "05:17", "06:17", "02/12/2017", "05:15","detail2");
        databaseAdapter.insertDataEvent("title3", "location3", "02/12/2017", "03/12/2017",
                "05:17", "06:17", "02/12/2017", "05:15","detail3");
        databaseAdapter.insertDataEvent("title4", "location7", "02/12/2017", "03/12/2017",
                "05:17", "06:17", "02/12/2017", "05:15","detail4");
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @NonNull
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void deleteOneEventFromRecyclerIndex0(){
        /*
        test case : delete 1st view of recycler view
        answer : 3rd view title is title4
         */
        onView(withText(R.string.tab_text_event)).perform(click());
        onView(withRecyclerView(R.id.showAllEvent).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("DELETE")).perform(click());
        onView(withRecyclerView(R.id.showAllEvent).atPositionOnView(2, R.id.eventTitle)).check(matches(withText("Title: title4")));
    }

    @Test
    public void deleteOneEventFromRecyclerIndex1() {
        /*
        test case : delete 2nd view of recycler view
        answer : 3rd view title is title4
         */
        onView(withText(R.string.tab_text_event)).perform(click());
        onView(withRecyclerView(R.id.showAllEvent).atPositionOnView(1, R.id.eventTitle)).perform(longClick());
        onView(withText("DELETE")).perform(click());
        onView(withRecyclerView(R.id.showAllEvent).atPositionOnView(2, R.id.eventTitle)).check(matches(withText("Title: title4")));
    }
}
