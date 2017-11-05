package kmitl.final_project.sirichai.eventontheday;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private CalendarView mCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Context context = getApplicationContext();
                String selectDate = i+"/"+i1+"/"+i2;
                Toast.makeText(context, selectDate, Toast.LENGTH_SHORT).show();
        //change calendar date to selected date
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.YEAR, 2014);
//                calendar.set(Calendar.MONTH, 3);
//                calendar.set(Calendar.DAY_OF_MONTH, 22);
//                long milliTime = calendar.getTimeInMillis();
//                mCalendarView.setDate(milliTime ,true,true);
            }
        });

    }
}
