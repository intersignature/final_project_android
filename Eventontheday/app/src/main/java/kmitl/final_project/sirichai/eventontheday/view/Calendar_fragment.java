package kmitl.final_project.sirichai.eventontheday.view;

/**
 * Created by atomiz on 6/11/2560.
 */


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.ListEvent;
import kmitl.final_project.sirichai.eventontheday.model.RecyclerAdapter;

public class Calendar_fragment extends Fragment {
    CalendarView mCalendarView;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListEvent> listEvents;
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
        recyclerView = rootView.findViewById(R.id.showEvent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listEvents = new ArrayList<>();

        for (int i=0; i<10;i++){
            ListEvent listEvent = new ListEvent(
                    "eventTitle"+String.valueOf(i+1),
                    "eventDate"+ String.valueOf(i+1),
                    "eventDescription"+String.valueOf(i+1)
            );
            listEvents.add(listEvent);
        }
        adapter = new RecyclerAdapter(listEvents,getContext()); // add list of event to recycler view
        recyclerView.setAdapter(adapter);
        return rootView;
    }


}
