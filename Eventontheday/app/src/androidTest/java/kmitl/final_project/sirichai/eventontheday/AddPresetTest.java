package kmitl.final_project.sirichai.eventontheday;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import kmitl.final_project.sirichai.eventontheday.controller.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.view.MainActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
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
 * Created by atomiz on 1/12/2560.
 */

public class AddPresetTest {

    private DatabaseAdapter databaseAdapter;

    @Before
    public void beforeTest(){
        mActivityTestRule.launchActivity(new Intent());
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        databaseAdapter.clearDataPreset();
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void allFieldEmpty(){
        /*
        test case : all field input is empty
        answer : toast is show with text "Enter empty field"
         */
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        try {
            onView(withId(R.id.addPreset)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.addpreset)).perform(click());
        }
        closeSoftKeyboard();
        onView(withId(R.id.submitAddPreset)).perform(click());
        onView(withText("Enter empty field")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void allFieldFillWithCorrectPattern(){
        /*
        test case : all field input is fill with correct develop's pattern
        answer : toast is show with text "Insertion successful!!"
         */
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        try {
            onView(withId(R.id.addPreset)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.addpreset)).perform(click());
        }
        onView(withId(R.id.setTitlePreset)).perform(replaceText("TitlePreset1"));
        onView(withId(R.id.setLocationPreset)).perform(replaceText("KmitlPreset"));
        onView(withId(R.id.setDetailPreset)).perform(replaceText("Detail Preset 1"));
        closeSoftKeyboard();
        onView(withId(R.id.submitAddPreset)).perform(click());
        onView(withText("Insertion successful!!")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void allFieldFillWithIncorrectName(){
        /*
        test case : all field input is fill but name is in incorrect
        answer : toast is show with text "Title must not contain special characters"
         */
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        try {
            onView(withId(R.id.addPreset)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.addpreset)).perform(click());
        }
        onView(withId(R.id.setTitlePreset)).perform(replaceText("Title@@#$#$#Preset1$#$#$@#@%"));
        onView(withId(R.id.setLocationPreset)).perform(replaceText("KmitlPreset"));
        onView(withId(R.id.setDetailPreset)).perform(replaceText("Detail Preset 1"));
        closeSoftKeyboard();
        onView(withId(R.id.submitAddPreset)).perform(click());
        onView(withText("Title must not contain special characters")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
