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
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by atomiz on 2/12/2560.
 */

public class UsePresetTest {

    private DatabaseAdapter databaseAdapter;

    @Before
    public void beforeTest(){
        mActivityTestRule.launchActivity(new Intent());
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        databaseAdapter.clearDataPreset();
        addDataForTest();
    }

    private void addDataForTest(){
        databaseAdapter.insertDataPreset("TitlePreset1","LocationPreset1", "DetailPreset1");
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @NonNull
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void usePresetTest1() {
        /*
        test case : view specific preset
        answer : show ViewPresetActivity with correct data
         */

        onView(withText(R.string.tab_text_preset)).perform(click());
        onView(withRecyclerView(R.id.showAllPreset).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("USE THIS PRESET")).perform(click());

        onView(withId(R.id.setTitle)).check(matches(withText("TitlePreset1")));
        onView(withId(R.id.setLocation)).check(matches(withText("LocationPreset1")));
        onView(withId(R.id.setDetail)).check(matches(withText("DetailPreset1")));
    }
}
