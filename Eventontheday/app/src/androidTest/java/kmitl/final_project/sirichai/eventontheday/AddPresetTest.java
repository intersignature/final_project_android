package kmitl.final_project.sirichai.eventontheday;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import kmitl.final_project.sirichai.eventontheday.view.MainActivity;

/**
 * Created by atomiz on 1/12/2560.
 */

public class AddPresetTest {

    @Before
    public void beforeTest(){
        mActivityTestRule.launchActivity(new Intent());
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void allFieldFillWithCorrectPattern(){
        /*

         */
    }

    @Test
    public void allFieldFillWithIncorrectPattern(){
        /*

         */
    }
}
