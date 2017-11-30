package kmitl.final_project.sirichai.eventontheday;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;

import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

/**
 * Created by atomiz on 30/11/2560.
 */

public final class PickerActions {

    private PickerActions() {
        // no Instance
    }

    /**
     * Returns a {@link ViewAction} that sets a date on a {@link DatePicker}.
     */
    public static ViewAction setDate(final int year, final int monthOfYear, final int dayOfMonth) {

        // monthOfYear which starts with zero in DatePicker widget.
        final int normalizedMonthOfYear = monthOfYear - 1;

        return new ViewAction() {

            @Override
            public void perform(UiController uiController, View view) {
                final DatePicker datePicker = (DatePicker) view;
                datePicker.updateDate(year, normalizedMonthOfYear, dayOfMonth);
            }

            @Override
            public String getDescription() {
                return "set date";
            }



            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(DatePicker.class), isDisplayed());
            }
        };

    }

    /**
     * Returns a {@link ViewAction} that sets a time on a {@link TimePicker}.
     */
    public static ViewAction setTime(final int hours, final int minutes) {

        return new ViewAction() {

            @Override
            public void perform(UiController uiController, View view) {
                final TimePicker timePicker = (TimePicker) view;
                timePicker.setCurrentHour(hours);
                timePicker.setCurrentMinute(minutes);
            }

            @Override
            public String getDescription() {
                return "set time";
            }

            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(TimePicker.class), isDisplayed());
            }
        };

    }

}