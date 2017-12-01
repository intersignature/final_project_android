package kmitl.final_project.sirichai.eventontheday;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by atomiz on 2/12/2560.
 */

public class ViewEventTest {

    private DatabaseAdapter databaseAdapter;

    @Before
    public void beforeTest(){
        mActivityTestRule.launchActivity(new Intent());
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        databaseAdapter.clearDataEvent();
        addDataForTest();
    }

    private void addDataForTest(){
        databaseAdapter.insertDataEvent("title1", "Chachoengsao", "02/12/2017", "03/12/2017",
                "05:17", "06:17", "02/12/2017", "05:15","detail1");
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @NonNull
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void viewEventTest1(){
        /*
        test case : view specific event
        answer : show ViewEventActivity with correct data
         */

        onView(withText(R.string.tab_text_event)).perform(click());
        onView(withRecyclerView(R.id.showAllEvent).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("VIEW")).perform(click());

        onView(withId(R.id.viewTitle)).check(matches(withText("title1")));
        onView(withId(R.id.viewLocation)).check(matches(withText("AT : Chachoengsao")));
        onView(withId(R.id.viewDetail)).check(matches(withText("Detail : detail1")));
        onView(withId(R.id.viewStartDate)).check(matches(withText("Start date : 02/12/2017 AT : 05:17")));
        onView(withId(R.id.viewEndDate)).check(matches(withText("End date : 03/12/2017 AT : 06:17")));
        onView(withId(R.id.viewAlertDate)).check(matches(withText("Alert date : 02/12/2017 AT : 05:15")));
    }
}
