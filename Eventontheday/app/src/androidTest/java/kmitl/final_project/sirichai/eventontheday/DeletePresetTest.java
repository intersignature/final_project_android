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

public class DeletePresetTest {

    private DatabaseAdapter databaseAdapter;

    @Before
    public void beforeTest(){
        mActivityTestRule.launchActivity(new Intent());
        databaseAdapter = new DatabaseAdapter(InstrumentationRegistry.getTargetContext());
        databaseAdapter.clearDataPreset();
        addDataForTest();
    }

    private void addDataForTest(){
        databaseAdapter.insertDataPreset("TitlePreset1","LocationPreset1", "DetailPreset1");
        databaseAdapter.insertDataPreset("TitlePreset2","LocationPreset2", "DetailPreset2");
        databaseAdapter.insertDataPreset("TitlePreset3","LocationPreset3", "DetailPreset3");
        databaseAdapter.insertDataPreset("TitlePreset4","LocationPreset4", "DetailPreset4");
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
        answer : 3rd view title is TitlePreset4
         */

        onView(withText(R.string.tab_text_preset)).perform(click());
        onView(withRecyclerView(R.id.showAllPreset).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("DELETE")).perform(click());
        onView(withRecyclerView(R.id.showAllPreset).atPositionOnView(2, R.id.eventTitle)).check(matches(withText("Title: TitlePreset4")));
    }

    @Test
    public void deleteOneEventFromRecyclerIndex2(){
        /*
        test case : delete 2nd view of recycler view
        answer : 2nd view title is title2
         */

        onView(withText(R.string.tab_text_preset)).perform(click());
        onView(withRecyclerView(R.id.showAllPreset).atPositionOnView(2, R.id.eventTitle)).perform(longClick());
        onView(withText("DELETE")).perform(click());
        onView(withRecyclerView(R.id.showAllPreset).atPositionOnView(1, R.id.eventTitle)).check(matches(withText("Title: TitlePreset2")));
    }

}
