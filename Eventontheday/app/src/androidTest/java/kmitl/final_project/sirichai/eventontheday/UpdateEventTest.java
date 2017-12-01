package kmitl.final_project.sirichai.eventontheday;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import kmitl.final_project.sirichai.eventontheday.controller.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.view.MainActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.facebook.FacebookSdk.getApplicationContext;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by atomiz on 2/12/2560.
 */

public class UpdateEventTest {

    private DatabaseAdapter databaseAdapter;

    @Before
    public void beforeTest(){
        mActivityTestRule.launchActivity(new Intent());
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        databaseAdapter.clearDataEvent();
        addDataForTest();
    }

    private void addDataForTest(){
        databaseAdapter.insertDataEvent("title", "location", "02/12/2017", "03/12/2017",
                "05:17", "06:17", "02/12/2017", "05:15","detail");
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @NonNull
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void updateEventWithCorrectPattern(){
        /*
        test case : update event with correct input pattern
        answer : toast will show with text "update successful!!" and event title is "NewEventTitle"
         */

        onView(withText(R.string.tab_text_event)).perform(click());
        onView(withRecyclerView(R.id.showAllEvent).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("UPDATE")).perform(click());

        onView(withId(R.id.setNewTitle)).perform(replaceText("NewEventTitle"));
        closeSoftKeyboard();

        onView(withId(R.id.submitEditEvent)).perform(click());
        onView(withText("update successful!!")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.showAllEvent).atPositionOnView(0, R.id.eventTitle)).check(matches(withText("Title: NewEventTitle")));
    }

    @Test
    public void updateEventWithEmptyFieldInput(){
        /*
        test case : update event with empty input
        answer : toast will show with text "Enter empty field"
         */

        onView(withText(R.string.tab_text_event)).perform(click());
        onView(withRecyclerView(R.id.showAllEvent).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("UPDATE")).perform(click());

        onView(withId(R.id.setNewTitle)).perform(replaceText(""));
        closeSoftKeyboard();

        onView(withId(R.id.submitEditEvent)).perform(click());
        onView(withText("Enter empty field")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void updateEventWithIncorrectName(){
        /*
        test case : update event with incorrect name pattern
        answer : toast will show with text "Title must not contain special characters"
         */

        onView(withText(R.string.tab_text_event)).perform(click());
        onView(withRecyclerView(R.id.showAllEvent).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("UPDATE")).perform(click());

        onView(withId(R.id.setNewTitle)).perform(replaceText("@$#%$#@#@#"));
        closeSoftKeyboard();

        onView(withId(R.id.submitEditEvent)).perform(click());
        onView(withText("Title must not contain special characters")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void updateEventWithIncorrectDateAndTime(){
        /*
        test case : update event with incorrect date or time pattern
        answer : toast will show with text "Wrong start and end event"
         */

        onView(withText(R.string.tab_text_event)).perform(click());
        onView(withRecyclerView(R.id.showAllEvent).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("UPDATE")).perform(click());
        closeSoftKeyboard();

        onView(withId(R.id.setNewStartDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2008, 10 + 1, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setNewEndDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2007, 10 + 1, 24));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setNewAlertDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10 + 1, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setNewStartTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setNewEndTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(15,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setNewAlertTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(16,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.submitEditEvent)).perform(click());
        onView(withText("Wrong start and end event")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void updateEventWithIncorrectAlertTime(){
        /*
        test case : update event with incorrect alert date or alert time pattern
        answer : toast will show with text "Wrong alert time"
         */

        onView(withText(R.string.tab_text_event)).perform(click());
        onView(withRecyclerView(R.id.showAllEvent).atPositionOnView(0, R.id.eventTitle)).perform(longClick());
        onView(withText("UPDATE")).perform(click());
        closeSoftKeyboard();

        onView(withId(R.id.setNewAlertDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2007, 10 + 1, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setNewAlertTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(16,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.submitEditEvent)).perform(click());
        onView(withText("Wrong alert time")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
