package kmitl.final_project.sirichai.eventontheday.view;

/**
 * Created by atomiz on 6/11/2560.
 */


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;


import java.util.Calendar;

import kmitl.final_project.sirichai.eventontheday.R;

public class Calendar_fragment extends Fragment {
    CalendarView mCalendarView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        mCalendarView = rootView.findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                Toast.makeText(calendarView.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();
                //change calendar date to selected date
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.YEAR, 2014);
//                calendar.set(Calendar.MONTH, 3);
//                calendar.set(Calendar.DAY_OF_MONTH, 22);
//                long milliTime = calendar.getTimeInMillis();
//                mCalendarView.setDate(milliTime ,true,true);
            }
        });
        return rootView;
    }


}
