package kmitl.final_project.sirichai.eventontheday;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import kmitl.final_project.sirichai.eventontheday.view.MainActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by atomiz on 30/11/2560.
 */

public class AddEventTest {

    @Before
    public void beforeTest(){
        mActivityTestRule.launchActivity(new Intent());
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
            onView(withId(R.id.addEvent)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.addevent)).perform(click());
        }
        closeSoftKeyboard();
        onView(withId(R.id.submitAddEvent)).perform(click());
        onView(withText("Enter empty field")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void someFieldEmpty(){
        /*
        test case : some field input is empty
        answer : toast is show with text "Enter empty field"
         */

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        try {
            onView(withId(R.id.addEvent)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.addevent)).perform(click());
        }
        onView(withId(R.id.setTitle)).perform(replaceText("Title Event 1"));
        onView(withId(R.id.setLocation)).perform(replaceText("Kmitl"));
        onView(withId(R.id.setDetail)).perform(replaceText("Detail Event 1"));
        closeSoftKeyboard();
        onView(withId(R.id.submitAddEvent)).perform(click());
        onView(withText("Enter empty field")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void allFieldFillWithCorrectPattern(){ //add data to db test
        /*
        test case : all field input is fill with correct develop's pattern
        answer : toast is show with text "Insertion successful!!"
         */

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        try {
            onView(withId(R.id.addEvent)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.addevent)).perform(click());
        }
        closeSoftKeyboard();
        onView(withId(R.id.setTitle)).perform(replaceText("TitleEvent1"));
        onView(withId(R.id.setLocation)).perform(replaceText("Kmitl"));
        onView(withId(R.id.setDetail)).perform(replaceText("Detail Event 1"));

        onView(withId(R.id.setStartDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10 + 1, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setEndDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10 + 1, 24));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setAlertDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10 + 1, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setStartTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setEndTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(15,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setAlertTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(16,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.submitAddEvent)).perform(click());
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
            onView(withId(R.id.addEvent)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.addevent)).perform(click());
        }
        closeSoftKeyboard();
        onView(withId(R.id.setTitle)).perform(replaceText("Title@!#@@$#@$@$@$Event1"));
        onView(withId(R.id.setLocation)).perform(replaceText("Kmitl"));
        onView(withId(R.id.setDetail)).perform(replaceText("DetailEvent1"));

        onView(withId(R.id.setStartDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10 + 1, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setEndDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10 + 1, 24));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setAlertDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10 + 1, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setStartTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setEndTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(15,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setAlertTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(16,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.submitAddEvent)).perform(click());
        onView(withText("Title must not contain special characters")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void allFieldFillWithWrongDateAndTime(){
        /*
        test case : all field input is fill but wrong date and time(start date and time is after start date and time)
        answer : toast is show with text "Wrong start and end event"
         */

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        try {
            onView(withId(R.id.addEvent)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.addevent)).perform(click());
        }
        closeSoftKeyboard();
        onView(withId(R.id.setTitle)).perform(replaceText("TitleEvent1"));
        onView(withId(R.id.setLocation)).perform(replaceText("Kmitl"));
        onView(withId(R.id.setDetail)).perform(replaceText("DetailEvent1"));

        onView(withId(R.id.setStartDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2008, 10 + 1, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setEndDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2007, 10 + 1, 24));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setAlertDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10 + 1, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setStartTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setEndTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(15,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setAlertTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(16,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.submitAddEvent)).perform(click());
        onView(withText("Wrong start and end event")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void allFieldFillWithWrongAlertTime(){
        /*
        test case : all field input is fill but wrong date and time(alert time is before current time)
        answer : toast is show with text "Wrong alert time"
         */

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        try {
            onView(withId(R.id.addEvent)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.addevent)).perform(click());
        }
        closeSoftKeyboard();
        onView(withId(R.id.setTitle)).perform(replaceText("TitleEvent1"));
        onView(withId(R.id.setLocation)).perform(replaceText("Kmitl"));
        onView(withId(R.id.setDetail)).perform(replaceText("DetailEvent1"));

        onView(withId(R.id.setStartDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2017, 10 + 1, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setEndDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2018, 10 + 1, 24));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setAlertDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2007, 10 + 1, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setStartTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setEndTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(15,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.setAlertTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(16,15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.submitAddEvent)).perform(click());
        onView(withText("Wrong alert time")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
