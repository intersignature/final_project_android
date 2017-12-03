package kmitl.final_project.sirichai.eventontheday;

import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import kmitl.final_project.sirichai.eventontheday.controller.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.view.MainActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.facebook.FacebookSdk.getApplicationContext;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by atomiz on 2/12/2560.
 */

public class UpdatePresetTest {

    private DatabaseAdapter databaseAdapter;

    @Before
    public void beforeTest(){
        mActivityTestRule.launchActivity(new Intent());
        databaseAdapter = new DatabaseAdapter(InstrumentationRegistry.getTargetContext());
        databaseAdapter.clearDataPreset();
        addDataForTest();
    }

    private void addDataForTest(){
        databaseAdapter.insertDataPreset("TitlePreset","LocationPreset", "DetailPreset");
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @NonNull
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void updatePresetWithCorrectPattern(){
        /*
        test case : update preset with correct pattern
        answer : toast will show with text "Update successful!!" and title in recyclerview will change to "NewPresetTitle"
         */

        onView(withText(R.string.tab_text_preset)).perform(click());
        onView(withRecyclerView(R.id.showAllPreset).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("UPDATE")).perform(click());

        onView(withId(R.id.setNewTitlePreset)).perform(replaceText("NewPresetTitle"));
        closeSoftKeyboard();

        onView(withId(R.id.submitEditPreset)).perform(click());
        onView(withText("Update successful!!")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.showAllPreset).atPositionOnView(0, R.id.eventTitle)).check(matches(withText("Title: NewPresetTitle")));
    }

    @Test
    public void updatePresetWithEmptyFieldInput(){
        /*
        test case : update preset with empty input
        answer : toast will show with text "Enter empty field"
         */

        onView(withText(R.string.tab_text_preset)).perform(click());
        onView(withRecyclerView(R.id.showAllPreset).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("UPDATE")).perform(click());

        onView(withId(R.id.setNewTitlePreset)).perform(replaceText(""));
        onView(withId(R.id.setNewLocationPreset)).perform(replaceText(""));
        onView(withId(R.id.setNewDetailPreset)).perform(replaceText(""));
        closeSoftKeyboard();

        onView(withId(R.id.submitEditPreset)).perform(click());
        onView(withText("Enter empty field")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void updatePresetWithIncorrectName(){
        /*
        test case : update preset with incorrect name pattern
        answer : toast will show with text "Title must not contain special characters"
         */

        onView(withText(R.string.tab_text_preset)).perform(click());
        onView(withRecyclerView(R.id.showAllPreset).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("UPDATE")).perform(click());

        onView(withId(R.id.setNewTitlePreset)).perform(replaceText("@#@#@#@$dkjfkls$#$"));
        closeSoftKeyboard();

        onView(withId(R.id.submitEditPreset)).perform(click());
        onView(withText("Title must not contain special characters")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
